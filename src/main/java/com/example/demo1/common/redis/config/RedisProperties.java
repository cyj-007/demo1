package com.example.demo1.common.redis.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author cyj
 * redis配置
 *
 * 用户id：token（uuid）+AES加密
 * 验证码：AES加密
 * */
public class RedisProperties {
    /**
     * 构建 随机生成密钥
     * */
    public static final AES AES = SecureUtil.aes(SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded());

    /**
     * redis存储时间 验证码
     * */
    public static final long REDIS_ID_TIME = 5*60;

    /**
     * 获取主键附加名称 加密 AES全称高级加密标准
     * */
    public static final String ID_NAME = AES.encryptHex(AES.encrypt("cyjyyds"));

    /**
     * 账号时间
     * */
    public static final long REDIS_ID_USER_TIME = 60 * 60;

}
