package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.ReportDao;
import org.javatribe.pinker.entity.Report;
import org.javatribe.pinker.service.ReportService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "reportServiceImpl")
@Transactional
public class ReportServiceImpl implements ReportService {
	
	@Resource(name = "reportDaoImpl")
	ReportDao reportDao;
	
	@Override
	public boolean save(Report report) {
		return reportDao.save(report);
	}

	@Override
	public boolean delete(Report report) {
		return reportDao.delete(report);
	}

	@Override
	public boolean update(Report report) {
		return reportDao.update(report);
	}

	@Override
	public Report getById(Integer id) {
		return reportDao.get(id);
	}

	@Override
	public List<Report> getByIdSet(Integer[] ids) {
		return reportDao.getByIdSet(ids);
	}

	@Override
	public List<Report> getAllList() {
		return reportDao.getAllList();
	}

	@Override
	public Pager findByPager(Pager pager) {
		return reportDao.findByPager(pager);
	}

	@Override
	public Report getReportByCommentId(Integer id) {
		return reportDao.getReportByCommentId(id);
	}
	
}
