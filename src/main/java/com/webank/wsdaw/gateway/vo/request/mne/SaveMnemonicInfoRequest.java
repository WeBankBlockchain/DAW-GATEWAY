package com.webank.wsdaw.gateway.vo.request.mne;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class SaveMnemonicInfoRequest extends CommonRequest {

    @NotBlank(message = "userId不能为空.")
    private String userId;

    private int keyType;

    @NotBlank(message = "encryptMn.")
    private String encryptMn;
}
