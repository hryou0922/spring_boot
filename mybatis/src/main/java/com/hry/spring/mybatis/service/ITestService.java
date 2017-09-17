package com.hry.spring.mybatis.service;

import com.hry.spring.mybatis.model.TestModel;
import com.hry.spring.mybatis.page.MyPage;
import com.hry.spring.mybatis.qry.TestQry;

import java.util.List;

public interface ITestService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    List<TestModel> selectAll(TestQry qry);

    MyPage<TestModel> selectAllWithPage(TestQry qry);
}
