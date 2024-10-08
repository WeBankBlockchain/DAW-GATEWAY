package com.webank.wsdaw.gateway.vo.request.mne;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenerateUserKeysRequest extends CommonRequest {
    @NotBlank(message = "企微用户ID不能为空.")
    private String weworkUserId;
}
