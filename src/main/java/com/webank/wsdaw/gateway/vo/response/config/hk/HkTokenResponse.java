package com.webank.wsdaw.gateway.vo.response.config.hk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HkTokenResponse {
    private String token;
    private String firstName;
    private String lastName;
}
