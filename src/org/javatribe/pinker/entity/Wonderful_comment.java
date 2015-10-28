package org.javatribe.pinker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/** @author kaiscript
 * 2015年10月25日 下午8:05:41
 */
@Entity
@Table(name= "wonderful_comment")
public class Wonderful_comment {
	
	private int wer_cmt_id;
	private Comment comment;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getWer_cmt_id() {
		return wer_cmt_id;
	}
	public void setWer_cmt_id(int wer_cmt_id) {
		this.wer_cmt_id = wer_cmt_id;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "cmt_id")
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public Wonderful_comment() {
		super();
	}
	public Wonderful_comment(int wer_cmt_id, Comment comment) {
		super();
		this.wer_cmt_id = wer_cmt_id;
		this.comment = comment;
	}
}
