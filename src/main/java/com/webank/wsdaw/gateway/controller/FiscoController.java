package com.webank.wsdaw.gateway.controller;

import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.service.face.FiscoQueryService;
import com.webank.wsdaw.gateway.service.fisco.FiscoProxyService;
import com.webank.wsdaw.gateway.vo.request.SendTxRequest;
import com.webank.wsdaw.gateway.vo.request.tx.CallRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bcos")
@Slf4j
public class FiscoController {

    @Autowired private FiscoQueryService fiscoQueryService;

    @Autowired private FiscoProxyService fiscoProxyService;

    @PostMapping("sendTransaction")
    public CommonResponse sendTransaction(@RequestBody SendTxRequest sendTxRequest)
            throws Exception {
        return fiscoProxyService.sendTransaction(sendTxRequest);
    }

    @PostMapping("call")
    public CommonResponse call(@RequestBody @Valid CallRequest request) throws Exception {
        log.info("Call request is {}", JSONUtil.toJsonPrettyStr(request));
        return fiscoProxyService.call(request);
    }

    @PostMapping("getBlockNumber")
    public CommonResponse getBlockNumber() {
        return fiscoProxyService.getBlockNumber();
    }
}
