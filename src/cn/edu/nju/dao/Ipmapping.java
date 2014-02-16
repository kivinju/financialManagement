package cn.edu.nju.dao;

/**
 * Ipmapping entity. @author MyEclipse Persistence Tools
 */
public class Ipmapping extends AbstractIpmapping implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Ipmapping() {
	}

	/** full constructor */
	public Ipmapping(IpmappingId id, Short rate, Integer amount) {
		super(id, rate, amount);
	}

}
