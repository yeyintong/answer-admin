package com.l024.ljl.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 系统用户
 */
@Data
@Entity
@Table(name ="sys_user")
@ApiModel(value="系统用户")
public class SysUserEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    //接受但是不序列化
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column
    private String avator;
    @Column
    private String phone;
    @Column
    private int sex;
    @Column
    private Timestamp createTime;
    @Transient
    private String token;
    @Column
    private int type;




}
