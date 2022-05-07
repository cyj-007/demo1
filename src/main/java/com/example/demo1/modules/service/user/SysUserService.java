package com.example.demo1.modules.service.user;

import com.example.demo1.modules.controller.vo.LoginVO;

/**
 * @author cyj
 */
public interface SysUserService {

    /**
     * 登陆用户
     *
     * @param reqVO 登陆信息
     * @param userIp 用户 IP
     * @param userAgent 用户 UA
     * @return 身份令牌，使用 JWT 方式
     */
    String login(LoginVO reqVO, String userIp, String userAgent);

}
