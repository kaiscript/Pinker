package org.javatribe.pinker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author kaiscript
 * 2015年7月29日 下午11:55:30
 */

@Entity
@Table(name="admin")
public class Admin {
	
	private String admin_username;
	private String admin_name;
	private String admin_password;
	private boolean admin_is_super;
	private Date admin_regist_time;
	
	public Admin() {
	}

	public Admin(String admin_username, String admin_name,
			String admin_password, boolean admin_is_super,
			Date admin_regist_time) {
		this.admin_username = admin_username;
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_is_super = admin_is_super;
		this.admin_regist_time = admin_regist_time;
	}
	
	@Id
	@GeneratedValue(generator="admin_id")
	@GenericGenerator(name="admin_id",strategy="assigned")
	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}
	
	
	@Column(length=20,nullable=false)
	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	
	@Column(length=20,nullable=false)
	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
	@org.hibernate.annotations.Type(type="yes_no")
	@Column(nullable=false)
	public boolean isAdmin_is_super() {
		return admin_is_super;
	}

	public void setAdmin_is_super(boolean admin_is_super) {
		this.admin_is_super = admin_is_super;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getAdmin_regist_time() {
		return admin_regist_time;
	}

	public void setAdmin_regist_time(Date admin_regist_time) {
		this.admin_regist_time = admin_regist_time;
	}
	
	
}
