package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Course;

/**
 * @author yeungkacent
 *
 * 2015年11月13日 下午9:51:09
 */
public interface CourseService {

	public boolean save(Course course);
	
	public boolean delete(Course course);
	
	public boolean update(Course course);
	
	public Course getById(Integer id);
	
	public List<Course> getByIdSet(Integer[] ids);
	
	public List<Course> getAllList();
	
	public Pager findByPager(Pager pager);
	
	public List<Course> getCourseByCourseId(int courseId);
	
	public List<Course> getCourseByMajorId(int majorId);
	
	
}
