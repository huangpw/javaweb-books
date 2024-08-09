package com.huangpw.book.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookType {
    private Integer id;
    private String name;
    private String notes;
    private Date createTime;
    private List<Book> books;
}
