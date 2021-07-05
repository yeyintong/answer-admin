package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_option")
@ApiModel(value = "题目选项")
public class SysOptionEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column
    private String letter;

    @Column
    private String content;

    @Column
    private long subject_id;
}
