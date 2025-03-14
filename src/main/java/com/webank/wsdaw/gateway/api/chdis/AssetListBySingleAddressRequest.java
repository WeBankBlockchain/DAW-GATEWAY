package com.webank.wsdaw.gateway.api.chdis;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssetListBySingleAddressRequest {
    @NotBlank(message = "userAddress cannot be null.")
    private String userAddress;

    @NotBlank(message = "contractAddress cannot be null.")
    private String contractAddress;

    private int pageNumber = 1;
    private int pageSize = 8;
}
