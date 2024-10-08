package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.service.face.KeystoreService;
import com.webank.wsdaw.gateway.vo.request.mne.GenerateUserKeysRequest;
import com.webank.wsdaw.gateway.vo.request.mne.GetUserKeysRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keystore")
@Slf4j
public class KeystoreController {
    @Autowired private KeystoreService keystoreService;

    @PostMapping("generateUserKeys")
    public CommonResponse generateUserKeys(
            @RequestHeader("userId") String userId,
            @RequestBody @Valid GenerateUserKeysRequest request) {
        log.info("generateUserKeys user id {} => {}", request.getWeworkUserId(), userId);
        request.setWeworkUserId(userId);
        return keystoreService.generateUserKeys(request);
    }

    @PostMapping("getUserKeys")
    public CommonResponse getUserKeys(
            @RequestHeader("userId") String userId,
            @RequestBody @Valid GetUserKeysRequest request) {
        log.info("getUserKeys user id {} => {}", request.getWeworkUserId(), userId);
        request.setWeworkUserId(userId);
        return keystoreService.getUserKeys(request);
    }
}
