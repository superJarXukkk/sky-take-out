package com.sky.mapper;


import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where openid = #{openid}")
    User queryWithOpenid(String openid);


    void add(User user);


    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
