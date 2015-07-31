package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Student;

/**
 * @author kaiscript
 * 2015年7月30日 下午3:04:09
 */

public interface StudentDao {
	
	public void addStudent(Student student);
	public List<Student> getAllStudent();
	public Student getStudentById(String id);
	public boolean delStudent(String id);
	public boolean updateStudent(Student student);
}
