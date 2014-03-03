package cn.edu.nju.entity;

import java.util.Date;
import java.util.Set;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
public class Project extends AbstractProject implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(Integer amount, Date beginDate, Date endDate) {
		super(amount, beginDate, endDate);
	}

	/** full constructor */
	public Project(Integer amount, Date beginDate, Date endDate,
			String description, Set upmappings, Set ipmappings, Set applications) {
		super(amount, beginDate, endDate, description, upmappings, ipmappings,
				applications);
	}

}
