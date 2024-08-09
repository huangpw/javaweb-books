package com.huangpw.book.bean;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowRecoder {
    private Integer id;
    private Integer cardid;
    private Integer bookid;
    private Date starttime;
    private Date endtime;
    private String stuname;
    private String bookname;
    private Integer userid;
    private String img;
}
