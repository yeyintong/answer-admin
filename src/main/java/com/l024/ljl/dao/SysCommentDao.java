package com.l024.ljl.dao;

import com.l024.ljl.entity.SysCommentEntity;
import com.l024.ljl.entity.SysUserSaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysCommentDao extends JpaRepository<SysCommentEntity,Long> {

    @Query(value = "select * from sys_comment where user_id = ?1",nativeQuery = true)
    List<SysCommentEntity> getUserComments(Long userId);

    @Query(value = "select * from sys_comment where subject_id = ?1",nativeQuery = true)
    List<SysCommentEntity> getSubjectComments(Long subjectId);

    @Transactional
    @Modifying
    @Query(value = "delete from sys_comment where subject_id = ?1", nativeQuery = true)
    void deleteBySubjectId(Long subjectId);

    



}
