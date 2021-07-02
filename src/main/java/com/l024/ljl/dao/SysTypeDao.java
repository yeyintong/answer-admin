package com.l024.ljl.dao;

import com.l024.ljl.entity.SysTypeEntity;
import com.l024.ljl.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 类型操作
 */
public interface SysTypeDao extends JpaRepository<SysTypeEntity,Long> {

}
