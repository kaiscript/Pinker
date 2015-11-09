package org.javatribe.pinker.dao;

import org.javatribe.pinker.entity.Report;

/** @author kaiscript
 * 2015年10月27日 下午12:22:05
 */
public interface ReportDao extends BaseDao<Report> {
	public Report getReportByCommentId(Integer id);
}
