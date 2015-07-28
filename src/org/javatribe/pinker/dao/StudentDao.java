package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Student;

public interface StudentDao {
	
	public void addStudent(Student student);
	public List<Student> getAllStudent();
	public Student getStudent(String id);
	public void delStudent(String id);
	public void updateStudent(Student student);
}
