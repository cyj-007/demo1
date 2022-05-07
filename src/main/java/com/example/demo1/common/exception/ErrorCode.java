package com.example.demo1.common.exception;

import lombok.Data;

/**
 * @author cyj
 *
 * 异常封装枚举值
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String message;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
