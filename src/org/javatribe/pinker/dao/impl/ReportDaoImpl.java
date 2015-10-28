package org.javatribe.pinker.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javatribe.pinker.dao.ReportDao;
import org.javatribe.pinker.entity.Report;
import org.springframework.stereotype.Repository;

/** @author kaiscript
 * 2015年10月27日 下午12:20:05
 */
@Repository(value = "reportDaoImpl")
public class ReportDaoImpl extends BaseDaoImpl<Report> implements ReportDao {

	@Override
	public Report getReportByCommentId(Integer id) {
		Criteria criteria = getSession().createCriteria(Report.class);
		criteria.add(Restrictions.eq("comment.cmt_id", id));
		return (Report) criteria.uniqueResult();
	}

}
