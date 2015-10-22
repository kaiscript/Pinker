package org.javatribe.pinker.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.DepartmentDao;
import org.javatribe.pinker.entity.Department;
import org.springframework.stereotype.Repository;

/**
 * @author kaiscript
 * 2015年8月23日 下午4:29:10
 */

@Repository(value="departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public Department getDepartmentById(int departmentId) {
		Criteria criteria = getSession().createCriteria(Department.class);
		criteria.add(Restrictions.eq("dept_id", departmentId));
		return (Department) criteria.uniqueResult();
	}

	@Override
	public Department getDepartmentByName(String departmentName) {
		Criteria criteria = getSession().createCriteria(Department.class);
		criteria.add(Restrictions.eq("dept_name", departmentName));
		return (Department)criteria.uniqueResult();
	}

}
