package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.StudentDao;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.StudentService;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaiscript
 * 2015年8月15日 下午6:46:27
 */

@Service(value = "studentServiceImpl")
@Transactional  
public class StudentServiceImpl implements StudentService {

	
	@Resource(name="studentDaoImpl")
	StudentDao studentDao;

	@Override
	public boolean save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public boolean delete(Student student) {
		return studentDao.delete(student);
	}

	@Override
	public boolean update(Student student) {
		return studentDao.update(student);
	}

	@Override
	public Student getById(Integer id) {
		return studentDao.get(id);
	}

	@Override
	public List<Student> getByIdSet(Integer[] ids) {
		return studentDao.getByIdSet(ids);
	}

	@Override
	public List<Student> getAllList() {
		return studentDao.getAllList();
	}

	@Override
	public Pager findByPager(Pager pager) {
		return studentDao.findByPager(pager);
	}
	
}
