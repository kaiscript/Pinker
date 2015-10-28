package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Report;

/**
 * @author kaiscript 2015年10月27日 下午12:21:56
 */
public interface ReportService {
	public boolean save(Report report);

	public boolean delete(Report report);

	public boolean update(Report report);

	public Report getById(Integer id);

	public List<Report> getByIdSet(Integer[] ids);

	public List<Report> getAllList();

	public Pager findByPager(Pager pager);
	
	/**
	 * 根据评论id取得 举报
	 * @param id
	 * @return
	 */
	public Report getReportByCommentId(Integer id);
}
