package com.l024.ljl.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sys_subject")
@ApiModel(value = "题目")
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

    // 题目类型
    @ManyToOne(targetEntity = SysTypeEntity.class)
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private SysTypeEntity type;

    // 选项
    @OneToMany(targetEntity=SysOptionEntity.class,fetch=FetchType.EAGER)
    @JoinColumn(name="subject_id",referencedColumnName="id")
    private List<SysOptionEntity> options;
}
