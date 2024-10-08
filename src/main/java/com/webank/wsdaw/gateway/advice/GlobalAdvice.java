package com.webank.wsdaw.gateway.advice;

import cn.hutool.extra.mail.MailException;
import com.webank.wsdaw.gateway.enums.CodeEnum;
import com.webank.wsdaw.gateway.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error("methodArgumentNotValidException exception", ex);
        BindingResult bindingResult = ex.getBindingResult();
        return ResponseEntity.ok(
                CommonResponse.error(
                        CodeEnum.REQUEST_PARAMS_ERROR.getCode(),
                        bindingResult.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<CommonResponse> mailException(MailException ex) {
        log.error("mail exception", ex);
        return ResponseEntity.ok(
                CommonResponse.error(CodeEnum.EMAIL_ERROR.getCode(), ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<CommonResponse> unhandledException(Exception ex) {
        log.error("ONError: unhandled exception", ex);
        return ResponseEntity.ok(CommonResponse.error(CodeEnum.UNKNOWN_ERROR));
    }
}
