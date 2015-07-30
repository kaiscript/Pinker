package org.javatribe.pinker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mars
 *
 *2015年7月29日 下午3:56:22
 */

@Entity
@Table
public class Report {

	private int rpt_id;
	private int rpt_cmt_id;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRpt_id() {
		return rpt_id;
	}

	public void setRpt_id(int rpt_id) {
		this.rpt_id = rpt_id;
	}

	@Column(name="rpt_cmt_id", length=8, nullable=false)
	public int getRpt_cmt_id() {
		return rpt_cmt_id;
	}

	public void setRpt_cmt_id(int rpt_cmt_id) {
		this.rpt_cmt_id = rpt_cmt_id;
	}

	public Report(int rpt_id, int rpt_cmt_id) {
		super();
		this.rpt_id = rpt_id;
		this.rpt_cmt_id = rpt_cmt_id;
	}

	public Report() {
		super();
	}

}
