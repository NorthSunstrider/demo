package com.apestore.mapper;

import org.apache.ibatis.annotations.Select;

import com.apestore.entity.User;

public interface UserMapper {
	@Select("select * from user where id = #{id}")
	public User selectUserById(Long id);
}
