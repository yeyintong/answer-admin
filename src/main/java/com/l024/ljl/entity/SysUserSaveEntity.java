package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.catalina.Manager;

import javax.persistence.*;

/**
 * 用户收藏
 */
@Data
@Entity
@Table(name ="sys_user_save")
@ApiModel(value="用户收藏")
public class SysUserSaveEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = SysUserEntity.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private SysUserEntity user;

    @OneToOne(targetEntity = SysSubjectEntity.class)
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private SysSubjectEntity subject;
}
