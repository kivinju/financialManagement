package cn.edu.nju.dao;

/**
 * AbstractIpmapping entity provides the base persistence definition of the
 * Ipmapping entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractIpmapping implements java.io.Serializable {

	// Fields

	private IpmappingId id;
	private Short rate;

	// Constructors

	/** default constructor */
	public AbstractIpmapping() {
	}

	/** full constructor */
	public AbstractIpmapping(IpmappingId id, Short rate) {
		this.id = id;
		this.rate = rate;
	}

	// Property accessors

	public IpmappingId getId() {
		return this.id;
	}

	public void setId(IpmappingId id) {
		this.id = id;
	}

	public Short getRate() {
		return this.rate;
	}

	public void setRate(Short rate) {
		this.rate = rate;
	}

}