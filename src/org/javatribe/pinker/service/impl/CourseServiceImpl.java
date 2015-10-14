package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.CourseDao;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Course;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.CourseService;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mars
 * 
 *         2015年8月30日 下午2:32:45
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

}
