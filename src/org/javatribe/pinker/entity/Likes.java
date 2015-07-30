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
 *2015年7月29日 下午5:20:13
 */

@Entity
@Table(name="likes")
public class Likes {

	private int like_id;
	private int like_cmt_id;
	private int like_user_id;
	private Date like_time;
	private int like_cmt_user_id;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=8, nullable=false)
	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}

	@Column(length=8, nullable=false)
	public int getLike_cmt_id() {
		return like_cmt_id;
	}

	public void setLike_cmt_id(int like_cmt_id) {
		this.like_cmt_id = like_cmt_id;
	}

	@Column(length=15, nullable=false)
	public int getLike_user_id() {
		return like_user_id;
	}

	public void setLike_user_id(int like_user_id) {
		this.like_user_id = like_user_id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getLike_time() {
		return like_time;
	}

	public void setLike_time(Date like_time) {
		this.like_time = like_time;
	}

	@Column(length=8, nullable=false)
	public int getLike_cmt_user_id() {
		return like_cmt_user_id;
	}

	public void setLike_cmt_user_id(int like_cmt_user_id) {
		this.like_cmt_user_id = like_cmt_user_id;
	}

	public Likes(int like_id, int like_cmt_id, int like_user_id,
			Date like_time, int like_cmt_user_id) {
		super();
		this.like_id = like_id;
		this.like_cmt_id = like_cmt_id;
		this.like_user_id = like_user_id;
		this.like_time = like_time;
		this.like_cmt_user_id = like_cmt_user_id;
	}

	public Likes() {
		super();
	}

}
