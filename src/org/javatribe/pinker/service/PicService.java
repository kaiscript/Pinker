package org.javatribe.pinker.service;

import java.util.List;

import org.javatribe.pinker.entity.Picture;

/**
 * @author kaiscript
 * 2015年10月16日 下午3:31:45
 */
public interface PicService{
	/**
	 * 
	 * @param picture
	 * @return
	 */
	public boolean save(Picture picture);
	/**
	 * 
	 * @param picture
	 * @return
	 */
	public boolean delete(Picture picture);
	/**
	 * 
	 * @param picture
	 * @return
	 */
	public boolean update(Picture picture);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Picture getById(Integer id);
	/**
	 * 
	 * @param ids
	 * @return
	 */
	public List<Picture> getByIdSet(Integer[] ids);
	/**
	 * 
	 * @return
	 */
	public List<Picture> getAllList();
}
