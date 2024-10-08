package com.webank.wsdaw.gateway.service.hk;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.manager.EmailManager;
import com.webank.wsdaw.gateway.service.face.AccessService;
import com.webank.wsdaw.gateway.service.face.HkVerifyService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.config.hk.*;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.hk.HkTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HkServiceImpl implements HkVerifyService {
    @Autowired private SystemConfig systemConfig;
    @Autowired private EmailManager emailManager;
    @Autowired private AccessService accessService;

    @Override
    public CommonResponse<String> sendVerificationCode(SendCodeRequest request) {
        log.info("send verification info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl() + ApiConstant.DAW_CONFIG_HK_SENDCODE)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse<String> cr =
                    JsonUtils.fromJson(response.body(), CommonResponse.class, String.class);
            if (cr.isSuccess()) {
                emailManager.sendVerificationCode(request.getEmail(), cr.getData());
                log.info("send code {} to {}", cr.getData(), request.getEmail());
                return CommonResponse.success();
            } else {
                log.error("send code failed , response : {}", response);
                return cr;
            }
        }
        return CommonResponse.error(response.getStatus(), "network error");
    }

    @Override
    public CommonResponse<String> verifyEmailCode(VerifyCodeRequest request) {
        log.info("verify code info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_HK_VERIFYCODE)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        CommonResponse<String> cr =
                JsonUtils.fromJson(response.body(), CommonResponse.class, String.class);
        return cr;
    }

    @Override
    public CommonResponse<HkTokenResponse> register(HkRegisterRequest request) {
        log.info("hk register info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl() + ApiConstant.DAW_CONFIG_HK_REGISTER)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse<HkTokenResponse> cr =
                    JsonUtils.fromJson(
                            response.body(), CommonResponse.class, HkTokenResponse.class);
            if (cr.isSuccess()) {
                String token = accessService.createToken(request.getEmail());
                cr.getData().setToken(token);
                log.info("login response is {}", JSONUtil.toJsonPrettyStr(cr));
                return cr;
            } else {
                log.error("send code failed , response : {}", response);
                return cr;
            }
        }
        return CommonResponse.error(response.getStatus(), "network error");
    }

    @Override
    public CommonResponse<HkTokenResponse> login(HkLoginRequest request) {
        log.info("hk register info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(systemConfig.getDawConfigUrl() + ApiConstant.DAW_CONFIG_HK_LOGIN)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse<HkTokenResponse> cr =
                    JsonUtils.fromJson(
                            response.body(), CommonResponse.class, HkTokenResponse.class);
            if (cr.isSuccess()) {
                String token = accessService.createToken(request.getEmail());
                cr.getData().setToken(token);
                log.info("login response is {}", JSONUtil.toJsonPrettyStr(cr));
                return cr;
            } else {
                log.error("send code failed , response : {}", response);
                return cr;
            }
        }
        return CommonResponse.error(response.getStatus(), "network error");
    }

    @Override
    public CommonResponse<Boolean> registerStatus(HkEmailRequest request) {
        log.info("hk registerStatus info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_HK_REGISTERSTATUS)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse<Boolean> cr =
                    JsonUtils.fromJson(response.body(), CommonResponse.class, Boolean.class);
            return cr;
        }
        return CommonResponse.error(response.getStatus(), "network error");
    }
}
