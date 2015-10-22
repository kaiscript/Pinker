package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Teacher;

/**
 * @author Mars
 *
 *2015年10月14日 下午10:31:59
 */

public interface TeacherService {

	public boolean save(Teacher teacher);
	
	public boolean delete(Teacher teacher);
	
	public boolean update(Teacher teacher);
	
	public Teacher getById(Integer id);
	
	public List<Teacher> getByIdSet(Integer[] ids);
	
	public List<Teacher> getAllList();
	
	public Pager findByPager(Pager pager);
	
	
}
