package com.northsunstrider.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.northsunstrider.entity.PasswordInfo;


public class SystemDataService extends ServiceAdaptor {
	public List<PasswordInfo> getPasswordInfos() {
		SessionWrapper sessionwrapper = null;
		try {
			sessionwrapper = begin();
			Criteria crt = sessionwrapper.getSession().createCriteria(PasswordInfo.class);
			crt.addOrder(Order.desc("id"));
			return crt.list();
		} catch (Exception e) {
			throw e;
		} finally {
			end(sessionwrapper, true);
		}
	}
}
