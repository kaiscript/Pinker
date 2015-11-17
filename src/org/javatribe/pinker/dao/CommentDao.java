package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Comment;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:58
 */

/**
 * @author kaiscript
 * 2015年10月11日 下午11:08:46
 * 增加 public List<Comment> getByFirstresult(int firstResult)
 * public List<Comment> getCommentsByCourseId(int courseId)
 * public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,int firstResult);
 * public List<Comment> getCommentByCourseIdSet(Integer[] courseids);
   public List<Comment> getCommentByCourseIdSetAndFirstresult(Integer[] courseids,int firstResult);

 */
public interface CommentDao extends BaseDao<org.javatribe.pinker.entity.Comment> {
	
	/**
	 * 
	 * @param firstResult 第几条记录开始
	 * @return 从某条记录开始的10条记录，倒序desc
	 */
	public List<Comment> getByFirstresultDesc(int firstResult);
	
	
	/**
	 * 根据主键降序排序的 所有记录
	 * @return 
	 */
	public List<Comment> getAllListDesc();
	/**
	 * 根据课程号获得评论
	 * @param courseId
	 * @return 
	 */
	public List<Comment> getCommentsByCourseId(int courseId);
	/**
	 * 根据课程号和分页index获得对应课程评论
	 * @param courseId
	 * @param firstResult
	 * @return
	 */
	public List<Comment> getCommentByCourseIdAndFirstresult(int courseId,int firstResult);
	
	/**
	 * @param courseids
	 * @return
	 */
	public List<Comment> getCommentByCourseIdSet(Integer[] courseids);
	
	/**
	 * 根据 多门课程和firstResult获取评论列表，10条数据
	 * @param courseids
	 * @param firstResult
	 * @return
	 */
	public List<Comment> getCommentByCourseIdSetAndFirstresult(Integer[] courseids,int firstResult);

	/**
	 * 根据用户的ID获取其评论 
	 * @param id
	 * @return
	 */
	public List<Comment> getCommentsByCommentatorId(int id);
	
	
	/**
	 * 根据原评论的ID获取对其回复的评论
	 * @param commentId
	 * @return
	 */
	public List<Comment> getReplyCommentsByOriComment(int commentId);


	/**
	 * 根据关键字搜索评价，返回Pager对象
	 * @param keyword
	 * @return
	 */
	public Pager getByKeyword(String keyword, Pager pager);


	/**
	 * 根据原始评论者的ID获取回复评论
	 * @param oriCommentatorId
	 * @return
	 */
	List<Comment> getReplyCommentsByOriCommentatorId(int oriCommentatorId);
}
