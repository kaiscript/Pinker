package org.javatribe.pinker.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.CommentDao;
import org.javatribe.pinker.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Mars
 *
 *2015年9月7日 下午4:21:04
 */

@Repository(value="commentDaoImpl")
public class CommentDaoImpl extends BaseDaoImpl<org.javatribe.pinker.entity.Comment> implements CommentDao {


}
