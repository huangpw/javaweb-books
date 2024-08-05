package com.huangpw.sys.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * 操作数据库的工具类
 */
public class MyDbUtils {

    // 定义的数据源
    private static MysqlDataSource dataSource;

    static {
        // 完成数据源的初始化操作
        dataSource = new MysqlDataSource();
        // 注意默认安装的数据的端口是 3306 这块需要结构自己数据库的情况调整
        dataSource.setURL("jdbc:mysql://8.140.200.150:3306/javaweb_books?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root"); // 数据库的用户名
        dataSource.setPassword("mysql_HtsefT"); // 数据库的密码
    }

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
