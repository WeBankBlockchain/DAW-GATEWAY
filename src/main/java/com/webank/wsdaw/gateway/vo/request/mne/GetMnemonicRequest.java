package com.webank.wsdaw.gateway.vo.request.mne;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetMnemonicRequest extends CommonRequest {
    @NotBlank(message = "userId不能为空.")
    private String userId;
}
