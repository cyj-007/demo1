package com.example.demo1.modules.convert.user;

import com.example.demo1.common.security.core.LoginUser;
import com.example.demo1.modules.dao.user.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author cyj
 *
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    LoginUser convert(SysUserDO bean);
}
