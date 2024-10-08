package com.webank.wsdaw.gateway.vo.response.config.version;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AppVersionInfo {
    private String currentVersion;
    private boolean openFlag;
    private boolean hkFlag;
}
