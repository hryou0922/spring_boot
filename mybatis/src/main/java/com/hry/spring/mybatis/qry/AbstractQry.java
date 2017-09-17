package com.hry.spring.mybatis.qry;

/**
 * Created by Administrator on 2017/9/6.
 * 定义基本的分页字段
 */
public class AbstractQry implements Qry {
    public static final int DEFAULT_PAGENUM = 1;
    public static final int DEFAULT_PAGESIZE = 1;
    public static final boolean DEFAULT_REQUIRETOTALCOUNT = false;

    private String id;
    private Integer pageNum = 1;// 第几页，首页为1
    private Integer pageSize = 10;// 每页记录条数
    private Boolean requireTotalCount = Boolean.FALSE;// 是否需要记录总数

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getRequireTotalCount() {
        return requireTotalCount;
    }

    public void setRequireTotalCount(Boolean requireTotalCount) {
        this.requireTotalCount = requireTotalCount;
    }
}
