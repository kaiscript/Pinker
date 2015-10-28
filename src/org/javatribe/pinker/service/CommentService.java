package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Comment;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:24:10
 */


public interface CommentService {

	public boolean save(Comment comment);
	
	public boolean delete(Comment comment);
	
	public boolean update(Comment comment);
	
	public Comment getById(Integer id);
	
	public List<Comment> getByIdSet(Integer[] ids);
	
	public List<Comment> getAllList();
	
	public List<Comment> getAllListDesc();
	
	public List<Comment> getByFirstresultDesc(int firstResult);
	
	public Pager findByPager(Pager pager);
	
	public List<Comment> getCommentsByCourseId(int courseId);
	
	public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,int firstResult);

	public List<Comment> getCommentsByCommentatorId(int id);
}
