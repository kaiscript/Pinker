package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Major;
import org.springframework.stereotype.Service;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:49
 */

public interface MajorService {
	/**
	 * 获取所有专业集合
	 * @return
	 */
	public List<Major> getAllList(); 
	
	/**
	 * 根据系id获取对应所有专业集合
	 * @param departmentId
	 * @return
	 */
	public List<Major> getMajorsByDepartmentId(int departmentId);
	
	/**
	 * 根据专业id获取专业实体
	 * @param majorId
	 * @return 
	 */
	public Major getMajorById(int majorId);
}
