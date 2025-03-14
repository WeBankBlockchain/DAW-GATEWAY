package com.webank.wsdaw.gateway.service.wallet;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.MnemonicService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.mne.ClearMnemonicRequest;
import com.webank.wsdaw.gateway.vo.request.mne.GetMnemonicRequest;
import com.webank.wsdaw.gateway.vo.request.mne.SaveMnemonicInfoRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MnemonicServiceImpl implements MnemonicService {

    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse saveMnemonicInfo(SaveMnemonicInfoRequest request) {
        log.info("SaveMnemonicInfoRequest info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawSafeboxUrl()
                                        + ApiConstant.DAW_SAFEBOX_SAVEMNINFO)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();

        log.info("Response Status: {}", response.getStatus());
        log.info("Response Headers: {}", response.headers());
        log.info("Response Body: {}", response.body());
        if (response.isOk()) {
            CommonResponse cr = JsonUtils.fromJson(response.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: saveAccountInfo request failed , reponse : {}", response);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse getMnemonicByUserId(GetMnemonicRequest request) {
        log.info("GetMnemonicRequest info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawSafeboxUrl()
                                        + ApiConstant.DAW_SAFEBOX_GETMNBYUSERID)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        log.info("Response Status: {}", response.getStatus());
        log.info("Response Headers: {}", response.headers());
        log.info("Response Body: {}", response.body());
        if (response.isOk()) {
            CommonResponse cr = JsonUtils.fromJson(response.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: getAccountByUserId request failed , response : {}", response);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse clearMnemonicByUserId(ClearMnemonicRequest request) {
        log.info("clearMnemonicByUserId is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawSafeboxUrl()
                                        + ApiConstant.DAW_SAFEBOX_CLEARMNBYUSERID)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(resp);
        if (resp.isOk()) {
            CommonResponse cr = JsonUtils.fromJson(resp.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: clearAccountByUserId request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
