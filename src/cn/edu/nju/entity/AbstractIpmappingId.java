package cn.edu.nju.entity;

/**
 * AbstractIpmappingId entity provides the base persistence definition of the
 * IpmappingId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractIpmappingId implements java.io.Serializable {

	// Fields

	private Project project;
	private Item item;

	// Constructors

	/** default constructor */
	public AbstractIpmappingId() {
	}

	/** full constructor */
	public AbstractIpmappingId(Project project, Item item) {
		this.project = project;
		this.item = item;
	}

	// Property accessors

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractIpmappingId))
			return false;
		AbstractIpmappingId castOther = (AbstractIpmappingId) other;

		return ((this.getProject() == castOther.getProject()) || (this
				.getProject() != null && castOther.getProject() != null && this
				.getProject().equals(castOther.getProject())))
				&& ((this.getItem() == castOther.getItem()) || (this.getItem() != null
						&& castOther.getItem() != null && this.getItem()
						.equals(castOther.getItem())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getProject() == null ? 0 : this.getProject().hashCode());
		result = 37 * result
				+ (getItem() == null ? 0 : this.getItem().hashCode());
		return result;
	}

}