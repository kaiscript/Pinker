package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Course;


/**
 * @author yeungkacent
 *
 * 2015年11月13日 下午9:52:03
 */
public interface CourseDao extends BaseDao<Course> {
	
	/**
	 * @param 获取专业对应的课程
	 * @return
	 */
	
	public List<Course> getCourseByMajorId(int majorId);
	
	/**
	 * 
	 * @param courseId 根据课程编号获取课程信息
	 * @return
	 */
	
	public List<Course> getCourseByCourseId(int courseId);
	
	
}
