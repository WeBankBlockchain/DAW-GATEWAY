package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.config.AssetSeriesRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.assetSeries.AssetSeriesEntity;
import java.util.List;

public interface AssetSeriesService {
    CommonResponse<List<AssetSeriesEntity>> getAssetSeriesByType(AssetSeriesRequest request);
}
