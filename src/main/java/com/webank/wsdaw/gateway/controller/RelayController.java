package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.service.face.RelayService;
import com.webank.wsdaw.gateway.vo.request.relay.RelayTokenRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relay")
@Slf4j
public class RelayController {
    @Autowired private RelayService relayService;

    @PostMapping("/getToken")
    public CommonResponse<String> getToken(@RequestBody RelayTokenRequest request)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        return relayService.getRelayToken(request);
    }
}
