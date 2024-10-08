package com.webank.wsdaw.gateway.vo.request.config.discovery;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchRequest extends CommonRequest {
    @NotBlank(message = "text不能为空.")
    private String text;
}
