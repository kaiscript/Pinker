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

/**
 * @author Mars 2015年7月29日 下午3:19:30
 */

@Entity
@Table(name="announcement")
public class Announcement {

	private int annc_id;
	private String annc_content;
	private Date annc_time;
	private String annc_publisher;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getAnnc_id() {
		return annc_id;
	}

	public void setAnnc_id(int annc_id) {
		this.annc_id = annc_id;
	}

	@Column(name="annc_content",length=10240, nullable=false )
	public String getAnnc_content() {
		return annc_content;
	}

	public void setAnnc_content(String annc_content) {
		this.annc_content = annc_content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="annc_time",nullable=false)
	public Date getAnnc_time() {
		return annc_time;
	}

	public void setAnnc_time(Date annc_time) {
		this.annc_time = annc_time;
	}

	@Column(name="annc_publisher", length=20, nullable=false)
	public String getAnnc_publisher() {
		return annc_publisher;
	}

	public void setAnnc_publisher(String annc_publisher) {
		this.annc_publisher = annc_publisher;
	}

	public Announcement() {
		super();
	}

	public Announcement(int annc_id, String annc_content, Date annc_time,
			String annc_publisher) {
		super();
		this.annc_id = annc_id;
		this.annc_content = annc_content;
		this.annc_time = annc_time;
		this.annc_publisher = annc_publisher;
	}

}
