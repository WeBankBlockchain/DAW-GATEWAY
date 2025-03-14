package com.webank.wsdaw.gateway.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author grayson
 * @description TODO
 * @date 2023-11-09 23:16
 */
@AllArgsConstructor
@Getter
public enum CodeEnum {
    // 0-request success
    TRANSACTION_SUCCESS(0, "操作成功"),

    // 1000-1999: params validate error
    REQUEST_PARAMS_ERROR(1001, "请求参数错误"),
    PASSWORD_ERROR(1002, "用户名或密码错误"),
    EMAIL_ERROR(1002, "邮件发送失败"),

    // 2000-2999: config

    // 3000-3999: kyc
    SIGN_NOT_PASS(3002, "签名验证不通过"),

    // 6000-6999: db error
    CONFIG_ERROR(6001, "数据库配置错误"),

    // 7000-7999: third service error
    // other error

    // 9000-9998: system error
    // 9999-unknown error
    UNKNOWN_ERROR(9999, "系统错误");

    private final int code;
    private final String msg;

    public static CodeEnum getCodeEnum(int code) {
        for (CodeEnum type : CodeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return UNKNOWN_ERROR;
    }
}
