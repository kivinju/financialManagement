package cn.edu.nju.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Upmapping entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.nju.dao.Upmapping
 * @author MyEclipse Persistence Tools
 */

public class UpmappingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UpmappingDAO.class);
	// property constants
	public static final String UPROLE = "uprole";

	protected void initDao() {
		// do nothing
	}

	public void save(Upmapping transientInstance) {
		log.debug("saving Upmapping instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Upmapping persistentInstance) {
		log.debug("deleting Upmapping instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Upmapping findById(cn.edu.nju.dao.UpmappingId id) {
		log.debug("getting Upmapping instance with id: " + id);
		try {
			Upmapping instance = (Upmapping) getHibernateTemplate().get(
					"cn.edu.nju.dao.Upmapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Upmapping instance) {
		log.debug("finding Upmapping instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Upmapping instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Upmapping as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUprole(Object uprole) {
		return findByProperty(UPROLE, uprole);
	}

	public List findAll() {
		log.debug("finding all Upmapping instances");
		try {
			String queryString = "from Upmapping";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Upmapping merge(Upmapping detachedInstance) {
		log.debug("merging Upmapping instance");
		try {
			Upmapping result = (Upmapping) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Upmapping instance) {
		log.debug("attaching dirty Upmapping instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Upmapping instance) {
		log.debug("attaching clean Upmapping instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UpmappingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UpmappingDAO) ctx.getBean("UpmappingDAO");
	}
}