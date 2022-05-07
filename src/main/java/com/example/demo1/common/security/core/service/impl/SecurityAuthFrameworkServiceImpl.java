package com.example.demo1.common.security.core.service.impl;

import com.example.demo1.common.redis.utils.RedisUtils;
import com.example.demo1.common.security.core.LoginUser;
import com.example.demo1.common.security.core.service.SecurityAuthFrameworkService;
import com.example.demo1.modules.dao.user.SysUserDO;
import com.example.demo1.modules.convert.user.UserConvert;
import com.example.demo1.modules.mapper.user.SysUserMapper;
import com.example.demo1.utils.json.JsonUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cyj
 */
@Service
public class SecurityAuthFrameworkServiceImpl implements SecurityAuthFrameworkService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public LoginUser verifyTokenAndRefresh(String token) {
        return JsonUtils.parseObject(redisUtils.get(token, true),LoginUser.class);
    }

    @Override
    public void logout(String token) {

    }

    /**
     * 账号密码校验
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO sysUserDO = sysUserMapper.selectOne("username", username);
        if (sysUserDO == null){
            throw new UsernameNotFoundException(username);
        }
        return UserConvert.INSTANCE.convert(sysUserDO);
    }
}
