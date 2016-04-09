package cn.annpeter.insurance.daos;

import cn.org.rapid_framework.page.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.List;

@Repository
public class Dao {

	@Resource
    SessionFactory sessionFactory;

	/**
	 * 保存对象
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Serializable save(Object obj) throws Exception {
		return getSession().save(obj);
	}

    /**
     * 删除对象
     * @param obj
     * @throws Exception
     */
    public void delete(Object obj) throws Exception {
        getSession().delete(obj);
    }

    /**
	 * 修改对象
	 * @param obj
	 * @throws Exception
	 */
	public void update(Object obj) throws Exception {
		getSession().update(obj);
	}

    /**
     * 查找对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public Long getCount(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? " WHERE "+where : "";
        String sql = MessageFormat.format("SELECT COUNT(*) FROM {0} {1}", tableName, where);
        return  ((BigInteger) getSession().createSQLQuery(sql).uniqueResult()).longValue();
    }

    /**
     * 查找对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public Object getObject(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? " WHERE "+where : "";
        String hql = MessageFormat.format("FROM {0} {1}", tableName, where);
        return getSession().createQuery(hql).uniqueResult();
    }

    /**
     * 查找所有对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public List<?> list(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? " WHERE "+where : "";
        String hql = MessageFormat.format("FROM {0} {1}", tableName, where);
        Query query = getSession().createQuery(hql);
        return query.list();
    }

	/**
	 * 分页查找
	 * @param tableName
	 * @param where
     * @param page
	 * @return
	 * @throws Exception
	 */
    public List<?> listByPage(String tableName, String where, final Page<Object> page) throws Exception {
        where = (where != null && where.length() != 0) ? " WHERE "+where : "";
        String hql = MessageFormat.format("FROM {0} {1}", tableName, where);
        Query query = getSession().createQuery(hql);
        query.setMaxResults(page.getPageSize());
        query.setFirstResult(page.getFirstResult());
        return query.list();
    }


    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public Session openSession(){
        return sessionFactory.openSession();
    }
}


