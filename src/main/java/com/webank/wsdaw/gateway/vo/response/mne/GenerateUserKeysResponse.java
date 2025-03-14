package com.webank.wsdaw.gateway.vo.response.mne;

import lombok.Data;

@Data
public class GenerateUserKeysResponse {
    private String weworkUid;
    private String publicKey;
}
