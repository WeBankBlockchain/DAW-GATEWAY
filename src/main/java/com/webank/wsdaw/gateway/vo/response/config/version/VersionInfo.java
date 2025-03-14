package com.webank.wsdaw.gateway.vo.response.config.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class VersionInfo {
    private String version;
    private boolean openFlag;
    private boolean hkFlag;
}
