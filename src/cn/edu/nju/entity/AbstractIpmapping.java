package cn.edu.nju.entity;

/**
 * AbstractIpmapping entity provides the base persistence definition of the
 * Ipmapping entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractIpmapping implements java.io.Serializable {

	// Fields

	private IpmappingId id;
	private Short rate;
	private Integer amount;

	// Constructors

	/** default constructor */
	public AbstractIpmapping() {
	}

	/** full constructor */
	public AbstractIpmapping(IpmappingId id, Short rate, Integer amount) {
		this.id = id;
		this.rate = rate;
		this.amount = amount;
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

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}