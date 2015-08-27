package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Department;

/**
 * @author kaiscript
 * 2015年8月23日 下午4:29:47
 */
public interface DepartmentService{
	public List<Department> getAllList();
	
	public Department getDepartmentById(int departmentName);
}
