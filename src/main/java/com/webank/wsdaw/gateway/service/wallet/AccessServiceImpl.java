package com.webank.wsdaw.gateway.service.wallet;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.service.face.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccessServiceImpl implements AccessService {
    @Autowired private SystemConfig systemConfig;

    @Override
    public String createToken(String userId) {
        DateTime dateTime =
                DateUtil.offsetMinute(DateUtil.date(), systemConfig.getTokenExpireTime());
        String token =
                JWT.create()
                        .setExpiresAt(dateTime)
                        .setPayload("appId", "beanBag")
                        .setPayload("userId", userId)
                        .setPayload("nonce", System.currentTimeMillis())
                        .setPayload("admin", true)
                        .setKey(systemConfig.getAdminPrivateKey().getBytes())
                        .sign();
        log.info("access token is {}", token);
        log.info("expire at: {}", dateTime.toString());
        return token;
    }

    public static boolean verifyAccessToken(
            String token, String secret, String userId, String appId) {
        JWT jwt = JWT.of(token);
        log.debug("header type is {}", jwt.getHeader(JWTHeader.TYPE));
        log.debug("algorithm is {}", jwt.getHeader(JWTHeader.ALGORITHM));
        log.info("jwt payloads: {}", jwt.getPayloads().toString());
        JWT j = JWTUtil.parseToken(token);
        String userIdJWT = (String) j.getPayload("userId");
        String appIdJWT = (String) j.getPayload("appId");
        if (userId == null || !StrUtil.equalsAnyIgnoreCase(userIdJWT, userId)) {
            log.error("userId invalid: {} {}", userIdJWT, userId);
            return false;
        }
        if (appId == null || !StrUtil.equalsAnyIgnoreCase(appIdJWT, appId)) {
            log.error("appId invalid: {} {}", appIdJWT, appId);
            return false;
        }
        return jwt.setKey(secret.getBytes()).validate(0);
    }
}
