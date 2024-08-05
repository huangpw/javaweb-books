package com.huangpw.sys.dao.impl;

import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.dao.IUserDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统用Dao的实现类
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public List<SysUser> list(SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_user where 1 = 1 ";
        if(user.getRoleId() != null && user.getRoleId() > 0){
            sql += " and role_id = " + user.getRoleId();
        }
        try {
            // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
            // 此处不会通过驼峰命名法 转换
//            List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
            List<SysUser> list = queryRunner.query(sql, new ResultSetHandler<List<SysUser>>() {
                @Override
                public List<SysUser> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysUser> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        SysUser user = new SysUser();
                        user.setId(resultSet.getInt("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(resultSet.getString("password"));
                        user.setNickname(resultSet.getString("nickname"));
                        user.setRoleId(resultSet.getInt("role_id"));
                        user.setRolename(resultSet.getString("rolename"));
                        user.setImg(resultSet.getString("img"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        list.add(user); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            });
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SysUser> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_user limit ?,?";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select * from sys_user where username like '%"+pageUtils.getKey()+"%' or nickname like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
            // 此处不会通过驼峰命名法 转换
//            List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
            List<SysUser> list = queryRunner.query(sql, new ResultSetHandler<List<SysUser>>() {
                @Override
                public List<SysUser> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysUser> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        SysUser user = new SysUser();
                        user.setId(resultSet.getInt("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(resultSet.getString("password"));
                        user.setNickname(resultSet.getString("nickname"));
                        user.setRoleId(resultSet.getInt("role_id"));
                        user.setRolename(resultSet.getString("rolename"));
                        user.setImg(resultSet.getString("img"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        list.add(user); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            },startNo, pageUtils.getPageSize());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from sys_user";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select count(1) from sys_user where username like '%"+pageUtils.getKey()+"%' or nickname like '%"+pageUtils.getKey()+"%'";
        }
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int save(SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into sys_user(username,password,nickname,img, role_id, rolename) values(?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getImg(), user.getRoleId(), user.getRolename());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public SysUser findById(Integer id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_user where id = ?";
        try {
            SysUser user = queryRunner.query(sql, new ResultSetHandler<SysUser>() {
                @Override
                public SysUser handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        SysUser user = new SysUser();
                        user.setId(resultSet.getInt("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(resultSet.getString("password"));
                        user.setNickname(resultSet.getString("nickname"));
                        user.setRoleId(resultSet.getInt("role_id"));
                        user.setRolename(resultSet.getString("rolename"));
                        user.setImg(resultSet.getString("img"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        return user; // 返回查询的结果
                    }
                    return null;
                }
            }, id);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update sys_user set username=?,password=?,nickname=?,img=?,role_id=?,rolename=? where id=?";
        try {
            return queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getImg(), user.getRoleId(), user.getRolename(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteById(Integer id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from sys_user where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public SysUser findByName(String username) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_user where username = ?";
        try {
            SysUser user = queryRunner.query(sql, new ResultSetHandler<SysUser>() {
                @Override
                public SysUser handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        // 每次循环一次 user 存储一条数据
                        SysUser user = new SysUser();
                        user.setId(resultSet.getInt("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(resultSet.getString("password"));
                        user.setNickname(resultSet.getString("nickname"));
                        user.setRoleId(resultSet.getInt("role_id"));
                        user.setRolename(resultSet.getString("rolename"));
                        user.setImg(resultSet.getString("img"));
                        user.setCreateTime(resultSet.getDate("create_time"));
                        return user; // 返回查询的结果
                    }
                    return null;
                }
            }, username);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
