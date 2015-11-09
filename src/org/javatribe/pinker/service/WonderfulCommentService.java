package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Wonderful_comment;

/** @author kaiscript
 * 2015年10月26日 下午10:32:30
 */
public interface WonderfulCommentService {
	public boolean save(Wonderful_comment wonderful_comment);

	public boolean delete(Wonderful_comment wonderful_comment);

	public boolean update(Wonderful_comment wonderful_comment);

	public Wonderful_comment getById(Integer id);

	public List<Wonderful_comment> getByIdSet(Integer[] ids);

	public List<Wonderful_comment> getAllList();
	
	public List<Wonderful_comment> getListByFirstresult(int firstResult);

	public Pager findByPager(Pager pager);
}
