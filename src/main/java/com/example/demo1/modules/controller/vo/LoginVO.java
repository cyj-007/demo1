package com.example.demo1.modules.controller.vo;

import com.example.demo1.emun.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author cyj
 */

@Data
public class LoginVO {
    /**
     * 账号
     * */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     * */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 性别
     * */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    public void setSex(String sex) {
        this.sex = SexEnum.addValue(sex);
    }
}
