package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.api.chdis.*;
import com.webank.wsdaw.gateway.vo.request.config.AssetSeriesRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import java.util.List;

public interface ChdisCacheService {

    CommonResponse<List<AssetListByAddressListResponse.AssetData>> getAssetListByAddressList(
            AssetSeriesRequest assetSeriesRequest);

    CommonResponse<AssetListBySingleAddressResponse.AssetData> getAssetListBySingleAddress(
            AssetListBySingleAddressRequest assetListBySingleAddressRequest);

    CommonResponse<GetAssetDetailResponse.AssetDetail> getAssetDetail(
            GetAssetDetailRequest getAssetDetailRequest);

    CommonResponse<String> getNftAssetDetailUrl(
            NftAddressDetailUrlRequest nftAddressDetailUrlRequest);
}
