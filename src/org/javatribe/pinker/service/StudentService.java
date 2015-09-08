package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Student;

/**
 * @author kaiscript
 * 2015年8月15日 下午6:47:56
 */
public interface StudentService {
	/**
	 * 保存学生实体
	 * @param student
	 * @return 是否保存成功
	 */
	public boolean save(Student student);
	
	/**
	 * 删除学生实体
	 * @param student
	 * @return 是否删除成功
	 */
	public boolean delete(Student student);
	
	/**
	 * 更新学生实体
	 * @param student
	 * @return 是否更新成功
	 */
	public boolean update(Student student);
	
	/**
	 * 根据id获取学生实体
	 * @param id
	 * @return 对应id的学生实体
	 */
	public Student getById(Integer id);
	
	/**
	 * 根据id集合获取学生集合
	 * @param ids
	 * @return
	 */
	public List<Student> getByIdSet(Integer[] ids);
	
	/**
	 * 获取所有学生的集合
	 * @return
	 */
	public List<Student> getAllList();
	
	/**
	 * 根据pager变量信息获得pager
	 * @param pager
	 * @return 分页变量
	 */
	public Pager findByPager(Pager pager);
}
