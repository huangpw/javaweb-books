package com.huangpw.book.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Classes {
    private Integer id;
    private String name;
    private String notes;
    private Integer departId;
    private String departname;
    private Date createTime;
}
