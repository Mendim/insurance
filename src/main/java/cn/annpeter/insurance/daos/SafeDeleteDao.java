package cn.annpeter.insurance.daos;

import cn.annpeter.insurance.utils.CommonUtils;
import cn.org.rapid_framework.page.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 * 使用此dao的对象, 他们的entities都含有delete字段,用于表明是否已经被删除
 * delete字段为0表示没有被删除,在save的时候赋值为0
 * delete字段为1表示已删除,在delete的时候update为1
 * 删除后该记录对系统不可见,但是没有物理删除,即对所有查询方法不可见
 */
@Repository
public class SafeDeleteDao extends Dao{


	/**
	 * 保存对象
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Serializable save(Object obj) throws Exception {
        CommonUtils.setFieldValueByName("_delete", obj, (short)0);
		return getSession().save(obj);
	}

    /**
     * 删除对象
     * @param obj
     * @throws Exception
     */
    public void delete(Object obj) throws Exception {
        if(obj == null){
            throw new NullPointerException("待删除对象为空");
        }
        //将delete字符设置为1
        CommonUtils.setFieldValueByName("_delete", obj, (short)1);
        update(obj);
    }

    /**
     * 查找对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public Long getCount(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? where + " AND _delete != 1" : " _delete != 1";
        return super.getCount(tableName, where);
    }

    /**
     * 查找对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public Object getObject(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? where + " AND _delete != 1" : " _delete != 1";
        return super.getObject(tableName, where);
    }

    /**
     * 查找所有对象
     * @param tableName
     * @param where
     * @return
     * @throws Exception
     */
    public List<?> list(String tableName, String where) throws Exception {
        where = (where != null && where.length() != 0) ? where + " AND _delete != 1" : " _delete != 1";
        return super.list(tableName, where);
    }

	/**
	 * 分页查找
	 * @param tableName
	 * @param where
     * @param page
	 * @return
	 * @throws Exception
	 */
    public List<?> listByPage(String tableName, String where, Page<Object> page) throws Exception {
        where = (where != null && where.length() != 0) ? where + " AND _delete != 1" : " _delete != 1";
        return super.listByPage(tableName, where, page);
    }
}


