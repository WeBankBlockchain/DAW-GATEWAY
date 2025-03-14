package com.webank.wsdaw.gateway.vo.request.relay;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RelayTokenRequest {
    @NotBlank(message = "aud url不能为空.")
    private String aud;

    private int ttl = 60 * 60 * 24;
}
