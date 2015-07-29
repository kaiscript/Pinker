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
 * @author Mars
 * 
 *         2015年7月29日 下午3:59:35
 */

@Entity
@Table
public class Comment {

	private int cmt_id;
	private int cmt_user_id;
	private Date cmt_time;
	private String cmt_crs_label;
	private String cmt_content;
	private int cmt_star;
	private boolean cmt_is_anon;
	private int cmt_like_number;
	private int cmt_against_number;
	private int cmt_report_number;
	private int cmt_reply_user_id;
	private int cmt_reply_id;
	private int cmt_crs_id;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCmt_id() {
		return cmt_id;
	}

	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}

	@Column(length=15 , nullable=false)
	public int getCmt_user_id() {
		return cmt_user_id;
	}

	public void setCmt_user_id(int cmt_user_id) {
		this.cmt_user_id = cmt_user_id;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCmt_time() {
		return cmt_time;
	}

	public void setCmt_time(Date cmt_time) {
		this.cmt_time = cmt_time;
	}

	@Column(length=30,nullable=true)
	public String getCmt_crs_label() {
		return cmt_crs_label;
	}

	public void setCmt_crs_label(String cmt_crs_label) {
		this.cmt_crs_label = cmt_crs_label;
	}

	@Column(length=10240, nullable=true)
	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	@Column(length=2, nullable=true)
	public int getCmt_star() {
		return cmt_star;
	}

	public void setCmt_star(int cmt_star) {
		this.cmt_star = cmt_star;
	}

	@Column(nullable=false)
	public boolean isCmt_is_anon() {
		return cmt_is_anon;
	}

	public void setCmt_is_anon(boolean cmt_is_anon) {
		this.cmt_is_anon = cmt_is_anon;
	}

	@Column(length=5, nullable=false)
	public int getCmt_like_number() {
		return cmt_like_number;
	}

	public void setCmt_like_number(int cmt_like_number) {
		this.cmt_like_number = cmt_like_number;
	}

	@Column(length=5, nullable=false)
	public int getCmt_against_number() {
		return cmt_against_number;
	}

	public void setCmt_against_number(int cmt_against_number) {
		this.cmt_against_number = cmt_against_number;
	}

	@Column(length=5, nullable=false)
	public int getCmt_report_number() {
		return cmt_report_number;
	}

	public void setCmt_report_number(int cmt_report_number) {
		this.cmt_report_number = cmt_report_number;
	}

	@Column(length=15, nullable=true)
	public int getCmt_reply_user_id() {
		return cmt_reply_user_id;
	}

	public void setCmt_reply_user_id(int cmt_replay_user_id) {
		this.cmt_reply_user_id = cmt_replay_user_id;
	}

	@Column(length=5, nullable=true)
	public int getCmt_reply_id() {
		return cmt_reply_id;
	}

	public void setCmt_reply_id(int cmt_reply_id) {
		this.cmt_reply_id = cmt_reply_id;
	}

	@Column(length=8, nullable=false)
	public int getCmt_crs_id() {
		return cmt_crs_id;
	}

	public void setCmt_crs_id(int cmt_crs_id) {
		this.cmt_crs_id = cmt_crs_id;
	}

	public Comment(int cmt_id, int cmt_user_id, Date cmt_time,
			String cmt_crs_label, String cmt_content, int cmt_star,
			boolean cmt_is_anon, int cmt_like_number, int cmt_against_number,
			int cmt_report_number, int cmt_replay_user_id, int cmt_reply_id,
			int cmt_crs_id) {
		super();
		this.cmt_id = cmt_id;
		this.cmt_user_id = cmt_user_id;
		this.cmt_time = cmt_time;
		this.cmt_crs_label = cmt_crs_label;
		this.cmt_content = cmt_content;
		this.cmt_star = cmt_star;
		this.cmt_is_anon = cmt_is_anon;
		this.cmt_like_number = cmt_like_number;
		this.cmt_against_number = cmt_against_number;
		this.cmt_report_number = cmt_report_number;
		this.cmt_reply_user_id = cmt_replay_user_id;
		this.cmt_reply_id = cmt_reply_id;
		this.cmt_crs_id = cmt_crs_id;
	}

	public Comment() {
		super();
	}

}
