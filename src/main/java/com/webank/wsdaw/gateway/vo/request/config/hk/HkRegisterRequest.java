package com.webank.wsdaw.gateway.vo.request.config.hk;

import com.webank.wsdaw.gateway.vo.request.CommonRequest;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HkRegisterRequest extends CommonRequest {
    @NotBlank(message = "email cannot be null.")
    String email;

    @NotBlank(message = "first name cannot be null.")
    String firstName;

    @NotBlank(message = "last name cannot be null.")
    String lastName;

    @NotBlank(message = "company cannot be null.")
    String company;

    @NotBlank(message = "phone code cannot be null.")
    String phoneCode;

    @NotBlank(message = "phone number cannot be null.")
    String phoneNumber;

    @NotBlank(message = "code cannot be null.")
    String code;
}
