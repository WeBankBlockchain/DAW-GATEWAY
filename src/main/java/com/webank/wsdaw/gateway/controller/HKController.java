package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.service.face.HkVerifyService;
import com.webank.wsdaw.gateway.vo.request.config.hk.HkEmailRequest;
import com.webank.wsdaw.gateway.vo.request.config.hk.HkLoginRequest;
import com.webank.wsdaw.gateway.vo.request.config.hk.HkRegisterRequest;
import com.webank.wsdaw.gateway.vo.request.config.hk.SendCodeRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.hk.HkTokenResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hk")
public class HKController {
    @Autowired private HkVerifyService emailVerifyService;

    @PostMapping("sendCode")
    public CommonResponse<String> hkSendCode(@RequestBody @Valid SendCodeRequest request)
            throws Exception {
        return emailVerifyService.sendVerificationCode(request);
    }

    @PostMapping("register")
    public CommonResponse<HkTokenResponse> hkRegister(@RequestBody @Valid HkRegisterRequest request)
            throws Exception {
        return emailVerifyService.register(request);
    }

    @PostMapping("login")
    public CommonResponse<HkTokenResponse> hkLogin(@RequestBody @Valid HkLoginRequest request)
            throws Exception {
        return emailVerifyService.login(request);
    }

    @PostMapping("registerStatus")
    public CommonResponse<Boolean> registerStatus(@RequestBody @Valid HkEmailRequest request)
            throws Exception {
        return emailVerifyService.registerStatus(request);
    }
}
