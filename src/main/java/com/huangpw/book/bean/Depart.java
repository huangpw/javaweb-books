package com.huangpw.book.bean;

import lombok.Data;
import java.util.Date;

@Data
public class Depart {
    private Integer id;
    private String name;
    private String notes;
    private Date createTime;
}
