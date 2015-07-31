package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Major crs_maj;
	private Elective_category crs_catg;
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

	@ManyToOne
	@JoinColumn(name="crs_maj_id",nullable=true)
	public Major getCrs_maj() {
		return crs_maj;
	}

	public void setCrs_maj(Major crs_maj) {
		this.crs_maj = crs_maj;
	}

	@ManyToOne
	@JoinColumn(name="crs_catg_id",nullable=true)
	public Elective_category getCrs_catg() {
		return crs_catg;
	}

	public void setCrs_catg(Elective_category crs_catg) {
		this.crs_catg = crs_catg;
	}

	@Column(length=1024, nullable=true)
	public String getCrs_labels() {
		return crs_labels;
	}

	public void setCrs_labels(String crs_labels) {
		this.crs_labels = crs_labels;
	}


	public Course(int crs_id, String crs_name, String crs_teacher_name,
			String crs_desc, int crs_avg_star, Major crs_maj,
			Elective_category crs_catg, String crs_labels) {
		super();
		this.crs_id = crs_id;
		this.crs_name = crs_name;
		this.crs_teacher_name = crs_teacher_name;
		this.crs_desc = crs_desc;
		this.crs_avg_star = crs_avg_star;
		this.crs_maj = crs_maj;
		this.crs_catg = crs_catg;
		this.crs_labels = crs_labels;
	}

	public Course() {
		super();
	}

}
