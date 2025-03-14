package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.SendTxRequest;
import com.webank.wsdaw.gateway.vo.request.tx.CallRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;

public interface ProxyService {

    CommonResponse<Call.CallOutput> call(CallRequest request);

    CommonResponse<TransactionReceipt> sendTransaction(SendTxRequest request);

    CommonResponse<Long> getBlockNumber();
}
