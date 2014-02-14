package cn.edu.nju.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String cardnum;
	private String banknum;
	private Short role;
	private String uname;
	private String password;
	private Set applications = new HashSet(0);
	private Set upmappings = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String cardnum, String banknum, Short role,
			String uname, String password) {
		this.cardnum = cardnum;
		this.banknum = banknum;
		this.role = role;
		this.uname = uname;
		this.password = password;
	}

	/** full constructor */
	public AbstractUser(String cardnum, String banknum, Short role,
			String uname, String password, Set applications, Set upmappings) {
		this.cardnum = cardnum;
		this.banknum = banknum;
		this.role = role;
		this.uname = uname;
		this.password = password;
		this.applications = applications;
		this.upmappings = upmappings;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getBanknum() {
		return this.banknum;
	}

	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}

	public Short getRole() {
		return this.role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set getApplications() {
		return this.applications;
	}

	public void setApplications(Set applications) {
		this.applications = applications;
	}

	public Set getUpmappings() {
		return this.upmappings;
	}

	public void setUpmappings(Set upmappings) {
		this.upmappings = upmappings;
	}

}