package org.javatribe.pinker.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.javatribe.pinker.dao.StudentDao;
import org.javatribe.pinker.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaiscript
 * 2015年7月30日 下午3:00:43
 */

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
	public Student getStudentById(String id) {
		return (Student) sessionFactory.getCurrentSession().load(Student.class, id);
	}

	@Override
	public boolean delStudent(String id) {
		String hql="del student s where s.stu_id=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return (query.executeUpdate()>0);
	}

	@Override
	public boolean updateStudent(Student student) {
		try {
			Session session =sessionFactory.getCurrentSession();
			session.clear();
			session.update(student);
			session.flush();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}
