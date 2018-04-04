package com.hry.spring.mybatis.mapper;

import com.hry.spring.mybatis.model.TestModel;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestModel record);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    List<TestModel> selectAll();


    int updateByPrimaryKeySelective(TestModel record);

    int updateByPrimaryKey(TestModel record);
}