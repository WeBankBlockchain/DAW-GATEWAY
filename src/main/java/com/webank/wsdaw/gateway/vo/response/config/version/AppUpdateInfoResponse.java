package com.webank.wsdaw.gateway.vo.response.config.version;

import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AppUpdateInfoResponse {

    private String version;

    private String androidUrl;

    private String iosUrl;

    private String introUrl;

    private Date createTime;
}
