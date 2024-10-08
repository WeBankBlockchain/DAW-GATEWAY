package com.webank.wsdaw.gateway.vo.request.tx;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressRequest extends CommonRequest {
    @NotBlank(message = "address不能为空.")
    private String address;
}
