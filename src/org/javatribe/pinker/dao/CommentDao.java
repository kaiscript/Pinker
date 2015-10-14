package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:58
 */

/**
 * @author kaiscript
 * 2015年10月11日 下午11:08:46
 * 增加 public List<Comment> getByFirstresult(int firstResult)
 */
@Repository(value="commentDao")
public interface CommentDao extends BaseDao<org.javatribe.pinker.entity.Comment> {
	
	/**
	 * 
	 * @param firstResult 第几条记录开始
	 * @return 从某条记录开始的10条记录，倒序desc
	 */
	public List<Comment> getByFirstresultDesc(int firstResult);
	
	/**
	 * 
	 * @return 根据主键降序排序的 所有记录
	 */
	public List<Comment> getAllListDesc();
}
