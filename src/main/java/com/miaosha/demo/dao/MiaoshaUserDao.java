package com.miaosha.demo.dao;

import com.miaosha.demo.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {


    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);

    @Select("select * from miaosha_user where name = #{name}")
    public MiaoshaUser getByName(@Param("name") String  name);


    @Insert("insert into miaosha_user (id, nickname, password, salt, head)" +
            "values (#{id}, #{nickname}, #{password}, #{salt}, #{head})")
    public boolean addMiaoshaUser(MiaoshaUser miaoshaUser);


}
