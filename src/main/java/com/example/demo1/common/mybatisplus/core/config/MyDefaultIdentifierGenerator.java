package com.example.demo1.common.mybatisplus.core.config;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;


/**
 *  自定义workid与datacenterid
 *
 * @author cyj
 */
@Component
public class MyDefaultIdentifierGenerator extends DefaultIdentifierGenerator {

    public MyDefaultIdentifierGenerator(){

        super(getWorkId(),getDataCenterId());
    }

    /**
     * workId使用IP生成
     *
     * @return workId
     */
    private static Long getWorkId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            System.out.println(hostAddress);
            int[] ints = StrUtil.splitToInt(hostAddress, ".");
            int sums = 0;
            for (int b : ints) {
                sums = sums + b;
            }
            return (long) (sums % 32);
        } catch (UnknownHostException e) {
            // 失败就随机
            return RandomUtil.randomLong(0, 31);
        }
    }

    /**
     * dataCenterId使用hostName生成
     *
     * @return dataCenterId
     */
    private static Long getDataCenterId() {
        try {
            //String hostName = InetAddress.getLocalHost().getHostName();
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            long[] ints = StrUtil.splitToLong(hostAddress, ".");
            long sums = 0;
            for (long i: ints) {
                sums = sums + i;
            }
            return  (sums % 32);
        } catch (Exception e) {
            // 失败就随机
            return RandomUtil.randomLong(0, 31);
        }
    }
}
