package com.webank.wsdaw.gateway.api;

import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApiCommonResponse<T> {
    protected int code;
    protected String message;
    protected String bizSeqNo;
    protected T data;

    public CommonResponse<T> toCommonResponse() {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setCode(code);
        commonResponse.setMsg(message);
        commonResponse.setData(data);
        return commonResponse;
    }
}
