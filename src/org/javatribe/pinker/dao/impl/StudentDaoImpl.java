package org.javatribe.pinker.dao.impl;


import org.javatribe.pinker.dao.StudentDao;
import org.javatribe.pinker.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author kaiscript
 * 2015年8月15日 下午6:48:03
 */

@Repository(value = "studentDaoImpl")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
	
	//this.getSession().create...
	

}
