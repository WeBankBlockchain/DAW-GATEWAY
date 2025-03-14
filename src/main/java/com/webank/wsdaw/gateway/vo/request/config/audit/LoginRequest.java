package com.webank.wsdaw.gateway.vo.request.config.audit;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends CommonRequest {
    @NotBlank(message = "app ID不能为空.")
    private String appId;

    @NotBlank(message = "用户ID不能为空.")
    private String userId;

    @NotBlank(message = "password不能为空.")
    private String password;
}
