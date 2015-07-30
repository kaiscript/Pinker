package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Mars
 * 
 *         2015年7月29日 下午4:16:10
 */

@Entity
@Table
public class Course {

	private int crs_id;
	private String crs_name;
	private String crs_teacher_name;
	private String crs_desc;
	private int crs_avg_star;
	private int crs_maj_id;
	private int crs_catg_id;
	private String crs_labels;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCrs_id() {
		return crs_id;
	}

	public void setCrs_id(int crs_id) {
		this.crs_id = crs_id;
	}

	@Column(length=30, nullable=false)
	public String getCrs_name() {
		return crs_name;
	}

	public void setCrs_name(String crs_name) {
		this.crs_name = crs_name;
	}

	@Column(length=20, nullable=false)
	public String getCrs_teacher_name() {
		return crs_teacher_name;
	}

	public void setCrs_teacher_name(String crs_teacher_name) {
		this.crs_teacher_name = crs_teacher_name;
	}
	
	@Column(length=255, nullable=false)
	public String getCrs_desc() {
		return crs_desc;
	}

	public void setCrs_desc(String crs_desc) {
		this.crs_desc = crs_desc;
	}

	@Column(length=2, nullable=false)
	public int getCrs_avg_star() {
		return crs_avg_star;
	}

	public void setCrs_avg_star(int crs_avg_star) {
		this.crs_avg_star = crs_avg_star;
	}

	@Column(length=5, nullable=true)
	public int getCrs_maj_id() {
		return crs_maj_id;
	}

	public void setCrs_maj_id(int crs_maj_id) {
		this.crs_maj_id = crs_maj_id;
	}

	@Column(length=5, nullable=true)
	public int getCrs_catg_id() {
		return crs_catg_id;
	}

	public void setCrs_catg_id(int crs_catg_id) {
		this.crs_catg_id = crs_catg_id;
	}

	@Column(length=1024, nullable=true)
	public String getCrs_labels() {
		return crs_labels;
	}

	public void setCrs_labels(String crs_labels) {
		this.crs_labels = crs_labels;
	}

	public Course(int crs_id, String crs_name, String crs_teacher_name,
			String crs_desc, int crs_avg_star, int crs_maj_id, int crs_catg_id,
			String crs_labels) {
		super();
		this.crs_id = crs_id;
		this.crs_name = crs_name;
		this.crs_teacher_name = crs_teacher_name;
		this.crs_desc = crs_desc;
		this.crs_avg_star = crs_avg_star;
		this.crs_maj_id = crs_maj_id;
		this.crs_catg_id = crs_catg_id;
		this.crs_labels = crs_labels;
	}

	public Course() {
		super();
	}

}
