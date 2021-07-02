package com.l024.ljl.dao;

import com.l024.ljl.entity.SysUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 系统用户操作
 */
public interface SysUserDao extends PagingAndSortingRepository<SysUserEntity,Long> {
    //根据编号来查询
    SysUserEntity querySysUserEntitiesByPhone(String phone);
    //根据用户名来查询
    SysUserEntity querySysUserEntitiesByName(String name);

    SysUserEntity querySysUserEntityByIdOrPhone(Long id,String phone);
}
