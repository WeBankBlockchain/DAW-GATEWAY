package com.webank.wsdaw.gateway.api.chdis;

import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.Data;

@Data
public class GetAssetDetailResponse {
    private int code;
    private String message;
    private AssetDetail data;
    private String bizSeqNo;
    private boolean success;

    @Data
    public class AssetDetail {
        private int assetId;
        private String assetUri;
        private int assetAmount;
        private AssetInfo assetInfo;
        private Object attachment;
    }

    @Data
    class AssetInfo {
        private String image;
        private String name;
        private String description;
        private int assetType;
        private boolean writeOffEnable;
        private boolean transferEnable;
        private String creator;
        private Object[] attributes;
    }

    public CommonResponse<AssetDetail> toCommonResponse() {
        CommonResponse<AssetDetail> cr = new CommonResponse<>();
        cr.setCode(this.code);
        cr.setMsg(this.message);
        cr.setData(this.data);
        return cr;
    }
}
