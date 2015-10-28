package org.javatribe.pinker.service.impl;

import javax.annotation.Resource;

import org.javatribe.pinker.dao.LikeDao;
import org.javatribe.pinker.entity.Likes;
import org.javatribe.pinker.service.LikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaiscript
 * 2015年10月23日 下午10:33:15
 */
@Service(value = "likeServiceImpl")
@Transactional
public class LikeServiceImpl implements LikeService {
	
	@Resource(name = "likeDaoImpl")
	private LikeDao likeDao;
	
	@Override
	public Likes getById(int like_cmt_id) {
		return likeDao.get(like_cmt_id);
	}
	
	@Override
	public boolean save(Likes like) {
		return likeDao.save(like);
	}

	@Override
	public Likes getLikeByCommentidAndUserid(int like_cmt_id,
			int like_user_id) {
		return likeDao.getLikeByCommentidAndUserid(like_cmt_id, like_user_id);
	}
	
}
