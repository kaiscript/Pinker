package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="picture")
public class Picture {
	
	private int pic_user_id;
	private String pic_url;
	private String pic_download_url;
	
	public Picture() {
		super();
	}

	public Picture(int pic_user_id, String pic_url, String pic_download_url) {
		super();
		this.pic_user_id = pic_user_id;
		this.pic_url = pic_url;
		this.pic_download_url = pic_download_url;
	}
	
	@Id
	public int getPic_user_id() {
		return pic_user_id;
	}
	public void setPic_user_id(int pic_user_id) {
		this.pic_user_id = pic_user_id;
	}
	
	@Column(length=255,nullable=true)
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	
	@Column(length=255,nullable =true)
	public String getPic_download_url() {
		return pic_download_url;
	}
	public void setPic_download_url(String pic_download_url) {
		this.pic_download_url = pic_download_url;
	}
	
	
}
