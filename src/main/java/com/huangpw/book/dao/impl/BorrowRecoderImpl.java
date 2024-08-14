package com.huangpw.book.dao.impl;

import com.huangpw.book.bean.BorrowCard;
import com.huangpw.book.bean.BorrowRecoder;
import com.huangpw.book.dao.IBorrowRecoderDao;
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

public class BorrowRecoderImpl implements IBorrowRecoderDao {
    @Override
    public List<BorrowRecoder> list(BorrowRecoder borrowRecoder) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_recoder";
        try {
            // 保证实体类与数据库的字段编码一致
            return queryRunner.query(sql, new BeanListHandler<BorrowRecoder>(BorrowRecoder.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BorrowRecoder> listPage(PageUtils pageUtils, SysUser user) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_recoder where 1=1 ";

        if (!StringUtils.isEmpty(pageUtils.getKey())) {
            sql += " and stuname like '%" + pageUtils.getKey() + "%'";
        }
        if (user != null && !user.getIsAdmin()) {
            // 不是管理员
            sql += " and userid =" + user.getId();
        }
        sql += " limit ?,?";
        // 计算 分页开始的位置
        int startNo = pageUtils.getStart();
        try {
            // 保证实体类与数据库的字段编码一致
            return queryRunner.query(sql, new BeanListHandler<BorrowRecoder>(BorrowRecoder.class), startNo, pageUtils.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(BorrowRecoder borrowRecoder) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "insert into t_borrow_recoder(cardid,bookid,stuname,bookname,userid)values(?,?,?,?,?)";
        try {
            return queryRunner.update(sql, borrowRecoder.getCardid(), borrowRecoder.getBookid(), borrowRecoder.getStuname(), borrowRecoder.getBookname(), borrowRecoder.getUserid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public BorrowRecoder findById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "select * from t_borrow_recoder where id=?";
        try {
            // 保证实体类与数据库的字段编码一致
            List<BorrowRecoder> list = queryRunner.query(sql, new BeanListHandler<BorrowRecoder>(BorrowRecoder.class), id);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateById(BorrowRecoder borrowRecoder) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "update t_borrow_recoder set endtime = now() where id = ?";
        try {
            return queryRunner.update(sql, borrowRecoder.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int id) {
        QueryRunner queryRunner = MyDbUtils.getQueryRunner();
        String sql = "delete from t_borrow_recoder where id = ?";
        //String sql = "update t_borrow_recoder set state = 3 where id = ?";
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
        String sql = "select count(1) from t_borrow_recoder where 1 = 1 ";
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
