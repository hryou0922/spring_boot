package com.hry.spring.pagehelper.mapper;

import com.hry.spring.pagehelper.model.TestModel;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestModel record);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestModel record);

    int updateByPrimaryKey(TestModel record);
}