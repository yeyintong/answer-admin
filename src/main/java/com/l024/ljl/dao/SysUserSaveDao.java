package com.l024.ljl.dao;

import com.l024.ljl.entity.SysTypeEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserSaveDao extends JpaRepository<SysUserSaveEntity,Long> {
    @Query(value = "select * from sys_user_save where user_id = ?1 and subject_id = ?2",nativeQuery = true)
    SysUserSaveEntity checkSave(Long userId,Long subjectId);

    @Query(value = "select * from sys_user_save where user_id = ?1",nativeQuery = true)
    List<SysUserSaveEntity> getUserSaves(Long userId);
}
