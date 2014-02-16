package cn.edu.nju.dao;

import java.util.Date;

/**
 * AbstractApplication entity provides the base persistence definition of the
 * Application entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractApplication implements java.io.Serializable {

	// Fields

	private ApplicationId id;
	private Integer amount;
	private Short state;
	private Date time;

	// Constructors

	/** default constructor */
	public AbstractApplication() {
	}

	/** full constructor */
	public AbstractApplication(ApplicationId id, Integer amount, Short state,
			Date time) {
		this.id = id;
		this.amount = amount;
		this.state = state;
		this.time = time;
	}

	// Property accessors

	public ApplicationId getId() {
		return this.id;
	}

	public void setId(ApplicationId id) {
		this.id = id;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}