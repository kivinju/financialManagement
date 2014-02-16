package cn.edu.nju.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Application entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.nju.dao.Application
 * @author MyEclipse Persistence Tools
 */

public class ApplicationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationDAO.class);
	// property constants
	public static final String AMOUNT = "amount";
	public static final String STATE = "state";

	protected void initDao() {
		// do nothing
	}

	public void save(Application transientInstance) {
		log.debug("saving Application instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Application persistentInstance) {
		log.debug("deleting Application instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Application findById(cn.edu.nju.dao.ApplicationId id) {
		log.debug("getting Application instance with id: " + id);
		try {
			Application instance = (Application) getHibernateTemplate().get(
					"cn.edu.nju.dao.Application", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Application instance) {
		log.debug("finding Application instance by example");
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
		log.debug("finding Application instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Application as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Application instances");
		try {
			String queryString = "from Application";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Application merge(Application detachedInstance) {
		log.debug("merging Application instance");
		try {
			Application result = (Application) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Application instance) {
		log.debug("attaching dirty Application instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Application instance) {
		log.debug("attaching clean Application instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ApplicationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ApplicationDAO) ctx.getBean("ApplicationDAO");
	}

	public List<Application> findByProjectId(Integer pid,
			short applicationRole) {
		String queryString = "from Application as model where model.id.project.pid= "+pid+" and model.state = ?";
		return getHibernateTemplate().find(queryString, applicationRole);
	}
}