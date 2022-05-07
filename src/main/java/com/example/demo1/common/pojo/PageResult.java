package com.example.demo1.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@ApiModel("分页结果")
@Data
public final class PageResult<T> implements Serializable {

//    @ApiModelProperty(value = "数据", required = true)
    private List<T> list;

//    @ApiModelProperty(value = "总量", required = true)
    private Integer total;

    public PageResult() {
    }

    public PageResult(List<T> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Integer total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0);
    }

}
