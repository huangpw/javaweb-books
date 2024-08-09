package com.huangpw.book.bean;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowCard {
    private Integer id;
    private Integer userid;
    private Integer stuid;
    private String stuname;
    private Integer state;
    private Date starttime;
    private Date endtime;
}
