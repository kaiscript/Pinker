package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.dao.MajorDao;
import org.javatribe.pinker.entity.Major;
import org.javatribe.pinker.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author kaiscript
 * 2015年8月25日 上午12:04:38
 */
@Service("majorServiceImpl")
@Transactional
public class MajorServiceImpl implements MajorService {
	
	@Resource(name="majorDaoImpl")
	MajorDao majorDao;
	
	@Override
	public List<Major> getAllList() {
		return majorDao.getAllList();
	}

	@Override
	public List<Major> getMajorsByDepartmentId(int departmentId) {
		return majorDao.getMajorsByDepartmentId(departmentId);
	}

	@Override
	public Major getMajorById(int majorId) {
		return majorDao.getMajorById(majorId);
	}

	@Override
	public Major getMajorByName(String majorName) {
		return majorDao.getMajorByName(majorName);
	}

}
