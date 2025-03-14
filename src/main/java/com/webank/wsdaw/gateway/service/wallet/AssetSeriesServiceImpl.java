package com.webank.wsdaw.gateway.service.wallet;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.AssetSeriesService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.config.AssetSeriesRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.assetSeries.AssetSeriesEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AssetSeriesServiceImpl implements AssetSeriesService {
    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse<List<AssetSeriesEntity>> getAssetSeriesByType(
            AssetSeriesRequest request) {
        log.info("getAssetSeriesByType info is {}", JSONUtil.toJsonPrettyStr(request));
        HttpResponse response =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_ASSET_SERIES_BY_TYPE)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(response);
        if (response.isOk()) {
            CommonResponse<List<AssetSeriesEntity>> cr =
                    JSONUtil.toBean(
                            response.body(),
                            new TypeReference<CommonResponse<List<AssetSeriesEntity>>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: getAssetSeriesByType request failed , response : {}", response);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
