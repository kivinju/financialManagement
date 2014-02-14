package cn.edu.nju.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractItem entity provides the base persistence definition of the Item
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItem implements java.io.Serializable {

	// Fields

	private Integer iid;
	private String name;
	private Set applications = new HashSet(0);
	private Set ipmappings = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractItem() {
	}

	/** minimal constructor */
	public AbstractItem(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractItem(String name, Set applications, Set ipmappings) {
		this.name = name;
		this.applications = applications;
		this.ipmappings = ipmappings;
	}

	// Property accessors

	public Integer getIid() {
		return this.iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getApplications() {
		return this.applications;
	}

	public void setApplications(Set applications) {
		this.applications = applications;
	}

	public Set getIpmappings() {
		return this.ipmappings;
	}

	public void setIpmappings(Set ipmappings) {
		this.ipmappings = ipmappings;
	}

}