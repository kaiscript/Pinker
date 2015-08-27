package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.dao.DepartmentDao;
import org.javatribe.pinker.entity.Department;
import org.javatribe.pinker.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaiscript
 * 2015年8月23日 下午4:29:29
 */

@Service("departmentServiceImpl")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource(name="departmentDaoImpl")
	DepartmentDao departmentDao;
	
	@Override
	public List<Department> getAllList() {
		return departmentDao.getAllList();
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		return departmentDao.getDepartmentById(departmentId);
	}
	
}
