package com.webank.wsdaw.gateway.filter;

import com.webank.wsdaw.gateway.service.wallet.AccessServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private String secret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // filter the security scan logs
        if (StringUtils.equalsIgnoreCase(requestURI, "/")) {
            log.debug("request uri is {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization Unauthorized: please check your header.");
            return;
        }
        log.info("request uri is {}", requestURI);
        if (requestURI.startsWith("/api/access/")
                || requestURI.startsWith("/api/config/")
                || requestURI.startsWith("/api/kyc/getWechatWorkUserId")
                || requestURI.startsWith("/api/hk/")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            log.error("Authorization Unauthorized: please check your header.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization Unauthorized: please check your header.");
            return;
        }
        token = token.substring(7);
        log.info("token is {}", token);
        String userId = request.getHeader("userId");
        String appId = request.getHeader("appId");
        log.info("userId is {}, appId is {}", userId, appId);
        if (!AccessServiceImpl.verifyAccessToken(token, secret, userId, appId)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Authorization Unauthorized: invalid access token.");
            log.error("Authorization Unauthorized: invalid access token.");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
