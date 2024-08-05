package com.huangpw.sys.bean;

import lombok.Data;
import java.util.Date;

@Data
public class SysMenu {
    private Integer id;
    private String name;
    private String url;
    private Integer parentId;
    private int seq;
    private Date createTime;
    private boolean check = false;
}
