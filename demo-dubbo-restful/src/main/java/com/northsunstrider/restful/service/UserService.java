package com.northsunstrider.restful.service;

import org.springframework.stereotype.Service;

import com.northsunstrider.restful.entity.User;

/** 
 * @Description: TODO
 * @author: North
 * @date: 2018年5月15日 下午5:32:54  
 */
@Service
public interface UserService {
	public User getUser(int uid);
}
