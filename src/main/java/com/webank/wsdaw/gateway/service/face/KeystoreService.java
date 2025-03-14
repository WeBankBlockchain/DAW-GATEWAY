package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.mne.GenerateUserKeysRequest;
import com.webank.wsdaw.gateway.vo.request.mne.GetUserKeysRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;

public interface KeystoreService {
    CommonResponse generateUserKeys(GenerateUserKeysRequest request);

    CommonResponse getUserKeys(GetUserKeysRequest request);
}
