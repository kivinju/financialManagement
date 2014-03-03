package cn.edu.nju.entity;

import java.util.Set;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */
public class Item extends AbstractItem implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Item() {
	}

	/** minimal constructor */
	public Item(String name) {
		super(name);
	}

	/** full constructor */
	public Item(String name, Set applications, Set ipmappings) {
		super(name, applications, ipmappings);
	}

}
