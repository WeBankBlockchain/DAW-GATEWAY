package com.webank.wsdaw.gateway.api;

import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.gateway.ServerApplicationTests;
import com.webank.wsdaw.gateway.service.face.ConfigService;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import com.webank.wsdaw.gateway.vo.response.config.version.AppUpdateInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConfigServiceTest extends ServerApplicationTests {

    @Autowired private ConfigService configService;

    @Test
    public void testGet() {
        CommonResponse<AppUpdateInfoResponse> res = configService.getAppUpdateInfo();
        log.info("getAppUpdateInfo res: {}", JSONUtil.toJsonPrettyStr(res));
    }
}
