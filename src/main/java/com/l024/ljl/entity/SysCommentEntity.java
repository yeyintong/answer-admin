package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

/**
 * 用户评论
 */
@Data
@Entity
@Table(name ="sys_comment")
@ApiModel(value="用户评论")
public class SysCommentEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column
    private long commentTime;

    @Column
    private String comment;

    @OneToOne(targetEntity = SysUserEntity.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private SysUserEntity user;

    @OneToOne(targetEntity = SysSubjectEntity.class)
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private SysSubjectEntity subject;
}
