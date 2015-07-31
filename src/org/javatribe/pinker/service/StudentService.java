package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Student;

/**
 * @author kaiscript
 * 2015年7月30日 下午3:00:17
 */
public interface StudentService {
	
	public void addStudent(Student student);
	public List<Student> getAllStudent();
	public Student getStudentById(String id);
	public boolean delStudent(String id);
	public boolean updateStudent(Student student);
}
