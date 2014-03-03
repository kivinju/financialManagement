package cn.edu.nju.entity;

/**
 * UpmappingId entity. @author MyEclipse Persistence Tools
 */
public class UpmappingId extends AbstractUpmappingId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UpmappingId() {
	}

	/** full constructor */
	public UpmappingId(User user, Project project) {
		super(user, project);
	}

}
