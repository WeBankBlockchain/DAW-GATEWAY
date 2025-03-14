package com.webank.wsdaw.gateway.api.chdis;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AssetListByAddressListRequest {
    @NotBlank(message = "userAddress cannot be null.")
    private String userAddress;

    private List<String> contractAddressList;
}
