package cn.edu.nju.dao;

/**
 * IpmappingId entity. @author MyEclipse Persistence Tools
 */
public class IpmappingId extends AbstractIpmappingId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public IpmappingId() {
	}

	/** full constructor */
	public IpmappingId(Project project, Item item) {
		super(project, item);
	}

}
