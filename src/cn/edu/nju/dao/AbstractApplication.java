package cn.edu.nju.dao;

/**
 * AbstractApplication entity provides the base persistence definition of the
 * Application entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractApplication implements java.io.Serializable {

	// Fields

	private ApplicationId id;
	private Integer amount;
	private Short state;

	// Constructors

	/** default constructor */
	public AbstractApplication() {
	}

	/** full constructor */
	public AbstractApplication(ApplicationId id, Integer amount, Short state) {
		this.id = id;
		this.amount = amount;
		this.state = state;
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

}