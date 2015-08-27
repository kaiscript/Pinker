package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Student;

/**
 * @author kaiscript
 * 2015年8月15日 下午6:47:56
 */
public interface StudentService {
	
	public boolean save(Student student);
	
	public boolean delete(Student student);
	
	public boolean update(Student student);
	
	public Student getById(Integer id);
	
	public List<Student> getByIdSet(Integer[] ids);
	
	public List<Student> getAllList();
	
	public Pager findByPager(Pager pager);
}
