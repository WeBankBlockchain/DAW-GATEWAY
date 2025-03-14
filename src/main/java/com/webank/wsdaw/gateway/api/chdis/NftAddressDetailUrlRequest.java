package com.webank.wsdaw.gateway.api.chdis;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NftAddressDetailUrlRequest {
    @NotBlank(message = "bizSeqNo cannot be null.")
    private String bizSeqNo;

    @NotBlank(message = "assetId cannot be null.")
    private String assetId;

    @NotBlank(message = "assetAddress cannot be null.")
    private String assetAddress;
}
