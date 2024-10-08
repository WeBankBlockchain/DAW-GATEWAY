package com.webank.wsdaw.gateway.vo.request.config.hk;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VerifyCodeRequest extends CommonRequest {
    @NotBlank(message = "email cannot be null.")
    String email;

    @NotBlank(message = "code cannot be null.")
    String code;
}
