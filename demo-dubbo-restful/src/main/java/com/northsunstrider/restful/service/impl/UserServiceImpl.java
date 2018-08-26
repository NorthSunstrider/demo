package com.northsunstrider.restful.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.northsunstrider.restful.entity.User;
import com.northsunstrider.restful.service.UserService;

/**
 * @Description: TODO
 * @author: North
 * @d5日 下午5:37:14
 */
@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class UserServiceImpl implements UserService {

	@Override
	@GET
	@Path("{id:\\d+}")
	public User getUser(@PathParam("id") int uid) {
		User user = new User(""+uid, "root");
		return user;
	}

}
