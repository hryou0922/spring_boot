package com.hry.spring.pagehelper.util;

import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2017/9/6.
 */
public interface IPageHelperPageCallBack {
    <T> List<T> select();
}
