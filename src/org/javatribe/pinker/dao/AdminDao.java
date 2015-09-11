package org.javatribe.pinker.dao;

import org.javatribe.pinker.entity.Admin;

public interface AdminDao extends BaseDao<Admin> {
	
	public Admin getAdminByUsername(String username);
}
