package com.webank.wsdaw.gateway.service.fisco;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.ProxyService;
import com.webank.wsdaw.gateway.vo.request.SendTxRequest;
import com.webank.wsdaw.gateway.vo.request.tx.CallRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call.CallOutput;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FiscoProxyService implements ProxyService {

    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse<Call.CallOutput> call(CallRequest request) {
        HttpResponse resp =
                HttpRequest.post(systemConfig.getDawTxProxyUrl() + ApiConstant.DAW_TXPROXY_CALL)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        if (resp.isOk()) {
            CommonResponse<Call.CallOutput> cr =
                    JSONUtil.toBean(
                            resp.body(), new TypeReference<CommonResponse<CallOutput>>() {}, true);
            return cr;
        } else {
            log.error("OnError: call request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<TransactionReceipt> sendTransaction(SendTxRequest request) {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawTxProxyUrl()
                                        + ApiConstant.DAW_TXPROXY_SENDTRANSACTION)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        if (resp.isOk()) {
            CommonResponse<TransactionReceipt> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<TransactionReceipt>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: sendTransaction request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<Long> getBlockNumber() {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawTxProxyUrl()
                                        + ApiConstant.DAW_TXPROXY_GETBLOCKNUMBER)
                        .execute();
        if (resp.isOk()) {
            CommonResponse<Long> cr =
                    JSONUtil.toBean(
                            resp.body(), new TypeReference<CommonResponse<Long>>() {}, true);
            return cr;
        } else {
            log.error("OnError: getBlockNumber request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
