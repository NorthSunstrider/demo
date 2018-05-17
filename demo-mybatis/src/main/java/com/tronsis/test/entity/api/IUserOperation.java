package com.tronsis.test.entity.api;

import java.util.List;

import com.tronsis.test.entity.User;

public interface IUserOperation {
	public User selectUserById(Long id);

	public User searchUser(String name, int age);

	public void saveUser(String name, int age);

	public List<User> listUser();

	public void updateUser(String password, String username);
}
