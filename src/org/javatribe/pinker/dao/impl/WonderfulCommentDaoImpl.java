package org.javatribe.pinker.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.javatribe.pinker.dao.WonderfulCommentDao;
import org.javatribe.pinker.entity.Wonderful_comment;
import org.springframework.stereotype.Repository;

/** @author kaiscript
 * 2015年10月26日 下午10:32:13
 */
@Repository(value = "wonderfulCommentDaoImpl")
public class WonderfulCommentDaoImpl extends BaseDaoImpl<Wonderful_comment>
		implements WonderfulCommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Wonderful_comment> getListByFirstresult(int firstResult) {
		Criteria criteria = getSession().createCriteria(Wonderful_comment.class);
		criteria.addOrder(Order.desc("wer_cmt_id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(10);
		return (List<Wonderful_comment>)criteria.list();
	}
			
}
