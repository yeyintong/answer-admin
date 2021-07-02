package com.l024.ljl.dao;

import com.l024.ljl.entity.SysSubjectEntity;
import com.l024.ljl.entity.SysTypeEntity;
import com.l024.ljl.entity.SysUserEntity;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//题目
public interface SysSubjectDao extends PagingAndSortingRepository<SysSubjectEntity,Long> {

    @Query(value = "select * from sys_subject ss LEFT JOIN sys_type st on ss.type_id = st.id LEFT JOIN sys_option so on ss.id = so.subject_id where st.id=?1",nativeQuery = true)
    List<SysSubjectEntity> findSysSubjectEntitiesByTypeId(long typeId);


    @Query(value = "select * from sys_subject ss LEFT JOIN sys_type st on ss.type_id = st.id LEFT JOIN sys_option so on ss.id = so.subject_id where ss.title like %?1%",nativeQuery = true)
    List<SysSubjectEntity> findSysSubjectEntitiesByTitle(String name);



}
