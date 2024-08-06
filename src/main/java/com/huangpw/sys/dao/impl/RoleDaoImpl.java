package com.huangpw.sys.dao.impl;

import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.bean.SysRoleMenu;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.dao.IRoleDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements IRoleDao {
    @Override
    public List<SysRole> list(SysRole role) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role where 1 = 1 ";
        if(role != null){
            if(!StringUtils.isEmpty(role.getName())) {
                sql += " and name = '" + role.getName() + "'";
            }
        }
        try {
            List<SysRole> list = queryRunner.query(sql, new ResultSetHandler<List<SysRole>>() {
                @Override
                public List<SysRole> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysRole> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysRole role = new SysRole();
                        role.setId(resultSet.getInt("id"));
                        role.setName(resultSet.getString("name"));
                        role.setNotes(resultSet.getString("notes"));
                        role.setCreateTime(resultSet.getDate("create_time"));
                        list.add(role); // 把查询的记录封装到了集合容器中
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
    public List<SysRole> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role limit ?,?";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select * from sys_role where name like '%"+pageUtils.getKey()+"%' or notes like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
            // 此处不会通过驼峰命名法 转换
//            List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
            List<SysRole> list = queryRunner.query(sql, new ResultSetHandler<List<SysRole>>() {
                @Override
                public List<SysRole> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysRole> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysRole role = new SysRole();
                        role.setId(resultSet.getInt("id"));
                        role.setName(resultSet.getString("name"));
                        role.setNotes(resultSet.getString("notes"));
                        role.setCreateTime(resultSet.getDate("create_time"));
                        list.add(role); // 把查询的记录封装到了集合容器中
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
    public int save(SysRole role) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into sys_role(name,notes) values(?,?)";
        try {
            return queryRunner.update(sql, role.getName(), role.getNotes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public SysRole findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role where id = ?";
        try {
             SysRole role = queryRunner.query(sql, new ResultSetHandler<SysRole>() {
                @Override
                public SysRole handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        SysRole role = new SysRole();
                        role.setId(resultSet.getInt("id"));
                        role.setName(resultSet.getString("name"));
                        role.setNotes(resultSet.getString("notes"));
                        role.setCreateTime(resultSet.getDate("create_time"));
                        return role;
                    }
                    return null;
                }
            }, id);
             return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(SysRole role) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update sys_role set name=?,notes=? where id=?";
        try {
            return queryRunner.update(sql, role.getName(), role.getNotes(), role.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from sys_role where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int count(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from sys_role";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select count(1) from sys_role where name like '%"+pageUtils.getKey()+"%' or notes like '%"+pageUtils.getKey()+"%'";
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
    public void deleteMenuByRoleId(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from sys_role_menu where role_id = ?";
        try {
            queryRunner.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDispatcherMenu(int roleId, int menuId) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into sys_role_menu(role_id,menu_id) values(?,?)";
        try {
            queryRunner.update(sql, roleId, menuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SysRoleMenu> queryByRoleId(int roleId) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_role_menu where role_id=?";
        try {
            List<SysRoleMenu> list = queryRunner.query(sql, new ResultSetHandler<List<SysRoleMenu>>() {
                @Override
                public List<SysRoleMenu> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysRoleMenu> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysRoleMenu roleMenu = new SysRoleMenu();
                        roleMenu.setId(resultSet.getInt("id"));
                        roleMenu.setRoleId(resultSet.getInt("role_id"));
                        roleMenu.setMenuId(resultSet.getInt("menu_id"));
                        roleMenu.setCreateTime(resultSet.getDate("create_time"));
                        list.add(roleMenu); // 把查询的记录封装到了集合容器中
                    }
                    return list; // 返回查询的结果
                }
            }, roleId);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
