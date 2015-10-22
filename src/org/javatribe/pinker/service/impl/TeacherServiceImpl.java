package org.javatribe.pinker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.dao.TeacherDao;
import org.javatribe.pinker.entity.Teacher;
import org.javatribe.pinker.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mars
 *
 *2015年10月14日 下午10:35:17
 */

@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl implements TeacherService{

	@Resource(name="teacherDaoImpl")
	TeacherDao teacherDao;
	
	@Override
	public boolean save(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.save(teacher);
	}

	@Override
	public boolean delete(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.delete(teacher);
	}

	@Override
	public boolean update(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.update(teacher);
	}

	@Override
	public Teacher getById(Integer id) {
		// TODO Auto-generated method stub
		return teacherDao.get(id);
	}

	@Override
	public List<Teacher> getByIdSet(Integer[] ids) {
		// TODO Auto-generated method stub
		return teacherDao.getByIdSet(ids);
	}

	@Override
	public List<Teacher> getAllList() {
		// TODO Auto-generated method stub
		return teacherDao.getAllList();
	}

	@Override
	public Pager findByPager(Pager pager) {
		// TODO Auto-generated method stub
		return teacherDao.findByPager(pager);
	}

	 
	
}
