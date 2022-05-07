package com.example.demo1.modules.service.user.impl;

import com.example.demo1.common.security.core.LoginUser;
import com.example.demo1.modules.controller.vo.LoginVO;
import com.example.demo1.modules.service.user.SysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static com.example.demo1.common.exception.util.ServiceExceptionUtil.exception;
import static com.example.demo1.emun.SysErrorCodeConstants.*;

/**
 * @author cyj
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginVO reqVO, String userIp, String userAgent) {
        // 使用账号密码，进行登陆。
        LoginUser loginUser = login0(reqVO.getUsername(), reqVO.getPassword());
//        loginUser.setRoleIds(getUserRoleIds(loginUser.getId())); // 获取用户角色列表

        // 缓存登陆用户到 Redis 中，返回 sessionId 编号
        return "登录成功";
    }

    private LoginUser login0(String username, String password) {
        // 用户验证
        Authentication authentication;
        try {
            // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
            // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException badCredentialsException) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        } catch (DisabledException disabledException) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        } catch (AuthenticationException authenticationException) {
            throw exception(AUTH_LOGIN_FAIL_UNKNOWN);
        }
        // 登陆成功
        Assert.notNull(authentication.getPrincipal(), "Principal 不会为空");
        return (LoginUser) authentication.getPrincipal();
    }
}
