package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Major;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:29
 */
public interface MajorDao extends BaseDao<Major> {
	/**
	 * 根据系id获取对应系的专业集合
	 * @param departmentId
	 * @return 对应系的专业集合
	 */
	public List<Major> getMajorsByDepartmentId(int departmentId);
	
	/**
	 * 根据专业id获取专业实体
	 * @param majorId
	 * @return 
	 */
	public Major getMajorById(int majorId);
	
	
	public Major getMajorByName(String majorName);
}
