package com.webank.wsdaw.gateway.api.chdis;

import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import java.util.List;
import lombok.Data;

@Data
public class AssetListBySingleAddressResponse {
    private int code;
    private String message;
    private AssetData data;
    private String attachment;
    private String bizSeqNo;
    private boolean success;

    @Data
    public static class AssetData {
        private String contractAddress;
        private String uri;
        private CollectionInfo collectionInfo;
        private int assetCount;
        private List<Asset> assetList;

        @Data
        public static class CollectionInfo {
            private String image;
            private String name;
            private String symbol;
            private String description;
            private String network;
            private String assetStandard;
            private List<Attribute> attributes;

            @Data
            public static class Attribute {
                private String traitType;
                private String value;
            }
        }

        @Data
        public static class Asset {
            private int assetId;
            private String assetUri;
            private int assetAmount;
            private AssetInfo assetInfo;

            @Data
            public static class AssetInfo {
                private String image;
                private String name;
                private String description;
                private int assetType;
                private boolean writeOffEnable;
                private boolean transferEnable;
                private String creator;
                private List<CollectionInfo.Attribute> attributes;
            }
        }
    }

    public CommonResponse<AssetData> toCommonResponse() {
        CommonResponse<AssetData> cr = new CommonResponse<>();
        cr.setCode(this.code);
        cr.setMsg(this.message);
        cr.setData(this.data);
        return cr;
    }
}
