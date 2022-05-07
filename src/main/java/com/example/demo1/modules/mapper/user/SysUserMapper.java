package com.example.demo1.modules.mapper.user;

import com.example.demo1.common.mybatisplus.core.mapper.BaseMapperX;
import com.example.demo1.modules.dao.user.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cyj
 *
 * 用户信息
 */
@Mapper
public interface SysUserMapper extends BaseMapperX<SysUserDO> {

}
