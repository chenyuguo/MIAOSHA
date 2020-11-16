package com.miaosha.demo.dao;

import com.miaosha.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id")int id);

    @Select("select * from user where name = #{name}")
    public User getByName(@Param("name") String name);

}
