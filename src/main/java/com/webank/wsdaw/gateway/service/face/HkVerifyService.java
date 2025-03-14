package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.config.hk.*;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.hk.HkTokenResponse;

public interface HkVerifyService {
    CommonResponse<String> sendVerificationCode(SendCodeRequest request);

    CommonResponse<String> verifyEmailCode(VerifyCodeRequest request);

    CommonResponse<HkTokenResponse> register(HkRegisterRequest request);

    CommonResponse<HkTokenResponse> login(HkLoginRequest request);

    CommonResponse<Boolean> registerStatus(HkEmailRequest request);
}
