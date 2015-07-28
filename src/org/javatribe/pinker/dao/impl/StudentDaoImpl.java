package org.javatribe.pinker.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.dao.StudentDao;
import org.javatribe.pinker.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository(value = "studentDaoImpl")
public class StudentDaoImpl implements StudentDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return sessionFactory.getCurrentSession().createQuery("from Student").list();
	}

	@Override
	public Student getStudent(String id) {
		return (Student) sessionFactory.getCurrentSession().load(Student.class, id);
	}

	@Override
	public void delStudent(String id) {
		Student stu=this.getStudent(id);
		sessionFactory.getCurrentSession().delete(stu);
	}

	@Override
	public void updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}

}
