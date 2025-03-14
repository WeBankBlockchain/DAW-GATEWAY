package com.webank.wsdaw.gateway.api.chdis;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetAssetDetailRequest {
    @NotBlank(message = "userAddress cannot be null.")
    private String userAddress;

    private String contractAddress;
    private int assetId = -1;
}
