package cn.edu.nju.dao;

import java.util.Date;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * Application entity. @author MyEclipse Persistence Tools
 */
public class Application extends AbstractApplication implements
		java.io.Serializable {
	public static final short WAITLEADER=0;
	public static final short WAITCHECKER=1;
	public static final short SUCCESS=2;
	public static final short REWRITE=3;
	
	// Constructors

	/** default constructor */
	public Application() {
	}

	/** full constructor */
	public Application(ApplicationId id, Integer amount, Short state, Date time) {
		super(id, amount, state, time);
	}

}
