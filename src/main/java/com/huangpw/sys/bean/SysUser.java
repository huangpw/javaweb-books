package com.huangpw.sys.bean;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户的实体对象
 */
@Data
public class SysUser {
    private  Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer roleId;
    private String rolename;
    private String img;
    private Date createTime;
    private Boolean isAdmin = false;
}
