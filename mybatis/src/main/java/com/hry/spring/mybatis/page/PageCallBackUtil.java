package com.hry.spring.mybatis.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hry.spring.mybatis.qry.AbstractQry;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 分页的回调函数
 * Created by huangrongyou@yixin.im on 2017/9/6.
 */
public class PageCallBackUtil {

    /**
     * 封装公共PageHelper的操作
     * @param qry
     * @param callBack
     * @param <T>
     * @return
     */
    public static<T>  MyPage<T> selectRtnPage(AbstractQry qry, IPageHelperPageCallBack callBack){
        Assert.notNull(qry, "qry can't be null!");
        Assert.notNull(callBack, "callBack cant' be null!");
        setPageHelperStartPage(qry);

        List<T> list = callBack.select();
        // 分页时，实际返回的结果list类型是Page<E>
        if(!(list instanceof Page)){
            throw new RuntimeException("list must be 'com.github.pagehelper.Page', now is " + list.getClass().getCanonicalName());
        }
        MyPage<T> myPage = new MyPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        myPage.setTotalCount((int) pageInfo.getTotal());
        myPage.setRows(pageInfo.getList());
        return myPage;
    }



    /**
     * 封装公共PageHelper的操作
     * @param qry
     * @param callBack
     * @param <T>
     * @return
     */
    public static<T>  List<T> select(AbstractQry qry, IPageHelperPageCallBack callBack){
        // 设置分页信息
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize(),qry.getRequireTotalCount());
        return callBack.select();
    }

    /**
     * 设置PageHelper的startPage
     * @param qry
     */
    private static void setPageHelperStartPage(AbstractQry qry) {
        // 设置分页信息
        // pageNum
        Integer pageNum = qry.getPageNum();
        pageNum = pageNum == null? AbstractQry.DEFAULT_PAGENUM : pageNum;
        // pageSize
        Integer pageSize = qry.getPageSize();
        pageSize = pageSize == null ? AbstractQry.DEFAULT_PAGESIZE : pageSize;
        // requireTotalCount
        Boolean requireTotalCount = qry.getRequireTotalCount();
        requireTotalCount = requireTotalCount == null ? AbstractQry.DEFAULT_REQUIRETOTALCOUNT : requireTotalCount;
        PageHelper.startPage(pageNum, pageSize,requireTotalCount);
    }
}
