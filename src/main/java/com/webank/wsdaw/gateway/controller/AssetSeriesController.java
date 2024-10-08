package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.api.chdis.*;
import com.webank.wsdaw.gateway.service.chdis.ChdisCacheServiceImpl;
import com.webank.wsdaw.gateway.service.face.AssetSeriesService;
import com.webank.wsdaw.gateway.vo.request.config.AssetSeriesRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.assetSeries.AssetSeriesEntity;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assetSeries")
public class AssetSeriesController {
    @Autowired private AssetSeriesService assetSeriesService;
    @Autowired private ChdisCacheServiceImpl chdisCacheServiceImpl;

    @PostMapping("getByType")
    public CommonResponse<List<AssetSeriesEntity>> getAssetSeriesByType(
            @RequestBody @Valid AssetSeriesRequest request) throws Exception {
        return assetSeriesService.getAssetSeriesByType(request);
    }

    @PostMapping("/assetListByAddressList")
    public CommonResponse<List<AssetListByAddressListResponse.AssetData>> getAssetListByAddressList(
            @RequestBody @Valid AssetSeriesRequest request) {
        return chdisCacheServiceImpl.getAssetListByAddressList(request);
    }

    @PostMapping("/assetListBySingleAddress")
    public CommonResponse<AssetListBySingleAddressResponse.AssetData> getAssetListBySingleAddress(
            @RequestBody @Valid AssetListBySingleAddressRequest request) {
        return chdisCacheServiceImpl.getAssetListBySingleAddress(request);
    }

    @PostMapping("/getAssetDetailInfo")
    public CommonResponse<GetAssetDetailResponse.AssetDetail> getAssetDetailInfo(
            @RequestBody @Valid GetAssetDetailRequest request) {
        return chdisCacheServiceImpl.getAssetDetail(request);
    }

    @PostMapping("/getAssetDetailUrl")
    public CommonResponse<String> getAssetDetailUrl(
            @RequestBody @Valid NftAddressDetailUrlRequest request) {
        return chdisCacheServiceImpl.getNftAssetDetailUrl(request);
    }
}
