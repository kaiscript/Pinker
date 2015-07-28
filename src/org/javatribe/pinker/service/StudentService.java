package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Student;

public interface StudentService {
	
	public void addStudent(Student student);
	public List<Student> getAllStudent();
	public Student getStudent(String id);
	public void delStudent(String id);
	public void updateStudent(Student student);
}
