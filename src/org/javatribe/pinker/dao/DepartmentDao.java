package org.javatribe.pinker.dao;

import org.javatribe.pinker.entity.Department;

/**
 * @author kaiscript
 * 2015年8月23日 下午4:29:57
 */
public interface DepartmentDao extends BaseDao<Department> {
	/**
	 * 根据系id获取系实体
	 * @param departmentId
	 * @return 
	 */
	public Department getDepartmentById(int departmentId);
}
