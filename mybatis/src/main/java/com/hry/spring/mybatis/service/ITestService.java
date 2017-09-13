package com.hry.spring.pagehelper.service;

import com.hry.spring.pagehelper.model.TestModel;
import com.hry.spring.pagehelper.qry.TestQry;

import java.util.List;

public interface ITestService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    List<TestModel> selectAll(TestQry qry);

    MyPage<TestModel> selectAllWithPage(TestQry qry);
}
