package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Mars
 *
 *2015年7月29日 下午3:56:22
 */

@Entity
@Table(name = "report")
public class Report {

	private int rpt_id;
	private Comment comment;  
	private String rpt_cmt_person_id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRpt_id() {
		return rpt_id;
	}

	public void setRpt_id(int rpt_id) {
		this.rpt_id = rpt_id;
	}

	@ManyToOne
	@JoinColumn(name="rpt_cmt_id",nullable=false)
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	
	@Column(length=65535,nullable=true)
	public String getRpt_cmt_person_id() {
		return rpt_cmt_person_id;
	}

	public void setRpt_cmt_person_id(String rpt_cmt_person_id) {
		this.rpt_cmt_person_id = rpt_cmt_person_id;
	}
	
	public Report(int rpt_id, Comment comment, String rpt_cmt_person_id) {
		super();
		this.rpt_id = rpt_id;
		this.comment = comment;
		this.rpt_cmt_person_id = rpt_cmt_person_id;
	}

	public Report() {
		super();
	}
	
}
