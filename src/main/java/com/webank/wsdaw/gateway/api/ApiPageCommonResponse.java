package com.webank.wsdaw.gateway.api;

import com.webank.wsdaw.gateway.vo.response.PageCommonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ApiPageCommonResponse<T> extends ApiCommonResponse<T> {
    protected String attachment;
    protected int totalCount;
    protected boolean success;

    public PageCommonResponse<T> toPageCommonResponse() {
        PageCommonResponse<T> commonResponse = new PageCommonResponse<>();
        commonResponse.setCode(super.code);
        commonResponse.setMsg(super.message);
        commonResponse.setData(super.data);
        commonResponse.setDebugMsg(this.attachment);
        commonResponse.setTotalCount(this.totalCount);
        return commonResponse;
    }
}
