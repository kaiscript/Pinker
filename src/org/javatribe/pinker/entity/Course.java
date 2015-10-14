package org.javatribe.pinker.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Mars
 * 
 *         2015年7月29日 下午4:16:10
 */

@Entity
@Table(name = "course")
public class Course {

	private int crs_id;
	private String crs_name;
	private String crs_teacher_name;
	private String crs_desc;
	private int crs_avg_star;
	private Major major;
	private Elective_category elective_category;
	private String crs_labels;

	private Set<Comment> comments = new HashSet<Comment>();
	
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
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@ManyToOne
	@JoinColumn(name="crs_catg_id",nullable=true)
	public Elective_category getElective_category() {
		return elective_category;
	}

	public void setElective_category(Elective_category elective_category) {
		this.elective_category = elective_category;
	}

	@Column(length=1024, nullable=true)
	public String getCrs_labels() {
		return crs_labels;
	}

	public void setCrs_labels(String crs_labels) {
		this.crs_labels = crs_labels;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="course")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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
		this.major = crs_maj;
		this.elective_category = crs_catg;
		this.crs_labels = crs_labels;
	}

	public Course() {
		super();
	}

}
