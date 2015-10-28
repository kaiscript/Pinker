package org.javatribe.pinker.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.CommentDao;
import org.javatribe.pinker.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:04
 */

/**
 * @author kaiscript
 * 2015年10月11日 下午11:00:56
 * 增加 public List<Comment> getByFirstresult(int firstResult)
 * public List<Comment> getCommentsByCourseId(int courseId)
 */
@Repository(value="commentDaoImpl")
public class CommentDaoImpl extends BaseDaoImpl<org.javatribe.pinker.entity.Comment> implements CommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByFirstresultDesc(int firstResult) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllListDesc() {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		return (List<Comment>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByCourseId(int courseId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.eq("course.crs_id",courseId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,int firstResult) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.eq("course.crs_id",courseId));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdSet(Integer[] courseids) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.in("course.crs_id", courseids));
		return (List<Comment>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdSetAndFirstresult(Integer[] courseids,int firstResult){
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.in("course.crs_id", courseids));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>)criteria.list();
	}
	
	
}
