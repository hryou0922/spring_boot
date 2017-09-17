package com.hry.spring.mybatis.service.impl;

import com.hry.spring.mybatis.mapper.TestMapper;
import com.hry.spring.mybatis.model.TestModel;
import com.hry.spring.mybatis.page.IPageHelperPageCallBack;
import com.hry.spring.mybatis.page.MyPage;
import com.hry.spring.mybatis.page.PageCallBackUtil;
import com.hry.spring.mybatis.qry.TestQry;
import com.hry.spring.mybatis.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Primary
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
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TestModel> selectAll(TestQry qry) {
        if(qry == null){
            qry = new TestQry();
        }
        return PageCallBackUtil.select(qry, new IPageHelperPageCallBack() {
            @Override
            public List<TestModel> select() {
                return testMapper.selectAll();
            }
        });
    }

    @Override
    public MyPage<TestModel> selectAllWithPage(TestQry qry) {
        if(qry == null){
            qry = new TestQry();
        }
        MyPage<TestModel> myPage = PageCallBackUtil.selectRtnPage(qry, new IPageHelperPageCallBack() {
            @Override
            public List<TestModel> select() {
                return  testMapper.selectAll();
            }
        });
        return myPage;
    }
}
