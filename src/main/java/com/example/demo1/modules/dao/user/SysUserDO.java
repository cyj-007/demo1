package com.example.demo1.modules.dao.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo1.common.mybatisplus.core.dataobject.BaseDO;
import com.example.demo1.emun.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cyj
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysUserDO extends BaseDO {
    /**
     * id
     * */
    private Long id;

    /**
     * 用户名
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 姓名
     * */
    private String nickname;

    /**
     * 性别
     * */
    private Integer sex;

    /**
     * 手机号码
     * */
    private String mobile;

    /**
     * 状态
     */
    private Integer status;


}
