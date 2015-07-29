package org.javatribe.pinker.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author kaiscript
 * 2015年7月29日 下午11:55:39
 */

@Entity
@Table(name="department")
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int dept_id;
	private String dept_name;
	private int dept_tel;
	
	private List<Teacher> teachers;
	private List<Student> students;
	private List<Major> majors;
	
	public Department() {
	}
	
	public Department(int dept_id, String dept_name, int dept_tel,
			List<Teacher> teachers) {
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.dept_tel = dept_tel;
		this.teachers = teachers;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	
	
	
	@Column(length=30,nullable=false)
	public String getDept_name() {
		return dept_name;
	}
	

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	@Column(length=11,nullable=true)
	public int getDept_tel() {
		return dept_tel;
	}
	public void setDept_tel(int dept_tel) {
		this.dept_tel = dept_tel;
	}
	

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="department")
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="department")
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="department")
	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}
	
	
}
