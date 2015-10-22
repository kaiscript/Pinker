package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.AdminDao;
import org.javatribe.pinker.entity.Admin;
import org.javatribe.pinker.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author kaiscript
 * 2015年10月14日 下午9:46:16
 */
@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Resource(name="adminDaoImpl")
	AdminDao adminDao;
	
	@Override
	public boolean save(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	public boolean delete(Admin admin) {
		return adminDao.delete(admin);
	}

	@Override
	public boolean update(Admin admin) {
		return adminDao.update(admin);
	}

	@Override
	public Admin getById(Integer id) {
		return adminDao.get(id);
	}

	@Override
	public List<Admin> getByIdSet(Integer[] ids) {
		return adminDao.getByIdSet(ids);
	}

	@Override
	public List<Admin> getAllList() {
		return adminDao.getAllList();
	}

	@Override
	public Pager findByPager(Pager pager) {
		return adminDao.findByPager(pager);
	}

	@Override
	public Admin getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}

}
