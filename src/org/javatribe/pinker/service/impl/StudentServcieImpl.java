package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.dao.StudentDao;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author kaiscript
 * 2015年7月28日 下午9:11:10
 */

@Service("studentService")
public class StudentServcieImpl implements StudentService {
	
	@Resource(name="studentDaoImpl")
	StudentDao stuDao;
	
	public StudentDao getStuDao() {
		return stuDao;
	}

	public void setStuDao(StudentDao stuDao) {
		this.stuDao = stuDao;
	}

	@Override
	public void addStudent(Student student) {
		stuDao.addStudent(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return stuDao.getAllStudent();
	}

	@Override
	public Student getStudent(String id) {
		return stuDao.getStudent(id);
	}

	@Override
	public void delStudent(String id) {
		stuDao.delStudent(id);
	}

	@Override
	public void updateStudent(Student student) {
		stuDao.updateStudent(student);
	}

}
