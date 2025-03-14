package com.webank.wsdaw.gateway.service.face;

import com.webank.wsdaw.gateway.vo.request.config.discovery.SearchRequest;
import com.webank.wsdaw.gateway.vo.response.*;
import com.webank.wsdaw.gateway.vo.response.config.asset.AssetInfoResponse;
import com.webank.wsdaw.gateway.vo.response.config.discovery.BannerInfo;
import com.webank.wsdaw.gateway.vo.response.config.discovery.ExploreInfo;
import com.webank.wsdaw.gateway.vo.response.config.version.AppUpdateInfoResponse;
import com.webank.wsdaw.gateway.vo.response.config.version.VersionInfo;
import java.util.List;

public interface ConfigService {
    CommonResponse<List<AssetInfoResponse>> getAssetInfo();

    CommonResponse<VersionInfo> getAppVersionInfo();

    CommonResponse<List<ExploreInfo>> getExploreInfo();

    CommonResponse<List<ExploreInfo>> searchExploreInfo(SearchRequest searchRequest);

    CommonResponse<List<BannerInfo>> getBannerInfo();

    CommonResponse<AppUpdateInfoResponse> getAppUpdateInfo();
}
