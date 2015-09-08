package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Course;

/**
 * @author Mars
 *
 *2015年8月30日 下午2:29:49
 */

public interface CourseService {

	public boolean save(Course course);
	
	public boolean delete(Course course);
	
	public boolean update(Course course);
	
	public Course getById(Integer id);
	
	public List<Course> getByIdSet(Integer[] ids);
	
	public List<Course> getAllList();
	
	public Pager findByPager(Pager pager);
}
