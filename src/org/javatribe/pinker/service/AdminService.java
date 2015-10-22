package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Admin;
/**
 * @author kaiscript
 * 2015年10月14日 下午9:46:10
 */
public interface AdminService {
	/**
	 * 保存管理员实体
	 * @param student
	 * @return 是否保存成功
	 */
	public boolean save(Admin admin);
	
	/**
	 * 删除管理员实体
	 * @param student
	 * @return 是否删除成功
	 */
	public boolean delete(Admin admin);
	
	/**
	 * 更新管理员实体
	 * @param student
	 * @return 是否更新成功
	 */
	public boolean update(Admin admin);
	
	/**
	 * 根据id获取管理员实体
	 * @param id
	 * @return 对应id的学生实体
	 */
	public Admin getById(Integer id);
	
	/**
	 * 根据id集合获取管理员集合
	 * @param ids
	 * @return
	 */
	public List<Admin> getByIdSet(Integer[] ids);
	
	/**
	 * 获取所有管理员的集合
	 * @return
	 */
	public List<Admin> getAllList();
	
	/**
	 * 根据pager变量信息获得pager
	 * @param pager
	 * @return 分页变量
	 */
	public Pager findByPager(Pager pager);
	/**
	 * 根据用户名获取 管理员实体
	 * @param username
	 * @return 管理员实体
	 */
	public Admin getAdminByUsername(String username);
}
