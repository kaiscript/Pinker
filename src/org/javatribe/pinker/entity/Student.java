package org.javatribe.pinker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author kaiscript
 * 2015年7月31日 上午11:35:07
 */

@Entity
@Table(name="student")
public class Student {
	
	private int stu_id;
	private String stu_name;
	private String stu_password;
	private Department department;
	private Major major;
	private String stu_sex;
	private String stu_per_sig;
	private String stu_head_img;
	private Date stu_regist_time;
	private String stu_attn_crs_ids;
	private String stu_pw_question;
	private String stu_pw_answer;
	
	
	public Student() {
		
	}
	
	public Student(int stu_id, String stu_name, String stu_password,
			Department department, Major major, String stu_sex,
			String stu_per_sig, String stu_head_img, Date stu_regist_time,
			String stu_attn_crs_ids, String stu_pw_question,
			String stu_pw_answer) {
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_password = stu_password;
		this.department = department;
		this.major = major;
		this.stu_sex = stu_sex;
		this.stu_per_sig = stu_per_sig;
		this.stu_head_img = stu_head_img;
		this.stu_regist_time = stu_regist_time;
		this.stu_attn_crs_ids = stu_attn_crs_ids;
		this.stu_pw_question = stu_pw_question;
		this.stu_pw_answer = stu_pw_answer;
	}


	@Id
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	
	@Column(length=20,nullable=false)
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	
	@Column(length=20,nullable=false)
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	
	
	@Column(length=2,nullable=false)
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	
	@Column(length=255,nullable=true)
	public String getStu_per_sig() {
		return stu_per_sig;
	}
	public void setStu_per_sig(String stu_per_sig) {
		this.stu_per_sig = stu_per_sig;
	}
	
	@Column(length=255,nullable=true)
	public String getStu_head_img() {
		return stu_head_img;
	}
	public void setStu_head_img(String stu_head_img) {
		this.stu_head_img = stu_head_img;
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getStu_regist_time() {
		return stu_regist_time;
	}
	public void setStu_regist_time(Date stu_regist_time) {
		this.stu_regist_time = stu_regist_time;
	}
	
	@Column(length=255,nullable=true)
	public String getStu_attn_crs_ids() {
		return stu_attn_crs_ids;
	}
	public void setStu_attn_crs_ids(String stu_attn_crs_ids) {
		this.stu_attn_crs_ids = stu_attn_crs_ids;
	}

	@Column(length=64,nullable=true)
	public String getStu_pw_question() {
		return stu_pw_question;
	}
	public void setStu_pw_question(String stu_pw_question) {
		this.stu_pw_question = stu_pw_question;
	}
	
	@Column(length=64,nullable=true)
	public String getStu_pw_answer() {
		return stu_pw_answer;
	}
	public void setStu_pw_answer(String stu_pw_answer) {
		this.stu_pw_answer = stu_pw_answer;
	}

	
	@ManyToOne()
	@JoinColumn(name="stu_dept_id",nullable=false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@ManyToOne()
	@JoinColumn(name="stu_major_id",nullable=false)
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	
}
