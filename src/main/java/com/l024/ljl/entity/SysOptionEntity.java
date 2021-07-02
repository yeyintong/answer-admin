package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

/**
 * 题分类 类别
 */
@Data
@Entity
@Table(name ="sys_option")
@ApiModel(value="题目选项")
public class SysOptionEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column
    private String index;
    @Column
    private String content;

    @Column
    private long subject_id;

//    //类型
//    @ManyToOne(targetEntity = SysSubjectEntity.class)
//    @JoinColumn(name = "subject_id",referencedColumnName = "id")
//    private SysSubjectEntity subject;
}
