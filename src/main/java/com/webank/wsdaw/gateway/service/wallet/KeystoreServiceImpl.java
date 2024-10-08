package com.webank.wsdaw.gateway.service.wallet;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.KeystoreService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.mne.GenerateUserKeysRequest;
import com.webank.wsdaw.gateway.vo.request.mne.GetUserKeysRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KeystoreServiceImpl implements KeystoreService {
    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse generateUserKeys(GenerateUserKeysRequest request) {
        log.info("generateUserKeys info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawKeystoreUrl()
                                        + ApiConstant.DAW_KEYSTORE_GENERATEKEYS)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse cr = JsonUtils.fromJson(response.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: generateUserKeys request failed , response : {}", response);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse getUserKeys(GetUserKeysRequest request) {
        log.info("GetUserKeysRequest info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawKeystoreUrl()
                                        + ApiConstant.DAW_KEYSTORE_GETUSERKEYS)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse cr = JsonUtils.fromJson(response.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: getUserKeys request failed , response : {}", response);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
