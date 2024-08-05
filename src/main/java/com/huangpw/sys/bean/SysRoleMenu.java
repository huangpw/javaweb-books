package com.huangpw.sys.bean;

import lombok.Data;
import java.util.Date;

@Data
public class SysRoleMenu {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private Date createTime;
}
