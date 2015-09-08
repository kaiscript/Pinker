package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Department;

/**
 * @author kaiscript
 * 2015年8月23日 下午4:29:47
 */
public interface DepartmentService{
	/**
	 * 获取所有系的集合
	 * @return
	 */
	public List<Department> getAllList();
	
	/**
	 * 根据id获取系实体
	 * @param departmentId
	 * @return
	 */
	public Department getDepartmentById(int departmentId);
}
