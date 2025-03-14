package com.webank.wsdaw.gateway.controller;

import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.service.face.ConfigService;
import com.webank.wsdaw.gateway.vo.request.config.discovery.SearchRequest;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.discovery.BannerInfo;
import com.webank.wsdaw.gateway.vo.response.config.discovery.ExploreInfo;
import com.webank.wsdaw.gateway.vo.response.config.version.VersionInfo;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired private ConfigService configService;
    @Autowired private SystemConfig systemConfig;

    @PostMapping("getAssetInfo")
    public CommonResponse getAssetInfo() throws Exception {
        return configService.getAssetInfo();
    }

    @PostMapping("getAppUpdateInfo")
    public CommonResponse getAppUpdateInfo() throws Exception {
        return configService.getAppUpdateInfo();
    }

    @PostMapping("getVersion")
    public CommonResponse<VersionInfo> getVersion() throws Exception {
        return configService.getAppVersionInfo();
    }

    @PostMapping("getExploreInfo")
    public CommonResponse<List<ExploreInfo>> getExploreInfo() throws Exception {
        return configService.getExploreInfo();
    }

    @PostMapping("searchExploreInfo")
    public CommonResponse<List<ExploreInfo>> searchExploreInfo(
            @RequestBody @Valid SearchRequest request) throws Exception {
        return configService.searchExploreInfo(request);
    }

    @PostMapping("getBannerInfo")
    public CommonResponse<List<BannerInfo>> getBannerInfo() throws Exception {
        return configService.getBannerInfo();
    }
}
