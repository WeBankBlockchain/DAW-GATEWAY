package com.webank.wsdaw.gateway.vo.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SendTxRequest extends CommonRequest {
    @NotBlank private String signedTransactionData;
    @NotBlank private String address;
    @NotBlank private String code;
    @NotBlank private String nonce;
    @NotBlank private String network;
}
