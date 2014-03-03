package cn.edu.nju.entity;

import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	public final static Short ROLE_MANAGER=0;
	public final static Short ROLE_USER=1;
	public final static Short ROLE_CHECKER=2;
	public final static Short ROLE_DIRECTOR=3;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String cardnum, String banknum, Short role, String uname,
			String password) {
		super(cardnum, banknum, role, uname, password);
	}

	/** full constructor */
	public User(String cardnum, String banknum, Short role, String uname,
			String password, Set applications, Set upmappings) {
		super(cardnum, banknum, role, uname, password, applications, upmappings);
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "uid:"+getUid()+" name:"+getUname()+ " role:"+getRole();
}
}