package com.huangpw.sys.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageUtils {
    private String key; // 查询的关键字
    private int pageNum = 1; // 当前页码
    private int pageSize = 10; // 每页显示的条数
    private int totalCount; // 总条数
    private int totalPage; // 总页数
    private List<?> list; // 分页显示的数据
    private List<String> pageList; // 前端分页的页码列表

    /**
     * SQL分页中的 开始的位置
     *
     * @return
     */
    public int getStart() {
        return (this.getPageNum() - 1) * this.getPageSize();
    }

    /**
     * SQL分页中的 结束的位置
     *
     * @return
     */
    public int getEnd() {
        return this.getPageNum() * this.getPageSize();
    }

    public List<String> getPageList() {
        pageList = new ArrayList<>();
        totalPage = getTotalPage();
        // 获取总的页数
        if(totalPage < 7){
            // 总共没有7条记录
            for (int i = 1; i <= totalPage; i++) {
                pageList.add(i+"");
            }
        }else{
            // 总共超过7条记录
            if(pageNum == 1 || pageNum == 2 || pageNum == 3){
                pageList = Arrays.asList("1","2","3","...",totalPage+"");
            }else{
                if(pageNum != totalPage) {
                    pageList = Arrays.asList((pageNum-2)+"",(pageNum- 1)+"",pageNum+"", "...", totalPage+"");
                } else {
                    pageList = Arrays.asList((pageNum-4)+"",(pageNum- 3)+"",(pageNum- 2)+"", "...", totalPage+"");
                }

            }
        }
        return pageList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        // 总的记录数/每页显示的条数
        totalPage = (int) Math.ceil(((double) totalCount) / pageSize);
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
