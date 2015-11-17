package org.javatribe.pinker.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.CommentDao;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:04
 */

/**
 * @author kaiscript 2015年10月11日 下午11:00:56 增加 public List<Comment>
 *         getByFirstresult(int firstResult) public List<Comment>
 *         getCommentsByCourseId(int courseId)
 */
@Repository(value = "commentDaoImpl")
public class CommentDaoImpl extends
		BaseDaoImpl<org.javatribe.pinker.entity.Comment> implements CommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByFirstresultDesc(int firstResult) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllListDesc() {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByCourseId(int courseId) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.eq("course.crs_id", courseId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,
			int firstResult) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.eq("course.crs_id", courseId));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdSet(Integer[] courseids) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.in("course.crs_id", courseids));
		return (List<Comment>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCourseIdSetAndFirstresult(
			Integer[] courseids, int firstResult) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.addOrder(Order.desc("cmt_id"));
		criteria.add(Restrictions.in("course.crs_id", courseids));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Comment>) criteria.list();
	}

	@Override
	public List<Comment> getCommentsByCommentatorId(int id) {
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("cmt_user_id", id));
		return (List<Comment>) criteria.list();
	}

	@Override
	public List<Comment> getReplyCommentsByOriComment(int oriCommentId) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("comment_reply.cmt_id", oriCommentId));

		return (List<Comment>) criteria.list();

	}
	
	@Override
	public List<Comment> getReplyCommentsByOriCommentatorId(int oriCommentatorId){
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("cmt_reply_user_id", oriCommentatorId));
		return (List<Comment>)criteria.list();
	}

	@Override
	public Pager getByKeyword(String keyword, Pager pager) {
		// TODO Auto-generated method stub
		Set<Comment> comments = new HashSet<Comment>();

		// 以标签为关键字查找
		Criteria criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.like("cmt_content", "%" + keyword + "%"));
		comments.addAll(new HashSet((List<Comment>) criteria.list()));

		// 以用户编号为关键字查找
		try {
			Integer num = Integer.parseInt(keyword);
			criteria = getSession().createCriteria(Comment.class);
			criteria.add(Restrictions.eq("cmt_user_id", num));
			comments.addAll(new HashSet((List<Comment>) criteria.list()));
		} catch (Exception e) {

		}

		//以标签为关键字查找
		criteria = getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.like("cmt_crs_label", "%" + keyword + "%"));
		comments.addAll(new HashSet((List<Comment>) criteria.list()));

		pager.init();
		pager.setDatas(new ArrayList(comments));

		return pager;
	}

}
