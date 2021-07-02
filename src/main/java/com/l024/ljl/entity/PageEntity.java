package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * 分页数据
 * @param <T>
 */
@Data
@ApiModel(value="分页数据")
public class PageEntity<T>{
    private long totalElements;//总记录数
    private int currentPage;//当前页数
    private int totalPage;//总页数
    private List<T> content;
}
