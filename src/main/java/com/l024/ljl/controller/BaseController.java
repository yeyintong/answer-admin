package com.l024.ljl.controller;


import com.l024.ljl.vo.R;

import java.util.Map;

/**
 * 基本的controller的方法封装接口
 * @param <T>
 */
public interface BaseController<T> {
    R add(T t);
    R del(Long id);
    R update(T t);
    R getAll();
    R get(T t);
    R page(Map<String,Object> map);
}
