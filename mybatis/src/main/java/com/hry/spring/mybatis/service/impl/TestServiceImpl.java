package com.hry.spring.pagehelper.service.impl;

import com.hry.spring.pagehelper.mapper.TestMapper;
import com.hry.spring.pagehelper.model.TestModel;
import com.hry.spring.pagehelper.qry.TestQry;
import com.hry.spring.pagehelper.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TestServiceImpl implements ITestService{
    @Autowired
    private TestMapper testMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        Assert.notNull(id, "id can't be null!");
        return testMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TestModel record) {
        Assert.notNull(record, "record can't be null!");
        return testMapper.insertSelective(record);
    }

    @Override
    public TestModel selectByPrimaryKey(Integer id) {
        Assert.notNull(id, "id can't be null!");
        return null;
    }

    @Override
    public MyPage<TestModel> selectAllWithPage(TestQry qry) {
        Assert.notNull(qry, "qry can't be null!");
        return null;
    }
}
