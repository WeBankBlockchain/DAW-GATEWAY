package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.service.face.MnemonicService;
import com.webank.wsdaw.gateway.vo.request.mne.GetMnemonicRequest;
import com.webank.wsdaw.gateway.vo.request.mne.SaveMnemonicInfoRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {
    @Autowired private MnemonicService mnemonicService;

    @PostMapping("getMnemonicByUserId")
    public CommonResponse getMnemonicByUserId(
            @RequestHeader("userId") String userId,
            @RequestBody @Valid GetMnemonicRequest request) {
        log.info("getMnemonicByUserId user id {} => {}", request.getUserId(), userId);
        request.setUserId(userId);
        return mnemonicService.getMnemonicByUserId(request);
    }

    @PostMapping("saveMnemonicInfo")
    public CommonResponse saveMnemonicInfo(
            @RequestHeader("userId") String userId,
            @RequestBody @Valid SaveMnemonicInfoRequest request) {
        log.info("saveMnemonicInfo user id {} => {}", request.getUserId(), userId);
        request.setUserId(userId);
        return mnemonicService.saveMnemonicInfo(request);
    }
}
