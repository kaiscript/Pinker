package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Major;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:29
 */
public interface MajorDao extends BaseDao<Major> {
	
	public List<Major> getMajorsByDepartmentId(int departmentId);
	
	public Major getMajorById(int majorId);
}
