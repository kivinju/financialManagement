package cn.edu.nju.entity;

/**
 * AbstractUpmapping entity provides the base persistence definition of the
 * Upmapping entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUpmapping implements java.io.Serializable {

	// Fields

	private UpmappingId id;
	private Boolean uprole;

	// Constructors

	/** default constructor */
	public AbstractUpmapping() {
	}

	/** full constructor */
	public AbstractUpmapping(UpmappingId id, Boolean uprole) {
		this.id = id;
		this.uprole = uprole;
	}

	// Property accessors

	public UpmappingId getId() {
		return this.id;
	}

	public void setId(UpmappingId id) {
		this.id = id;
	}

	public Boolean getUprole() {
		return this.uprole;
	}

	public void setUprole(Boolean uprole) {
		this.uprole = uprole;
	}

}