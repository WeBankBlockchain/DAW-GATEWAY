package com.webank.wsdaw.gateway.vo.response.config.discovery;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BannerInfo {
    private String name;
    private String url;
    private String img;
    private int priority;
}
