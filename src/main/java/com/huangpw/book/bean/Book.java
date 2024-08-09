package com.huangpw.book.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private String img;
    private String notes;
    private Integer state;
    private String isbn;
    private Integer price;
    private Integer typeId;
    private String typename;
    private Date createTime;

}
