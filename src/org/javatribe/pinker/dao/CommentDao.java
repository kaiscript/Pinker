package org.javatribe.pinker.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:58
 */

@Repository(value="commentDao")
public interface CommentDao extends BaseDao<org.javatribe.pinker.entity.Comment> {

}
