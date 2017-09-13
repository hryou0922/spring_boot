package com.hry.spring.mybatis.mapper;

import com.hry.spring.mybatis.model.TestModel;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestModel record);

    int insertSelective(TestModel record);

    TestModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestModel record);

    int updateByPrimaryKey(TestModel record);
}