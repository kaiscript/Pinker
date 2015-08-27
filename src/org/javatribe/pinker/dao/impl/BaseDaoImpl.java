package org.javatribe.pinker.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.BaseDao;

/**
 * BaseDaoImpl 定义DAO的通用操作的实现
 * 
 * @author kaiscript 2015年8月2日 下午6:16:02
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	public SessionFactory sessionFactory;
	protected Class<T> entityClass;

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(T entity) {
		try {
			getSession().save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			getSession().clear();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(T entity) {

		try {
			getSession().update(entity);
			getSession().flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Integer id) {
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIdSet(Integer[] ids) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.in("id", ids));
		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllList() {

		return getSession().createCriteria(entityClass).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager findByPager(Pager pager) {
		Criteria criteria = getSession().createCriteria(entityClass);

		// 获得此查询条件下的总纪录条数
		criteria.setProjection(Projections.rowCount());
		int totalCount = ((Long) criteria.uniqueResult()).intValue();
		pager.setTotalCount(totalCount);

		criteria.setProjection(null);

		pager.init();
		/**
		 * 根据分页增加需求待完善
		 */
		 criteria.setFirstResult((pager.getPageIndex()-1)*pager.getPageSize());
		 //从第几条记录开始
		 criteria.setMaxResults(pager.getPageSize()); //查询的条数
		pager.setDatas((List<T>) criteria.list());
		return pager;
	}


}
