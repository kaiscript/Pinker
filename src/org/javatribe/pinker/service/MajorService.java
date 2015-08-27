package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Major;
import org.springframework.stereotype.Service;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:49
 */

@Service
public interface MajorService {
	public List<Major> getAllList(); 
	
	public List<Major> getMajorsByDepartmentId(int departmentId);
	
	public Major getMajorById(int majorId);
}
