package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.dao.IBorrowCardDao;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.MyDbUtils;
import com.huangpw.sys.utils.PageUtils;
import com.huangpw.sys.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowCardDaoImpl implements IBorrowCardDao {
    @Override
    public List<BorrowCard> list(BorrowCard borrowCard) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_card";
        try {
            // 保证实体类与数据库的字段编码一致
            return queryRunner.query(sql, new BeanListHandler<BorrowCard>(BorrowCard.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BorrowCard> listPage(PageUtils pageUtils, SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_card where 1=1 ";

        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql += " and stuname like '%" + pageUtils.getKey() + "%'";
        }
        if(user != null && !user.getIsAdmin()) {
            // 不是管理员
            sql += " and userid =" + user.getId();
        }
        sql += " limit ?,?";
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            // 保证实体类与数据库的字段编码一致
            return queryRunner.query(sql, new BeanListHandler<BorrowCard>(BorrowCard.class), startNo, pageUtils.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(BorrowCard borrowCard) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_borrow_card(userid,stuid,stuname,state,starttime,endtime)values(?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, borrowCard.getUserid(), borrowCard.getStuid(), borrowCard.getStuname(), borrowCard.getState(), borrowCard.getStarttime(), borrowCard.getEndtime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public BorrowCard findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_card where id=?";
        try {
            // 保证实体类与数据库的字段编码一致
            List<BorrowCard> list = queryRunner.query(sql, new BeanListHandler<BorrowCard>(BorrowCard.class), id);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(BorrowCard borrowCard) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_borrow_card set userid=?,stuid=?,stuname=?,state=?,starttime=?,endtime=? where id = ?";
        try {
            return queryRunner.update(sql, borrowCard.getUserid(), borrowCard.getStuid(), borrowCard.getStuname(), borrowCard.getState(), borrowCard.getStarttime(), borrowCard.getEndtime(), borrowCard.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        //String sql = "delete from t_borrow_card where id = ?";
        String sql = "update t_borrow_card set state = 3 where id = ?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int count(PageUtils pageUtils, SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select count(1) from t_borrow_card where 1 = 1 ";
        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql += " and stuname like '%" + pageUtils.getKey() + "%'";
        }
        if(user != null && !user.getIsAdmin()) {
            // 不是管理员
            sql += " and userid =" + user.getId();
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
}
