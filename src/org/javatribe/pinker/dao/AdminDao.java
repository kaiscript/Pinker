package org.javatribe.pinker.dao;

import org.javatribe.pinker.entity.Admin;
/**
 * @author kaiscript
 * 2015年10月14日 下午9:46:00
 */
public interface AdminDao extends BaseDao<Admin> {
	
	public Admin getAdminByUsername(String username);
}
