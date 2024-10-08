package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.relay.RelayTokenRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public interface RelayService {

    public CommonResponse<String> getRelayToken(RelayTokenRequest relayTokenRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException;
}
