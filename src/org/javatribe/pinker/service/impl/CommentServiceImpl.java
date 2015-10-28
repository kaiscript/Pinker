package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.CommentDao;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:26:43
 */

@Service("commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Resource(name="commentDaoImpl")
	CommentDao commentDao;
	
	@Override
	public boolean save(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.save(comment);
	}

	@Override
	public boolean delete(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.delete(comment);
	}

	@Override
	public boolean update(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.update(comment);
	}

	@Override
	public Comment getById(Integer id) {
		return commentDao.get(id);
	}

	@Override
	public List<Comment> getByIdSet(Integer[] ids) {
		// TODO Auto-generated method stub
		return commentDao.getByIdSet(ids);
	}

	@Override
	public List<Comment> getAllList() {
		return commentDao.getAllList();
	}
	
	@Override
	public List<Comment> getAllListDesc() {
		return commentDao.getAllListDesc();
	}

	@Override
	public Pager findByPager(Pager pager) {
		return commentDao.findByPager(pager);
	}

	@Override
	public List<Comment> getByFirstresultDesc(int firstResult) {
		return commentDao.getByFirstresultDesc(firstResult);
	}

	@Override
	public List<Comment> getCommentsByCourseId(int courseId) {
		return commentDao.getCommentsByCourseId(courseId);
	}

	@Override
	public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,
			int firstResult) {
		return commentDao.getCommentByCourseIdAndFirstresult(courseId, firstResult);
	}

	@Override
	public List<Comment> getCommentByCourseIdSet(Integer[] courseids) {
		return commentDao.getCommentByCourseIdSet(courseids);
	}

	@Override
	public List<Comment> getCommentByCourseIdSetAndFirstresult(
			Integer[] courseids, int firstResult) {
		return commentDao.getCommentByCourseIdSetAndFirstresult(courseids, firstResult);
	}
	
}
