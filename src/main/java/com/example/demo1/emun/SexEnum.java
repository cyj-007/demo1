package com.example.demo1.emun;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.example.demo1.common.exception.util.ServiceExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cyj
 *
 * 性别枚举
 */
@Getter
@AllArgsConstructor
public enum SexEnum implements IEnum<Integer> {
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer value;
    private String desc;

    /**
     * 数据库查询返回汉字
     * */
    @Override
    public String toString(){
        return this.desc;
    }

    /**
     * VO验证
     * */
    public static Integer addValue(String desc){
        if (SexEnum.MAN.getDesc().equals(desc)){
            return SexEnum.MAN.getValue();
        }
        if (SexEnum.WOMAN.getDesc().equals(desc)){
            return SexEnum.WOMAN.getValue();
        }
        throw ServiceExceptionUtil.exception("性别枚举类型错误");

    }

    /**
     * VO验证
     * */
    public static String addDesc(Integer desc){
        if (SexEnum.MAN.getDesc().equals(desc)){
            return SexEnum.MAN.getDesc();
        }
        if (SexEnum.WOMAN.getDesc().equals(desc)){
            return SexEnum.WOMAN.getDesc();
        }
        throw ServiceExceptionUtil.exception("性别枚举类型错误");

    }

}
