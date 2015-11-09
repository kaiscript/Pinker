package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.WonderfulCommentDao;
import org.javatribe.pinker.entity.Wonderful_comment;
import org.javatribe.pinker.service.WonderfulCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author kaiscript
 * 2015年10月26日 下午10:32:25
 */

@Service(value = "wonderfulServiceImpl")
@Transactional
public class WonderfulCommentServiceImpl implements WonderfulCommentService {
	
	@Resource(name = "wonderfulCommentDaoImpl")
	WonderfulCommentDao wonderfulCommentDao;
	
	@Override
	public boolean save(Wonderful_comment wonderful_comment) {
		return wonderfulCommentDao.save(wonderful_comment);
	}

	@Override
	public boolean delete(Wonderful_comment wonderful_comment) {
		return wonderfulCommentDao.delete(wonderful_comment);
	}

	@Override
	public boolean update(Wonderful_comment wonderful_comment) {
		return wonderfulCommentDao.update(wonderful_comment);
	}

	@Override
	public Wonderful_comment getById(Integer id) {
		return wonderfulCommentDao.get(id);
	}

	@Override
	public List<Wonderful_comment> getByIdSet(Integer[] ids) {
		return wonderfulCommentDao.getByIdSet(ids);
	}

	@Override
	public List<Wonderful_comment> getAllList() {
		return wonderfulCommentDao.getAllList();
	}

	
	@Override
	public List<Wonderful_comment> getListByFirstresult(int firstResult) {
		return wonderfulCommentDao.getListByFirstresult(firstResult);
	}

	@Override
	public Pager findByPager(Pager pager) {
		return wonderfulCommentDao.findByPager(pager);
	}

}
