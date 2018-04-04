package com.hry.spring.mvc.validation;

import com.hry.spring.mvc.exceptionhandling.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理验证失败的异常
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */

@RestControllerAdvice
public class BindExceptionHanlder {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = ex.getFieldError();
                    StringBuilder sb = new StringBuilder();
            sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                    .append(fieldError.getDefaultMessage());
        // 生成返回结果
        Result errorResult = new Result();
        errorResult.setCode(400);
        errorResult.setMessage(sb.toString());
        return errorResult;
    }
}
