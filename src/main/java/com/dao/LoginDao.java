package com.dao;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface LoginDao {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);
    User selectByName(String name);
    User checkPassword(@Param("name")String name,@Param("password")String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}