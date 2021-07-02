package com.l024.ljl.service;

import com.l024.ljl.entity.PageEntity;

import java.util.List;

/**
 * 基本的service方法封装接口
 * @param <T>
 */
public interface BaseService<T> {
    boolean add(T t);

    boolean del(long id);
    List<T> getAll();
    boolean update(T t);
    PageEntity<T> page(int page, int size);
}
