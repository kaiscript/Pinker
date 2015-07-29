package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mars
 * 
 *         2015年7月29日 下午4:59:32
 */
@Entity
@Table
public class Elective_category {

	private int catg_id;
	private String catg_name;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCatg_id() {
		return catg_id;
	}

	public void setCatg_id(int catg_id) {
		this.catg_id = catg_id;
	}

	@Column(length=30,nullable=false)
	public String getCatg_name() {
		return catg_name;
	}

	public void setCatg_name(String catg_name) {
		this.catg_name = catg_name;
	}

	public Elective_category(int catg_id, String catg_name) {
		super();
		this.catg_id = catg_id;
		this.catg_name = catg_name;
	}

	public Elective_category() {
		super();
	}

}
