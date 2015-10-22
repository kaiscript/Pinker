package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.dao.PicDao;
import org.javatribe.pinker.entity.Picture;
import org.javatribe.pinker.service.PicService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kaiscript
 * 2015年10月16日 下午3:37:02
 */
@Repository(value = "picServiceImpl")
@Transactional
public class PicServiceImpl implements PicService {
	
	//另一种注入的写法。一种是在此处注入，一种是在setter注入
	PicDao picDao;

	@Resource(name = "picDaoImpl")
	public void setPicDao(PicDao picDao) {
		this.picDao = picDao;
	}

	@Override
	public boolean save(Picture picture) {
		return picDao.save(picture);
	}

	@Override
	public boolean delete(Picture picture) {
		return picDao.delete(picture);
	}

	@Override
	public boolean update(Picture picture) {
		return picDao.update(picture);
	}

	@Override
	public Picture getById(Integer id) {
		return picDao.get(id);
	}

	@Override
	public List<Picture> getByIdSet(Integer[] ids) {
		return picDao.getByIdSet(ids);
	}

	@Override
	public List<Picture> getAllList() {
		return picDao.getAllList();
	}

}
