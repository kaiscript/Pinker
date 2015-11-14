package org.javatribe.pinker.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.CourseDao;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Course;
import org.springframework.stereotype.Repository;

/**
 * @author yeungkacent
 *
 * 2015年11月13日 下午9:52:35
 */
@Repository(value = "courseDaoImpl")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourseByMajorId(int majorId)
	{
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.addOrder(Order.desc("crs_id"));
		criteria.add(Restrictions.eq("crs_maj_id",majorId));
		return (List<Course>)criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourseByCourseId(int courseId)
	{
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("crs_id",courseId));
		return (List<Course>)criteria.list();
	}

}
