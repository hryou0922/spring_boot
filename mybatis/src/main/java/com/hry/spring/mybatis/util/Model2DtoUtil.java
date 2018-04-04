package com.hry.spring.mybatis.util;

import com.hry.spring.mybatis.page.MyPage;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2017/9/6.
 */
public class Model2DtoUtil {

    /**
     * 将 MyPage<model> 修改为  MyPage<Dto>
     *
     * @param sourcePage
     * @param cls
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> MyPage<K> model2Dto(MyPage<T> sourcePage, Class<K> cls) {
        if(sourcePage == null){
            return null;
        }
        Assert.notNull(cls, "cls can't be null!");

        MyPage<K> dstPage = new MyPage<K>();
        dstPage.setTotalCount(sourcePage.getTotalCount());
        dstPage.setApiId(sourcePage.getApiId());
        dstPage.setMessage(sourcePage.getMessage());
        dstPage.setCode(sourcePage.getCode());

        // list
        List<T> sourceList = sourcePage.getRows();
        List<K> dstList = new ArrayList<K>();

        dealListModel2Dto(sourceList, cls, dstList);
        dstPage.setRows(dstList);
        return dstPage;
    }

    /**
     * 将 model 修改为 Dto
     *
     * @param source
     * @param cls
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> K model2Dto(T source, Class<K> cls) {
        if(source == null){
            return null;
        }
        Assert.notNull(cls, "cls can't be null!");

        try {
            K dst = cls.newInstance();
            CommonBeanUtils.copyProperties(source, dst);
            return dst;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new BeanCreationException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BeanCreationException(e.getMessage());
        }
    }

    /**
     * model -> dto
     * @param sourceList
     * @param cls
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K>  List<K> model2Dto(List<T> sourceList, Class<K> cls) {
        if(sourceList == null){
            return null;
        }
        Assert.notNull(cls, "cls can't be null!");

        // list
        List<K> dstList = new ArrayList<K>();

        dealListModel2Dto(sourceList, cls, dstList);
        return dstList;
    }

    private static <T, K> void dealListModel2Dto(List<T> sourceList, Class<K> cls, List<K> dstList) {
        for (T source : sourceList) {
            try {
                K dst = cls.newInstance();
                CommonBeanUtils.copyProperties(source, dst);
                dstList.add(dst);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new BeanCreationException(e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new BeanCreationException(e.getMessage());
            }
        }
    }

}
