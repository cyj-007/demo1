package com.example.demo1.common.security.core.handler;

import cn.hutool.core.util.StrUtil;
import com.example.demo1.common.pojo.CommonResult;
import com.example.demo1.common.security.config.SecurityProperties;
import com.example.demo1.common.security.core.service.SecurityAuthFrameworkService;
import com.example.demo1.common.security.core.util.SecurityFrameworkUtils;
import com.example.demo1.utils.servlet.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义退出处理器
 *
 * @author cyj
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private SecurityAuthFrameworkService securityFrameworkService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 执行退出
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            securityFrameworkService.logout(token);
        }
        // 返回成功
        ServletUtils.writeJSON(response, CommonResult.success(null));
    }
}
