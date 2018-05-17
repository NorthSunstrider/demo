/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northsunstrider.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.northsunstrider.entity.IEntity;
import com.northsunstrider.util.HibernateUtil;

public class ServiceAdaptor implements IService {

	public ServiceAdaptor() {
		Logger log = Logger.getLogger("org.hibernate.SQL");
		log.setLevel(Level.SEVERE);
	}

	@SuppressWarnings("unused")
	private Session getCurrentSession() {
		SessionFactory sessionFact = HibernateUtil.getSessionFactory();
		Session session = sessionFact.getCurrentSession();
		return session;
	}

	private Session getSession() {
		SessionFactory sessionFact = null;
		Session session;
		try {
			sessionFact = HibernateUtil.getSessionFactory();
			session = sessionFact.openSession();
		} catch (Exception ex) {
			session = sessionFact.getCurrentSession();
			ex.printStackTrace();
		}
		return session;
	}

	@Override
	public void closeSession(Session session) {
		try {
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	SessionWrapper begin() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		return new SessionWrapper(session, tx);
	}

	void end(SessionWrapper session, boolean commit) {
		if (session != null) {
			try {
				if (commit)
					session.getTx().commit();
			} catch (Exception ex) {
				// ex.printStackTrace();
			}
			try {
				closeSession(session.getSession());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	void rollback(SessionWrapper sessionWrapper) {
		if (sessionWrapper != null && sessionWrapper.getTx() != null)
			sessionWrapper.getTx().rollback();
	}

	@Override
	public void save(IEntity bean) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.save(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public int getCount(String hql) {
		SessionWrapper sessionWrapper = null;
		try {
			sessionWrapper = begin();
			hql = "SELECT COUNT(*) " + hql.substring(hql.toLowerCase().indexOf("from"));
			Query query = sessionWrapper.getSession().createQuery(hql);
			return ((Number) query.iterate().next()).intValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			rollback(sessionWrapper);
			return 0;
		} finally {
			end(sessionWrapper, false);
		}
	}

	@Override
	public synchronized void update(IEntity bean) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.update(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void save(List<? extends IEntity> beanList) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			for (Object bean : beanList)
				session.save(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	@Override
	public synchronized void update(List<IEntity> beanList) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			for (Object bean : beanList)
				session.update(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public synchronized int update(Class table, String whereField, Object whereValue, String updateField, Object value)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();

			String hqlUpdate = "update " + table.getName() + " set " + updateField + "=:val1 where " + whereField
					+ "= :val2";
			Query query = session.createQuery(hqlUpdate);

			if (value instanceof Long)
				query.setLong("val1", ((Long) value).longValue());
			else if (value instanceof String)
				query.setString("val1", (String) value);
			else if (value instanceof Byte)
				query.setByte("val1", (Byte) value);
			else if (value instanceof Integer)
				query.setInteger("val1", (Integer) value);
			else if (value instanceof Boolean)
				query.setBoolean("val1", (Boolean) value);
			else if (value instanceof Timestamp)
				query.setTimestamp("val1", (Timestamp) value);
			else if (value instanceof Date)
				query.setDate("val1", (Date) value);
			else
				query.setEntity("val1", value);

			if (whereValue instanceof Long)
				query.setLong("val2", ((Long) whereValue).longValue());
			else if (whereValue instanceof String)
				query.setString("val2", (String) whereValue);
			else if (whereValue instanceof Boolean)
				query.setBoolean("val2", (Boolean) whereValue);
			else
				query.setEntity("val2", whereValue);

			int updateEntities = query.executeUpdate();
			tx.commit();
			return updateEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public synchronized int update(Class table, String whereField, Object whereValue, String updateField, Object value,
			String comparission) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();

			String hqlUpdate = "update " + table.getName() + " set " + updateField + "=:val1 where " + whereField
					+ comparission + " :val2";
			Query query = session.createQuery(hqlUpdate);

			if (value instanceof Long)
				query.setLong("val1", ((Long) value).longValue());
			else if (value instanceof String)
				query.setString("val1", (String) value);
			else if (value instanceof Byte)
				query.setByte("val1", (Byte) value);
			else if (value instanceof Integer)
				query.setInteger("val1", (Integer) value);
			else if (value instanceof Boolean)
				query.setBoolean("val1", (Boolean) value);
			else if (value instanceof Timestamp)
				query.setTimestamp("val1", (Timestamp) value);
			else if (value instanceof Date)
				query.setDate("val1", (Date) value);
			else
				query.setEntity("val1", value);

			if (whereValue instanceof Long)
				query.setLong("val2", ((Long) whereValue).longValue());
			else if (whereValue instanceof String)
				query.setString("val2", (String) whereValue);
			else if (whereValue instanceof Boolean)
				query.setBoolean("val2", (Boolean) whereValue);
			else if (whereValue instanceof Timestamp)
				query.setTimestamp("val2", (Timestamp) whereValue);
			else if (whereValue instanceof Date)
				query.setDate("val2", (Date) whereValue);
			else
				query.setEntity("val2", whereValue);

			int updateEntities = query.executeUpdate();
			tx.commit();
			return updateEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public synchronized int update(Class table, String whereFields[], Object whereValues[], String updateField,
			Object value) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			if (whereFields.length != whereValues.length)
				return -1;

			session = getSession();
			tx = session.beginTransaction();

			String hqlUpdate = "update " + table.getName() + " ";
			String strWhere = " where ";
			for (int i = 0; i < whereFields.length; i++) {
				strWhere = strWhere + whereFields[i] + " = :val" + i + " and ";
			}
			hqlUpdate = hqlUpdate + " set " + updateField + " = :val" + strWhere;
			hqlUpdate = hqlUpdate.substring(0, hqlUpdate.lastIndexOf(" and"));
			Query query = session.createQuery(hqlUpdate);

			if (value instanceof Long)
				query.setLong("val", ((Long) value).longValue());
			else if (value instanceof String)
				query.setString("val", (String) value);
			else if (value instanceof Byte)
				query.setByte("val", (Byte) value);
			else if (value instanceof Integer)
				query.setInteger("val", (Integer) value);
			else if (value instanceof Boolean)
				query.setBoolean("val", (Boolean) value);
			else if (value instanceof Timestamp)
				query.setTimestamp("val", (Timestamp) value);
			else if (value instanceof Date)
				query.setDate("val", (Date) value);
			else
				query.setEntity("val", value);

			for (int i = 0; i < whereValues.length; i++) {
				if (whereValues[i] instanceof Long)
					query.setLong("val" + i, ((Long) whereValues[i]).longValue());
				else if (whereValues[i] instanceof Integer)
					query.setLong("val" + i, ((Integer) whereValues[i]).intValue());
				else if (whereValues[i] instanceof String)
					query.setString("val" + i, (String) whereValues[i]);
				else if (whereValues[i] instanceof Boolean)
					query.setBoolean("val" + i, (Boolean) whereValues[i]);
				else if (whereValues[i] instanceof Timestamp)
					query.setTimestamp("val" + i, (Timestamp) whereValues[i]);
				else if (whereValues[i] instanceof Date)
					query.setDate("val" + i, (Date) whereValues[i]);
				else
					query.setEntity("val" + i, whereValues[i]);
			}

			int updateEntities = query.executeUpdate();
			tx.commit();
			return updateEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public synchronized int update(Class table, String whereField, Object whereValue, String updateFields[],
			Object updateValues[]) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			if (updateFields.length != updateValues.length)
				return -1;

			session = getSession();
			tx = session.beginTransaction();

			String strUpdate = "set ";

			for (int i = 0; i < updateFields.length - 1; i++) {
				strUpdate = strUpdate + updateFields[i] + "=:val" + i + ", ";
			}
			strUpdate = strUpdate + updateFields[updateFields.length - 1] + "=:val" + (updateFields.length - 1) + "";

			String hqlUpdate = "update " + table.getName() + " " + strUpdate + " where " + whereField + "=:val";
			Query query = session.createQuery(hqlUpdate);

			if (whereValue instanceof Long)
				query.setLong("val", ((Long) whereValue).longValue());
			else if (whereValue instanceof String)
				query.setString("val", (String) whereValue);
			else if (whereValue instanceof Byte)
				query.setByte("val", (Byte) whereValue);
			else if (whereValue instanceof Integer)
				query.setInteger("val", (Integer) whereValue);
			else if (whereValue instanceof Boolean)
				query.setBoolean("val", (Boolean) whereValue);
			else
				query.setEntity("val", whereValue);

			for (int i = 0; i < updateValues.length; i++) {
				if (updateValues[i] instanceof Long)
					query.setLong("val" + i, ((Long) updateValues[i]).longValue());
				else if (updateValues[i] instanceof String)
					query.setString("val" + i, (String) updateValues[i]);
				else if (updateValues[i] instanceof Byte)
					query.setByte("val" + i, (Byte) updateValues[i]);
				else if (updateValues[i] instanceof Integer)
					query.setInteger("val" + i, (Integer) updateValues[i]);
				else if (updateValues[i] instanceof Boolean)
					query.setBoolean("val" + i, (Boolean) updateValues[i]);
				else
					query.setEntity("val" + i, updateValues[i]);
			}

			int updateEntities = query.executeUpdate();
			tx.commit();
			return updateEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	// public Object find(String table,int pagesize,int pageno) throws
	// Exception{
	// SessionWrapper sw = null;
	// try {
	// sw = begin();
	// Criteria crt = sw.getSession().createCriteria(Class.forName(table));
	// return crt.list();
	// } catch (Exception e) {
	// throw e;
	// } finally {
	// end(sw, true);
	// }
	// }

	public Object find(Class table, String whereField, Object whereValue) throws Exception {
		Session session = null;
		try {
			session = getSession();
			String hql = "from " + table.getName() + " where " + whereField + "= :val";
			Query query = session.createQuery(hql);

			if (whereValue instanceof Long)
				query.setLong("val", ((Long) whereValue).longValue());
			else if (whereValue instanceof Integer)
				query.setInteger("val", ((Integer) whereValue).intValue());

			else if (whereValue instanceof String)
				query.setString("val", (String) whereValue);
			return query.list();
		} catch (Exception ex) {
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public Object findByID(Class table, Object whereValue) throws Exception {
		Session session = null;
		try {
			session = getSession();
			String hql = "from " + table.getName() + " where id" + " = :val";
			Query query = session.createQuery(hql);

			if (whereValue instanceof Long)
				query.setLong("val", ((Long) whereValue).longValue());
			else if (whereValue instanceof Integer)
				query.setInteger("val", ((Integer) whereValue).intValue());

			else if (whereValue instanceof String)
				query.setString("val", (String) whereValue);
			return query.uniqueResult();
		} catch (Exception ex) {
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	public synchronized int delete(Class table, String whereField, Object whereValue) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();

			String hqlDelete = "delete from " + table.getName() + " where " + whereField + "= :val";

			Query query = session.createQuery(hqlDelete);

			if (whereValue instanceof Long)
				query.setLong("val", ((Long) whereValue).longValue());
			else if (whereValue instanceof Integer)
				query.setInteger("val", ((Integer) whereValue).intValue());

			else if (whereValue instanceof String)
				query.setString("val", (String) whereValue);

			int deleteEntities = query.executeUpdate();
			tx.commit();
			return deleteEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {

			closeSession(session);
		}
	}

	public synchronized int delete(Class table, String whereFields[], Object whereValues[]) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			if (whereFields.length != whereValues.length)
				return -1;

			session = getSession();
			tx = session.beginTransaction();

			String hqlUpdate = "delete from " + table.getName() + " where ";
			for (int i = 0; i < whereFields.length; i++) {
				hqlUpdate = hqlUpdate + whereFields[i] + " = :val" + i + " and ";
			}
			hqlUpdate = hqlUpdate.substring(0, hqlUpdate.lastIndexOf(" and") + 1);
			Query query = session.createQuery(hqlUpdate);

			for (int i = 0; i < whereValues.length; i++) {
				if (whereValues[i] instanceof Long)
					query.setLong("val" + i, ((Long) whereValues[i]).longValue());
				else if (whereValues[i] instanceof Integer)
					query.setLong("val" + i, ((Integer) whereValues[i]).intValue());
				else if (whereValues[i] instanceof String)
					query.setString("val" + i, (String) whereValues[i]);
				else if (whereValues[i] instanceof Boolean)
					query.setBoolean("val" + i, (Boolean) whereValues[i]);
				else if (whereValues[i] instanceof Timestamp)
					query.setTimestamp("val" + i, (Timestamp) whereValues[i]);
				else if (whereValues[i] instanceof Date)
					query.setDate("val" + i, (Date) whereValues[i]);
				else
					query.setEntity("val" + i, whereValues[i]);
			}
			int updateEntities = query.executeUpdate();
			tx.commit();
			return updateEntities;
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void saveOrUpdate(List<IEntity> beanList) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			for (Object bean : beanList)
				session.saveOrUpdate(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}
	}

	@Override
	public synchronized void saveOrUpdate(IEntity bean) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}

	}

	@Override
	public synchronized void delete(IEntity bean) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.delete(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}

	}

	@Override
	public synchronized void delete(List<? extends IEntity> beans) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			for (Object bean : beans)
				session.delete(bean);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			closeSession(session);
		}

	}

	void orderBy(Criteria criteria, String sortOrder, String sortField) {
		try {
			if (sortOrder != null && !sortOrder.trim().isEmpty() && sortField != null && !sortField.trim().isEmpty()) {
				criteria.addOrder(sortOrder.equalsIgnoreCase("asc") ? Order.asc(sortField) : Order.desc(sortField));
			}
		} catch (Exception ex) {
			System.out.println("ServiceAdaptor.orderBy() " + ex.getCause());
		}
	}

	final class SessionWrapper {
		private Session session = null;
		private Transaction tx = null;

		public SessionWrapper(Session session, Transaction tx) {
			super();
			this.session = session;
			this.tx = tx;
		}

		public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}

		public Transaction getTx() {
			return tx;
		}

		public void setTx(Transaction tx) {
			this.tx = tx;
		}

	}

}
