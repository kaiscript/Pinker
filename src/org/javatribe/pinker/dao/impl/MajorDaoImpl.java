package org.javatribe.pinker.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.MajorDao;
import org.javatribe.pinker.entity.Major;
import org.junit.Test;
import org.springframework.stereotype.Repository;

/**
 * @author kaiscript
 * 2015年8月24日 下午11:57:45
 */

@Repository(value="majorDaoImpl")
public class MajorDaoImpl extends BaseDaoImpl<Major> implements MajorDao {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Major> getMajorsByDepartmentId(int departmentId) {
		Criteria criteria =getSession().createCriteria(Major.class);
		criteria.add(Restrictions.eq("department.dept_id", departmentId));
		return (List<Major>) criteria.list();
	}
	
	@Override
	public  Major getMajorById(int majorId) {
		Criteria criteria=getSession().createCriteria(Major.class);
		criteria.add(Restrictions.eq("maj_id", majorId));
		return (Major)criteria.uniqueResult();
	}

	@Override
	public Major getMajorByName(String majorName) {
		Criteria criteria = getSession().createCriteria(Major.class);
		criteria.add(Restrictions.eq("maj_name", majorName));
		return (Major)criteria.uniqueResult();
	}
	
}
