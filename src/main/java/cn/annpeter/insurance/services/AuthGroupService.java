package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.Exceptions.NotFoundException;
import cn.annpeter.insurance.utils.Exceptions.SetValueErrorException;
import cn.org.rapid_framework.page.Page;
import org.apache.log4j.Level;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by annpeter on 3/23/16.
 */
@Service
public class AuthGroupService{

    @Resource
    SafeDeleteDao safeDeleteDao;
    @Resource
    Dao dao;


    public List<AuthGroup> list()throws Exception {
        return (List<AuthGroup>)safeDeleteDao.list("jt_auth_group", null);
    }


    public AuthGroup getById(int groupId) throws Exception {
        return (AuthGroup)safeDeleteDao.getObject("jt_auth_group", "id = "+groupId);
    }

    private void update(AuthGroup newAuthGroup)throws Exception {
        AuthGroup oldAuthGroup = getById(newAuthGroup.getId());
        String[] modifyProperties=new String[]{"name", "description", "authRule"};

        CommonUtils.modifyObj(newAuthGroup, oldAuthGroup, modifyProperties);

        safeDeleteDao.update(oldAuthGroup);
    }

    private void save(AuthGroup authGroup) throws Exception {
        safeDeleteDao.save(authGroup);
    }

    public void saveOrUpdate(AuthGroup authGroup) throws Exception {
        if(authGroup.getId() != null){
            update(authGroup);
        }else {
            save(authGroup);
        }
    }

    public void delete(int groupId) throws Exception {
        safeDeleteDao.delete(getById(groupId));
    }

    /**
     * 查看用户在此url上是否有权限
     * @param memberLogined
     * @param action
     * @param method
     * @return
     * @throws Exception
     */
    public boolean checkActionMethodVaild(Administrator memberLogined, String action, String method) throws Exception  {
        //获取用户分组
        Integer groupId = memberLogined.getGroup_id();
        AuthGroup authGroup = getById(groupId);

        //获取分组的权限id
        String authRuleStr = authGroup.getAuthRule();
        if(authRuleStr == null || authRuleStr.length() == 0 ){
            return false;
        }else {
            String[] authRules = authRuleStr.split(",");
            String orStr = "";
            for(int i = 0; i < authRules.length; i++){
                if(i == 0){
                    orStr = "( id = " + authRules[i];
                }else{
                    orStr += " OR id = " + authRules[i];
                }
            }
            String where = orStr + ") And method='"+method+"' And action='"+action+"'";

            return dao.getCount("jt_auth_tree", where)==0 ? false : true;
        }
    }

    public ArrayList<AuthGroup> queryByPage(final String where, final Page page) throws Exception {
        return (ArrayList<AuthGroup>) safeDeleteDao.listByPage("jt_auth_group", where, page);
    }

    public long getCount(String where) throws Exception {
        return safeDeleteDao.getCount("jt_auth_group", null);
    }
}
