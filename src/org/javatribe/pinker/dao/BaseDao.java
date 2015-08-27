package org.javatribe.pinker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.common.Pager;

/**
 * BaseDao 定义DAO的通用操作 
 * @author kaiscript
 * 2015年8月2日 下午6:16:08
 */
public interface BaseDao<T> {
	/**
	 * 向数据库写入记录的方法
	 * @param entity - 要操作的实体类
	 * @return 操作是否成功的标志值
	 */
	public boolean save(T entity);
	/**
	 * 向数据库删除记录的方法
	 * @param entity - 要操作的实体类
	 * @return 操作是否成功的标志值
	 */
	public boolean delete(T entity);
	/**
	 * 向数据库更新记录的方法
	 * @param entity - 要操作的实体类
	 * @return 操作是否成功的标志值
	 */
	public boolean update(T entity);
	/**
	 * 根据id获得实体类
	 * @param id
	 * @return 返回实体对象
	 */
	public T get(Integer id);
	/**
	 * 得到指定一批Id的实体记录
	 * @param ids
	 * @return 实体记录列表
	 */
	public List<T> getByIdSet(Integer[] ids);
	/**
	 * 获得所有实体记录的方法
	 * @return 数据库表中所有的记录
	 */
	public List<T> getAllList();
	/**
	 * 根据分页查找记录的方法
	 * @param pager - 分页信息的实体类
	 * @return 分页的实体类
	 */
	public Pager findByPager(Pager pager);
	/**
	 * 获取会话工厂的方法
	 * @return hibernate的会话工厂
	 */
	
	public SessionFactory getSessionFactory();
}
