package cn.edu.nju.entity;

/**
 * AbstractApplicationId entity provides the base persistence definition of the
 * ApplicationId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractApplicationId implements java.io.Serializable {

	// Fields

	private User user;
	private Project project;
	private Item item;

	// Constructors

	/** default constructor */
	public AbstractApplicationId() {
	}

	/** full constructor */
	public AbstractApplicationId(User user, Project project, Item item) {
		this.user = user;
		this.project = project;
		this.item = item;
	}

	// Property accessors

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
		if (!(other instanceof AbstractApplicationId))
			return false;
		AbstractApplicationId castOther = (AbstractApplicationId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null
				&& castOther.getUser() != null && this.getUser().equals(
				castOther.getUser())))
				&& ((this.getProject() == castOther.getProject()) || (this
						.getProject() != null && castOther.getProject() != null && this
						.getProject().equals(castOther.getProject())))
				&& ((this.getItem() == castOther.getItem()) || (this.getItem() != null
						&& castOther.getItem() != null && this.getItem()
						.equals(castOther.getItem())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result
				+ (getProject() == null ? 0 : this.getProject().hashCode());
		result = 37 * result
				+ (getItem() == null ? 0 : this.getItem().hashCode());
		return result;
	}

}