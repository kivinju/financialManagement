package cn.edu.nju.entity;

/**
 * Upmapping entity. @author MyEclipse Persistence Tools
 */
public class Upmapping extends AbstractUpmapping implements
		java.io.Serializable {
	public static final boolean ROLE_LEADER=true;
	public static final boolean ROLE_MEMBER=false;
	// Constructors

	/** default constructor */
	public Upmapping() {
	}

	/** full constructor */
	public Upmapping(UpmappingId id, Boolean uprole) {
		super(id, uprole);
	}

}
