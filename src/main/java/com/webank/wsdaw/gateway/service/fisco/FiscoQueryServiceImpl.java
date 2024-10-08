package com.webank.wsdaw.gateway.service.fisco;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.FiscoQueryService;
import com.webank.wsdaw.gateway.vo.request.tx.AccountBalanceRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FiscoQueryServiceImpl implements FiscoQueryService {

    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse balanceOf(AccountBalanceRequest request) {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawTxProxyUrl() + ApiConstant.DAW_TXPROXY_BALANCEOF)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        if (resp.isOk()) {
            log.info("{}", resp.body());
            CommonResponse cr = JSONUtil.toBean(resp.body(), CommonResponse.class);
            return cr;
        } else {
            log.error("OnError: balanceOf error {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
