package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * 题分类 类别
 */
@Data
@Entity
@Table(name ="sys_subject")
@ApiModel(value="题目")
public class SysSubjectEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String answer;

    @Column
    private String note;
    //类型
    //多对一  题目与类型
    @ManyToOne(targetEntity = SysTypeEntity.class)
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private SysTypeEntity type;

    //配置题目和选项 一对多关系
    @OneToMany(targetEntity=SysOptionEntity.class,fetch=FetchType.EAGER)
    @JoinColumn(name="subject_id",referencedColumnName="id")
//    @OneToMany(mappedBy="subject",fetch=FetchType.EAGER)
    private List<SysOptionEntity> options;//题目选项
}
