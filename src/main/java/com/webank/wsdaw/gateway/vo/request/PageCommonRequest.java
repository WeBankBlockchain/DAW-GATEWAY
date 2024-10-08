package com.webank.wsdaw.gateway.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PageCommonRequest extends CommonRequest {
    private int pageNo = 1;
    private int pageSize = 10;
}
