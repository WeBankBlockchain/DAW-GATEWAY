package com.webank.wsdaw.gateway.service.wallet;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.config.SystemConfig;
import com.webank.wsdaw.gateway.constant.ApiConstant;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.service.face.ConfigService;
import com.webank.wsdaw.gateway.util.HttpLogUtil;
import com.webank.wsdaw.gateway.vo.request.config.discovery.SearchRequest;
import com.webank.wsdaw.gateway.vo.response.*;
import com.webank.wsdaw.gateway.vo.response.config.asset.AssetInfoResponse;
import com.webank.wsdaw.gateway.vo.response.config.discovery.BannerInfo;
import com.webank.wsdaw.gateway.vo.response.config.discovery.ExploreInfo;
import com.webank.wsdaw.gateway.vo.response.config.version.AppUpdateInfoResponse;
import com.webank.wsdaw.gateway.vo.response.config.version.AppVersionInfo;
import com.webank.wsdaw.gateway.vo.response.config.version.VersionInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {

    @Autowired private SystemConfig systemConfig;

    @Override
    public CommonResponse<List<AssetInfoResponse>> getAssetInfo() {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_GETASSETINFO)
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<List<AssetInfoResponse>> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<List<AssetInfoResponse>>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: getAssetInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<VersionInfo> getAppVersionInfo() {
        log.info(
                "request endpoint {}",
                systemConfig.getDawConfigUrl() + ApiConstant.DAW_CONFIG_GETAPPVERSION);
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_GETAPPVERSION)
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<AppVersionInfo> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<AppVersionInfo>>() {},
                            true);
            VersionInfo versionInfo = new VersionInfo();
            AppVersionInfo appVersionInfo = cr.getData();
            versionInfo
                    .setVersion(appVersionInfo.getCurrentVersion())
                    .setOpenFlag(appVersionInfo.isOpenFlag())
                    .setHkFlag(appVersionInfo.isHkFlag());
            return CommonResponse.success(versionInfo);
        } else {
            log.error("OnError: getAppVersionInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<List<ExploreInfo>> getExploreInfo() {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_GETEXPLOREINFO)
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<List<ExploreInfo>> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<List<ExploreInfo>>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: getExploreInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<List<ExploreInfo>> searchExploreInfo(SearchRequest request) {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_SEARCHEXPLOREINFO)
                        .body(JSONUtil.toJsonStr(request))
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<List<ExploreInfo>> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<List<ExploreInfo>>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: searchExploreInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<List<BannerInfo>> getBannerInfo() {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_GETBANNERINFO)
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<List<BannerInfo>> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<List<BannerInfo>>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: getExploreInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public CommonResponse<AppUpdateInfoResponse> getAppUpdateInfo() {
        HttpResponse resp =
                HttpRequest.post(
                                systemConfig.getDawConfigUrl()
                                        + ApiConstant.DAW_CONFIG_GETUPDATEINFO)
                        .execute();
        HttpLogUtil.printLogs(resp);

        if (resp.isOk()) {
            CommonResponse<AppUpdateInfoResponse> cr =
                    JSONUtil.toBean(
                            resp.body(),
                            new TypeReference<CommonResponse<AppUpdateInfoResponse>>() {},
                            true);
            return cr;
        } else {
            log.error("OnError: getAppUpdateInfo request failed , resp : {}", resp);
            return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
        }
    }
}
