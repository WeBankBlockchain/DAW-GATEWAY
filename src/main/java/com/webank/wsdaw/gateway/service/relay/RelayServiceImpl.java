package com.webank.wsdaw.gateway.service.relay;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.service.face.RelayService;
import com.webank.wsdaw.gateway.util.JwtIssEncoder;
import com.webank.wsdaw.gateway.vo.request.relay.RelayTokenRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import io.jsonwebtoken.Jwts;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.utils.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RelayServiceImpl implements RelayService {
    @Autowired private SystemConfig systemConfig;

    public String getJwt(RelayTokenRequest relayTokenRequest)
            throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        // 从HEX字符串反序列化密钥
        byte[] privateKeyBytes = Hex.decode(systemConfig.getRelaySeHex());
        byte[] publicKeyBytes = Hex.decode(systemConfig.getRelayPublicKeyHex());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("Ed25519", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        String iss = JwtIssEncoder.encodeIss(publicKeyBytes);

        // 设置JWT的Header部分
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "EdDSA");
        headers.put("typ", "JWT");

        long iat = System.currentTimeMillis() / 1000;
        long exp = iat + relayTokenRequest.getTtl();
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", iss);
        claims.put("sub", Base64.encode(RandomUtil.randomBytes(32)));
        claims.put("aud", relayTokenRequest.getAud());
        claims.put("iat", iat);
        claims.put("exp", exp);
        log.info("JWT claims: {}", JSONUtil.toJsonPrettyStr(claims));
        String jwt =
                Jwts.builder()
                        .header()
                        .add(headers)
                        .and()
                        .claims(claims)
                        .signWith(privateKey, Jwts.SIG.EdDSA)
                        .compact();

        log.info("getJwt jwt:{}", jwt);
        return jwt;
    }

    @Override
    public CommonResponse<String> getRelayToken(RelayTokenRequest relayTokenRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        return CommonResponse.success(getJwt(relayTokenRequest));
    }
}
