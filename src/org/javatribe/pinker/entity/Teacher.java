package org.javatribe.pinker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * 2015年7月29日 下午9:33:58
 */

@Entity
@Table(name="teacher")
public class Teacher {
	
	private int tch_id;
	private String tch_name;
	private String tch_password;
	private int tch_dept_id;
	private String tch_sex;
	private String tch_per_sig;
	private String tch_head_img;
	private Date tch_regist_time;
	
	private Department department;
	
	
	public Teacher() {
	}

	
	public Teacher(int tch_id, String tch_name, String tch_password,
			int tch_dept_id, String tch_sex, String tch_per_sig,
			String tch_head_img, Date tch_regist_time, Department department) {
		this.tch_id = tch_id;
		this.tch_name = tch_name;
		this.tch_password = tch_password;
		this.tch_dept_id = tch_dept_id;
		this.tch_sex = tch_sex;
		this.tch_per_sig = tch_per_sig;
		this.tch_head_img = tch_head_img;
		this.tch_regist_time = tch_regist_time;
		this.department = department;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=15)
	public int getTch_id() {
		return tch_id;
	}
	
	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}
	
	@Column(length=20,nullable=false)
	public String getTch_name() {
		return tch_name;
	}
	public void setTch_name(String tch_name) {
		this.tch_name = tch_name;
	}
	
	@Column(length=20,nullable=false)
	public String getTch_password() {
		return tch_password;
	}
	public void setTch_password(String tch_password) {
		this.tch_password = tch_password;
	}
	
	@Column(length=5,nullable=false)
	public int getTch_dept_id() {
		return tch_dept_id;
	}
	public void setTch_dept_id(int tch_dept_id) {
		this.tch_dept_id = tch_dept_id;
	}
	
	@Column(length=2,nullable=false)
	public String getTch_sex() {
		return tch_sex;
	}
	public void setTch_sex(String tch_sex) {
		this.tch_sex = tch_sex;
	}
	
	@Column(length=255,nullable=true)
	public String getTch_per_sig() {
		return tch_per_sig;
	}
	public void setTch_per_sig(String tch_per_sig) {
		this.tch_per_sig = tch_per_sig;
	}
	
	@Column(length=255,nullable=false)
	public String getTch_head_img() {
		return tch_head_img;
	}
	public void setTch_head_img(String tch_head_img) {
		this.tch_head_img = tch_head_img;
	}
	
	@Temporal(value=TemporalType.DATE)
	@Column(nullable=false)
	public Date getTch_regist_time() {
		return tch_regist_time;
	}
	public void setTch_regist_time(Date tch_regist_time) {
		this.tch_regist_time = tch_regist_time;
	}
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id",nullable=false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
}
