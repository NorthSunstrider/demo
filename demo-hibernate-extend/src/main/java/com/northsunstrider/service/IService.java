/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.northsunstrider.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.northsunstrider.entity.IEntity;

public interface IService extends Serializable {
	public void closeSession(Session session);

	public void save(IEntity obj) throws Exception;

	public void update(IEntity obj) throws Exception;

	public void delete(IEntity obj) throws Exception;

	public void saveOrUpdate(IEntity bean) throws Exception;

	public void update(List<IEntity> beanList) throws Exception;

	public void saveOrUpdate(List<IEntity> beanList) throws Exception;

	void delete(List<? extends IEntity> beans) throws Exception;

	void save(List<? extends IEntity> beanList) throws Exception;
}
