package com.apestore.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.apestore.entity.User;

public interface UserMapper {
	@Select("select * from user where id = #{id}")
	public User getUser(@Param("id") Long id);
}
