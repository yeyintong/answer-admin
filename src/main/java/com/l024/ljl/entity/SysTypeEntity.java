package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

/**
 * 题分类 类别
 */
@Data
@Entity
@Table(name ="sys_type")
@ApiModel(value="题类型")
public class SysTypeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column
    private String subjectType;
}
