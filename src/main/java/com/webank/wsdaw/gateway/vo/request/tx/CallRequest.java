package com.webank.wsdaw.gateway.vo.request.tx;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CallRequest extends CommonRequest {
    @NotBlank private String from;
    @NotBlank private String to;
    @NotBlank private String data;
    @NotBlank private String network;
}
