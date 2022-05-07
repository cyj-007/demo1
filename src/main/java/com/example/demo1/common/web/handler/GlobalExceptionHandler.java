package com.example.demo1.common.web.handler;

import com.example.demo1.common.pojo.CommonResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.demo1.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.example.demo1.common.pojo.CommonResult.error;

/**
 * @author cyj
 *
 * 全局捕获异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Valid注释的参数的验证失败时抛出异常
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        FieldError bindingResult = exception.getBindingResult().getFieldError();
        assert bindingResult != null;
        System.out.println(bindingResult.getDefaultMessage()+"handleMethodArgumentNotValidException");
        return error(BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", bindingResult.getDefaultMessage()));
    }

    /**
     * 全局的最后异常捕获
     * */
    @ExceptionHandler(Exception.class)
    public CommonResult<?> handleDefaultException(Exception exception) {
        System.out.println(exception);
        return error(BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", "其他错误"));
    }
}
