package cn.edu.nju.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractProject entity provides the base persistence definition of the
 * Project entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProject implements java.io.Serializable {

	// Fields

	private Integer pid;
	private Integer amount;
	private Date beginDate;
	private Date endDate;
	private String description;
	private Set upmappings = new HashSet(0);
	private Set ipmappings = new HashSet(0);
	private Set applications = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractProject() {
	}

	/** minimal constructor */
	public AbstractProject(Integer amount, Date beginDate, Date endDate) {
		this.amount = amount;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	/** full constructor */
	public AbstractProject(Integer amount, Date beginDate, Date endDate,
			String description, Set upmappings, Set ipmappings, Set applications) {
		this.amount = amount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
		this.upmappings = upmappings;
		this.ipmappings = ipmappings;
		this.applications = applications;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getUpmappings() {
		return this.upmappings;
	}

	public void setUpmappings(Set upmappings) {
		this.upmappings = upmappings;
	}

	public Set getIpmappings() {
		return this.ipmappings;
	}

	public void setIpmappings(Set ipmappings) {
		this.ipmappings = ipmappings;
	}

	public Set getApplications() {
		return this.applications;
	}

	public void setApplications(Set applications) {
		this.applications = applications;
	}

}