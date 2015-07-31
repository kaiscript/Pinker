package org.javatribe.pinker.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author kaiscript
 * 2015年7月29日 下午11:55:57
 */
@Entity
@Table(name="major")
public class Major {
	
	private int maj_id;
	private String maj_name;
	private Department department;
	
	private Set<Student> students=new HashSet<Student>();
	private Set<Course> courses=new HashSet<Course>();
	
	public Major() {
	}

	public Major(int maj_id, String maj_name, Department department) {
		this.maj_id = maj_id;
		this.maj_name = maj_name;
		this.department = department;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMaj_id() {
		return maj_id;
	}

	public void setMaj_id(int maj_id) {
		this.maj_id = maj_id;
	}
	
	@Column(length=30,nullable=false)
	public String getMaj_name() {
		return maj_name;
	}

	public void setMaj_name(String maj_name) {
		this.maj_name = maj_name;
	}
	
	
	@ManyToOne()
	@JoinColumn(name="maj_dept_id",nullable=false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="major")
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="major")
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
}
