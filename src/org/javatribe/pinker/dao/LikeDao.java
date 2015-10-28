package org.javatribe.pinker.dao;

import org.javatribe.pinker.entity.Likes;
/**
 * @author kaiscript
 * 2015年10月23日 下午10:33:31
 */
public interface LikeDao extends BaseDao<Likes> {
	public Likes getLikeByCommentidAndUserid(int like_cmt_id,int like_user_id);
}
