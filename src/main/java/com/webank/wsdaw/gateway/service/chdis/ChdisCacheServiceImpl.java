package com.webank.wsdaw.gateway.service.chdis;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.api.ApiCommonResponse;
import com.webank.wsdaw.gateway.api.chdis.*;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.AssetSeriesService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.config.AssetSeriesRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.assetSeries.AssetSeriesEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChdisCacheServiceImpl
        implements com.webank.wsdaw.gateway.service.face.ChdisCacheService {
    @Autowired private SystemConfig systemConfig;
    @Autowired private AssetSeriesService assetSeriesService;

    @Override
    public CommonResponse<List<AssetListByAddressListResponse.AssetData>> getAssetListByAddressList(
            AssetSeriesRequest assetSeriesRequest) {
        CommonResponse<List<AssetSeriesEntity>> asset =
                assetSeriesService.getAssetSeriesByType(assetSeriesRequest);
        if (!asset.isSuccess()) {
            return CommonResponse.error(asset.getCode(), asset.getMsg());
        }
        List<String> addressList =
                asset.getData().stream()
                        .map(e -> e.getContractAddress())
                        .collect(Collectors.toList());
        AssetListByAddressListRequest request = new AssetListByAddressListRequest();
        request.setContractAddressList(addressList)
                .setUserAddress(assetSeriesRequest.getUserAddress());
        HttpRequest httpRequest =
                HttpRequest.post(
                                systemConfig.getWsasFrontUrl()
                                        + ApiConstant.CHDIS_ASSETLIST_BY_ADDRESSLIST)
                        .header("Content-Type", "application/json")
                        .body(JSONUtil.toJsonStr(request));

        HttpResponse response = httpRequest.execute();
        HttpLogUtil.printLogs(httpRequest, JSONUtil.toJsonPrettyStr(request), response);

        if (response.isOk()) {
            AssetListByAddressListResponse assetResponse =
                    JSONUtil.toBean(response.body(), AssetListByAddressListResponse.class, true);

            if (assetResponse.getCode() != 0) {
                return CommonResponse.error(assetResponse.getCode(), assetResponse.getMessage());
            }

            CommonResponse<List<AssetListByAddressListResponse.AssetData>> res =
                    assetResponse.toCommonResponse();
            List<AssetListByAddressListResponse.AssetData> list = res.getData();
            for (AssetSeriesEntity ase : asset.getData()) {
                for (AssetListByAddressListResponse.AssetData d : list) {
                    if (StrUtil.equalsIgnoreCase(
                            ase.getContractAddress(), d.getContractAddress())) {
                        d.setSeriesIcon(ase.getSeriesIcon());
                        d.setSeriesName(ase.getSeriesName());
                        d.setSeriesType(ase.getSeriesType());
                    }
                }
            }
            log.info("getAssetListByAddressList result {}", JSONUtil.toJsonPrettyStr(res));
            return res;
        } else {
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<AssetListBySingleAddressResponse.AssetData> getAssetListBySingleAddress(
            AssetListBySingleAddressRequest assetListBySingleAddressRequest) {
        HttpRequest httpRequest =
                HttpRequest.post(
                                systemConfig.getWsasFrontUrl()
                                        + ApiConstant.CHDIS_ASSETLIST_BY_SINGLEADDRESS)
                        .header("Content-Type", "application/json")
                        .body(JSONUtil.toJsonStr(assetListBySingleAddressRequest));

        HttpResponse response = httpRequest.execute();
        HttpLogUtil.printLogs(
                httpRequest, JSONUtil.toJsonPrettyStr(assetListBySingleAddressRequest), response);

        if (response.isOk()) {
            AssetListBySingleAddressResponse assetResponse =
                    JSONUtil.toBean(response.body(), AssetListBySingleAddressResponse.class, true);

            if (assetResponse.getCode() != 0) {
                return CommonResponse.error(assetResponse.getCode(), assetResponse.getMessage());
            }

            return assetResponse.toCommonResponse();
        } else {
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<GetAssetDetailResponse.AssetDetail> getAssetDetail(
            GetAssetDetailRequest getAssetDetailRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("userAddress", getAssetDetailRequest.getUserAddress());
        if (StrUtil.isNotBlank(getAssetDetailRequest.getContractAddress())) {
            params.put("assetAddress", getAssetDetailRequest.getContractAddress());
        }
        if (getAssetDetailRequest.getAssetId() >= 0) {
            params.put("assetId", getAssetDetailRequest.getAssetId());
        }
        HttpRequest httpRequest =
                HttpRequest.get(systemConfig.getWsasFrontUrl() + ApiConstant.CHDIS_GET_ASSETDETAIL)
                        .header("Content-Type", "application/json")
                        .header("bizSeqNo", RandomUtil.randomString(10))
                        .form(params);

        HttpResponse response = httpRequest.execute();
        HttpLogUtil.printLogs(
                httpRequest, JSONUtil.toJsonPrettyStr(getAssetDetailRequest), response);

        if (response.isOk()) {
            GetAssetDetailResponse res =
                    JSONUtil.toBean(
                            response.body(), new TypeReference<GetAssetDetailResponse>() {}, true);

            if (res.getCode() != 0) {
                return CommonResponse.error(res.getCode(), res.getMessage());
            }

            return res.toCommonResponse();
        } else {
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<String> getNftAssetDetailUrl(
            NftAddressDetailUrlRequest nftAddressDetailUrlRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("assetId", nftAddressDetailUrlRequest.getAssetId());
        params.put("assetAddress", nftAddressDetailUrlRequest.getAssetAddress());
        HttpRequest httpRequest =
                HttpRequest.get(
                                systemConfig.getWsasFrontUrl()
                                        + ApiConstant.CHDIS_GET_DETAILADDRESSURL)
                        .header("Content-Type", "application/json")
                        .form(params);

        HttpResponse response = httpRequest.execute();
        HttpLogUtil.printLogs(
                httpRequest, JSONUtil.toJsonPrettyStr(nftAddressDetailUrlRequest), response);

        if (response.isOk()) {
            ApiCommonResponse<String> res =
                    JSONUtil.toBean(
                            response.body(),
                            new TypeReference<ApiCommonResponse<String>>() {},
                            true);

            if (res.getCode() != 0) {
                return CommonResponse.error(res.getCode(), res.getMessage());
            }

            return res.toCommonResponse();
        } else {
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
