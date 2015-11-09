package org.javatribe.pinker.dao;

import java.util.List;

import org.javatribe.pinker.entity.Wonderful_comment;

/** @author kaiscript
 * 2015年10月26日 下午10:31:57
 */
public interface WonderfulCommentDao extends BaseDao<Wonderful_comment> {
	public List<Wonderful_comment> getListByFirstresult(int firstResult);
}
