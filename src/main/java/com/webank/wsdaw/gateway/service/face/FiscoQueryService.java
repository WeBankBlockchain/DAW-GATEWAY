package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.tx.AccountBalanceRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface FiscoQueryService {

    CommonResponse balanceOf(AccountBalanceRequest request);
}
