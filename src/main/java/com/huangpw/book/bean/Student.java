package com.huangpw.book.bean;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    private Integer id;
    private Integer account;
    private String stuno;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private String talephone;
    private String address;
    private String wechat;
    private Integer classId;
    private String classname;
    private Integer departId;
    private String departname;
    private Date createTime;

}
