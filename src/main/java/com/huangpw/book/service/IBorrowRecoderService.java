package com.huangpw.book.service;

import com.huangpw.book.bean.BorrowRecoder;
import com.huangpw.sys.bean.SysUser;
import com.huangpw.sys.utils.PageUtils;

import java.util.List;

public interface IBorrowRecoderService {
    /**
     * 查询所有
     *@return
     */
    public List<BorrowRecoder> list(BorrowRecoder borrowRecoder);
    /**
     *分页查询的方法
     *@param pageUtils 分页数据
     *@return
     */
    public void listPage(PageUtils pageUtils, SysUser user);

    /**
     * 保存
     * @param borrowRecoder
     * @return
     */
    public int save(BorrowRecoder borrowRecoder);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public BorrowRecoder findById(int id);

    /**
     * 根据id更新
     * @param entity
     * @return
     */
    public int updateById(BorrowRecoder borrowRecoder);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public int deleteById(int id);

    /**
     * 查询总数
     * @param pageUtils
     * @return
     */
    public int count(PageUtils pageUtils, SysUser user);
}
