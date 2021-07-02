package com.l024.ljl.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private long id;

    private String name;


    private String avator;

    private String phone;

    private int sex;

    private String token;

}
