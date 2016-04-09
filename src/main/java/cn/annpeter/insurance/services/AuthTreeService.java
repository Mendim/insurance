package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.entities.auth.AuthTree;
import net.sf.json.JSONArray;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 3/23/16.
 */
@Service
public class AuthTreeService{

    @Resource
    Dao dao;

    public List<AuthTree> list() throws Exception {
        String sql = "SELECT id, pId, name FROM jt_auth_tree WHERE id != 1;";
        return dao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    public AuthTree getTree() throws Exception {
        return (AuthTree)dao.getObject("jt_auth_tree", "id = 1");
    }
}
