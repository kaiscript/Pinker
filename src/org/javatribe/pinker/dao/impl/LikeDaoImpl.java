package org.javatribe.pinker.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.LikeDao;
import org.javatribe.pinker.entity.Likes;
import org.springframework.stereotype.Repository;
/**
 * @author kaiscript
 * 2015年10月23日 下午10:33:38
 */
@Repository(value = "likeDaoImpl")
public class LikeDaoImpl extends BaseDaoImpl<Likes> implements LikeDao {

	@Override
	public Likes getLikeByCommentidAndUserid(int like_cmt_id,
			int like_user_id) {
		Criteria criteria = getSession().createCriteria(Likes.class);
		criteria.add(Restrictions.eq("like_cmt_id",like_cmt_id))
				.add(Restrictions.eq("like_user_id",like_user_id));
		
		return (Likes) criteria.uniqueResult();
	}
	
	
}
