package com.northsunstrider.restful.entity;

/**
 * @Description: TODO
 * @author: North
 * @date: 2018年5月15日 下午5:33:47
 */
public class User extends IEntity {
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
