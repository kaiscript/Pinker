package org.javatribe.pinker.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.AdminDao;
import org.javatribe.pinker.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository(value = "adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	@Override
	public Admin getAdminByUsername(String username) {
		Criteria criteria =getSession().createCriteria(Admin.class);
		criteria.add(Restrictions.eq("admin_username", username));
		
		return (Admin)criteria.uniqueResult();
	}
	
}
