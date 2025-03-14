package com.webank.wsdaw.gateway.vo.response.config.asset;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AssetInfoResponse {
    private String networkName;
    private String contractAddress;
    private String assetName;
    private String assetSymbol;
    private String assetType;
    private String assetIcon;
    private Integer assetStatus;
    private AssetConfig assetConfig;
}
