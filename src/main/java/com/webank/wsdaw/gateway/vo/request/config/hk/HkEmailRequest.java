package com.webank.wsdaw.gateway.vo.request.config.hk;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HkEmailRequest extends CommonRequest {
    @NotBlank(message = "email cannot be null.")
    String email;
}
