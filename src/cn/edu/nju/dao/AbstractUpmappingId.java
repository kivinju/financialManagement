package cn.edu.nju.dao;

/**
 * AbstractUpmappingId entity provides the base persistence definition of the
 * UpmappingId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUpmappingId implements java.io.Serializable {

	// Fields

	private User user;
	private Project project;

	// Constructors

	/** default constructor */
	public AbstractUpmappingId() {
	}

	/** full constructor */
	public AbstractUpmappingId(User user, Project project) {
		this.user = user;
		this.project = project;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractUpmappingId))
			return false;
		AbstractUpmappingId castOther = (AbstractUpmappingId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null
				&& castOther.getUser() != null && this.getUser().equals(
				castOther.getUser())))
				&& ((this.getProject() == castOther.getProject()) || (this
						.getProject() != null && castOther.getProject() != null && this
						.getProject().equals(castOther.getProject())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result
				+ (getProject() == null ? 0 : this.getProject().hashCode());
		return result;
	}

}