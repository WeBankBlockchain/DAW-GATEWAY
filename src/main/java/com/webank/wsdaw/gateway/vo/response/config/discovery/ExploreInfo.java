package com.webank.wsdaw.gateway.vo.response.config.discovery;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ExploreInfo {
    private String name;
    private String title;
    private String url;
    private String icon;
    private int priority;
}
