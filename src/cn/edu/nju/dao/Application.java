package cn.edu.nju.dao;

/**
 * Application entity. @author MyEclipse Persistence Tools
 */
public class Application extends AbstractApplication implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Application() {
	}

	/** full constructor */
	public Application(ApplicationId id, Integer amount, Short state) {
		super(id, amount, state);
	}

}
