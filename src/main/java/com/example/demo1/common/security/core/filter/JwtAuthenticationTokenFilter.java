package com.example.demo1.common.security.core.filter;

import cn.hutool.core.util.StrUtil;
import com.example.demo1.common.exception.util.ServiceExceptionUtil;
import com.example.demo1.common.pojo.CommonResult;
import com.example.demo1.common.security.config.SecurityProperties;
import com.example.demo1.common.security.core.LoginUser;
import com.example.demo1.common.security.core.service.impl.SecurityAuthFrameworkServiceImpl;
import com.example.demo1.common.security.core.util.SecurityFrameworkUtils;
import com.example.demo1.modules.dao.user.SysUserDO;
import com.example.demo1.modules.convert.user.UserConvert;
import com.example.demo1.modules.mapper.user.SysUserMapper;
import com.example.demo1.utils.servlet.ServletUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo1.common.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

/**
 * JWT 过滤器，验证 token 的有效性
 * 验证通过后，获得 {@link LoginUser} 信息，并加入到 Spring Security 上下文
 *
 * @author cyj
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private SecurityAuthFrameworkServiceImpl securityAuthFrameworkService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotEmpty(token)) {
            try {
                // 验证 token 有效性
                LoginUser loginUser = securityAuthFrameworkService.verifyTokenAndRefresh(token);
                if (loginUser == null) {
                    // 开启 token查无用户 添加admin用户
                    loginUser = this.mockLoginUser();
                }
                // 设置当前用户
                if (loginUser != null) {
                    SecurityFrameworkUtils.setLoginUser(loginUser, request);
                }
            } catch (Throwable ex) {
                System.out.println(ex.getMessage());
                ServletUtils.writeJSON(response, CommonResult.error(INTERNAL_SERVER_ERROR));
            }
        }
        // 继续过滤链
        chain.doFilter(request, response);
    }

    /**
     * 是否开始测试 开启不需要token默认admin用户
     * */
    public LoginUser mockLoginUser(){
        if (!securityProperties.getMockEnable()){
            return null;
        }
        SysUserDO sysUserDO = sysUserMapper.selectOne("username", securityProperties.getMockSecret());
        if (sysUserDO == null){
            throw ServiceExceptionUtil.exception("数据库没有admin用户");
        }
        return UserConvert.INSTANCE.convert(sysUserDO);
    }

}
