package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.CourseDao;
import org.javatribe.pinker.entity.Course;
import org.javatribe.pinker.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yeungkacent
 *
 * 2015年11月13日 下午9:51:42
 */
@Service("courseServiceImpl")
@Transactional  
public class CourseServiceImpl implements CourseService{

	@Resource(name="courseDaoImpl")
	CourseDao courseDao;
	
	@Override
	public boolean save(Course course) {
		// TODO Auto-generated method stub
		return courseDao.save(course);
	}

	@Override
	public boolean delete(Course course) {
		// TODO Auto-generated method stub
		return courseDao.delete(course);
	}

	@Override
	public boolean update(Course course) {
		// TODO Auto-generated method stub
		return courseDao.update(course);
	}

	@Override
	public Course getById(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.get(id);
	}

	@Override
	public List<Course> getByIdSet(Integer[] ids) {
		// TODO Auto-generated method stub
		return courseDao.getByIdSet(ids);
	}

	@Override
	public List<Course> getAllList() {
		// TODO Auto-generated method stub
		return courseDao.getAllList();
	}

	@Override
	public Pager findByPager(Pager pager) {
		// TODO Auto-generated method stub
		return courseDao.findByPager(pager);
	} 
	
	@Override
	public List<Course> getCourseByCourseId(int courseId){
		// TODO Auto-generated method stub
		return courseDao.getCourseByCourseId(courseId);
	}

	@Override
	public List<Course> getCourseByMajorId(int majorId) {
		// TODO Auto-generated method stub
		return courseDao.getCourseByMajorId(majorId);
	}

	
	
}
