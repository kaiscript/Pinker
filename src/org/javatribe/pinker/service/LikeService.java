package org.javatribe.pinker.service;

import org.javatribe.pinker.entity.Likes;
/**
 * @author kaiscript
 * 2015年10月23日 下午10:33:25
 */
public interface LikeService {
	
	/**
	 * 根据点赞id获取点赞条目
	 * @param like_cmt_id
	 * @return
	 */
	public Likes getById(int like_cmt_id);
	
	/**
	 * @param like
	 * @return
	 */
	public boolean save(Likes like);
	/**
	 * 根据评价编号和 点赞者编号获取点赞条目
	 * @param like_cmt_id
	 * @param like_user_id
	 * @return
	 */
	public Likes getLikeByCommentidAndUserid(int like_cmt_id,int like_user_id);
}
