package com.huangpw.sys.dao.impl;

import com.huangpw.sys.bean.SysMenu;
import com.huangpw.sys.bean.SysRole;
import com.huangpw.sys.dao.IMenuDao;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements IMenuDao {
    @Override
    public List<SysMenu> list(SysMenu menu) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu order by seq";
        try {
            List<SysMenu> list = queryRunner.query(sql, new ResultSetHandler<List<SysMenu>>() {
                @Override
                public List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysMenu> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysMenu menu = new SysMenu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setUrl(resultSet.getString("url"));
                        menu.setParentId(resultSet.getInt("parent_id"));
                        menu.setSeq(resultSet.getInt("seq"));
                        menu.setCreateTime(resultSet.getDate("create_time"));
                        list.add(menu); // 把查询的记录封装到了集合容器中
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
    public List<SysMenu> listPage(PageUtils pageUtils) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu limit ?,?";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select * from sys_role where parent_id = -1 and name like '%"+pageUtils.getKey()+"%' limit ?,?";
        }
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            // BeanListHandler 会自动的帮助我们完成字段和属性的映射。前提是属性和字段完全一直
            // 此处不会通过驼峰命名法 转换
//            List<SysUser> list = queryRunner.query(sql, new BeanListHandler<SysUser>(SysUser.class));
            List<SysMenu> list = queryRunner.query(sql, new ResultSetHandler<List<SysMenu>>() {
                @Override
                public List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysMenu> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysMenu menu = new SysMenu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setUrl(resultSet.getString("url"));
                        menu.setParentId(resultSet.getInt("parent_id"));
                        menu.setSeq(resultSet.getInt("seq"));
                        menu.setCreateTime(resultSet.getDate("create_time"));
                        list.add(menu); // 把查询的记录封装到了集合容器中
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
    public int save(SysMenu menu) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into sys_menu(name, url, parent_id, seq) values(?,?,?,?)";
        try {
            return queryRunner.update(sql, menu.getName(), menu.getUrl(), menu.getParentId(), menu.getSeq());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public SysMenu findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu where id = ?";
        try {
            SysMenu role = queryRunner.query(sql, new ResultSetHandler<SysMenu>() {
                @Override
                public SysMenu handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    if (resultSet.next()) {
                        SysMenu menu = new SysMenu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setUrl(resultSet.getString("url"));
                        menu.setParentId(resultSet.getInt("parent_id"));
                        menu.setSeq(resultSet.getInt("seq"));
                        menu.setCreateTime(resultSet.getDate("create_time"));
                        return menu;
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
    public int updateById(SysMenu menu) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update sys_menu set name=?,url=?,parent_id=?,seq=? where id=?";
        try {
            return queryRunner.update(sql, menu.getName(), menu.getUrl(), menu.getParentId(), menu.getSeq(), menu.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from sys_menu where id = ?";
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
        String sql = "select count(1) from sys_menu";
        if(!StringUtils.isEmpty(pageUtils.getKey())){
            // 需要带条件查询
            sql = "select count(1) from sys_menu where parent_id = -1 and name like '%"+pageUtils.getKey()+"%'";
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
    public List<SysMenu> getAllParent() {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu where parent_id = -1 order by seq";
        try {
            List<SysMenu> list = queryRunner.query(sql, new ResultSetHandler<List<SysMenu>>() {
                @Override
                public List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysMenu> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysMenu menu = new SysMenu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setUrl(resultSet.getString("url"));
                        menu.setParentId(resultSet.getInt("parent_id"));
                        menu.setSeq(resultSet.getInt("seq"));
                        menu.setCreateTime(resultSet.getDate("create_time"));
                        list.add(menu); // 把查询的记录封装到了集合容器中
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
    public boolean isDispatcher(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from sys_role_menu where menu_id = ?"; // 查询当前菜单是否被分配过
        try {
             return queryRunner.query(sql, new ResultSetHandler<Boolean>() {
                @Override
                public Boolean handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1) > 0;
                }
            }, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean haveSubMenu(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from sys_menu where parent_id = ?"; // 查询当前菜单是否有子菜单
        try {
            return queryRunner.query(sql, new ResultSetHandler<Boolean>() {
                @Override
                public Boolean handle(ResultSet resultSet) throws SQLException {
                    resultSet.next();
                    return resultSet.getInt(1) > 0;
                }
            }, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SysMenu> findMenuByRoleId(Integer roleId) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from sys_menu where id in ( select menu_id from sys_role_menu where role_id = ? ) order by seq";
        try {
            List<SysMenu> list = queryRunner.query(sql, new ResultSetHandler<List<SysMenu>>() {
                @Override
                public List<SysMenu> handle(ResultSet resultSet) throws SQLException {
                    // 存储返回结果的容器
                    List<SysMenu> list = new ArrayList<>();
                    while (resultSet.next()) {
                        // 每次循环一次 role 存储一条数据
                        SysMenu menu = new SysMenu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setUrl(resultSet.getString("url"));
                        menu.setParentId(resultSet.getInt("parent_id"));
                        menu.setSeq(resultSet.getInt("seq"));
                        menu.setCreateTime(resultSet.getDate("create_time"));
                        list.add(menu); // 把查询的记录封装到了集合容器中
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
