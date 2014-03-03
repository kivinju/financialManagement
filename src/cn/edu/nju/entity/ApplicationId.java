package cn.edu.nju.entity;

/**
 * ApplicationId entity. @author MyEclipse Persistence Tools
 */
public class ApplicationId extends AbstractApplicationId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ApplicationId() {
	}

	/** full constructor */
	public ApplicationId(User user, Project project, Item item) {
		super(user, project, item);
	}

}
