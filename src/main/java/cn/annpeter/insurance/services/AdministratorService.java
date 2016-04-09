package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.Exceptions.NotFoundException;
import cn.org.rapid_framework.page.Page;
import org.apache.log4j.Level;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 4/4/16.
 */
@Service
public class AdministratorService {
    @Resource
    SafeDeleteDao safeDeleteDao;

    private void save(Administrator admin) throws Exception {
        safeDeleteDao.save(admin);
    }

    public Long getCount(String where) throws Exception {
        return safeDeleteDao.getCount("jt_administrator", where);
    }

    /**
     * 列出所有系统管理员
     * @param page
     * @return
     * @throws Exception
     */
    public List<Administrator> getByPage(Page page) throws Exception {
        return (List<Administrator>)safeDeleteDao.listByPage("jt_administrator", null, page);
    }

    /**
     * 列出当前组的所有管理员
     * @param page
     * @param groupId
     * @return
     * @throws Exception
     */
    public List<Administrator> getByPage(Page page, int groupId) throws Exception {
        return (List<Administrator>)safeDeleteDao.listByPage("jt_administrator", " group_id = "+ groupId, page);
    }


    public Administrator getById(int id) throws Exception {
        return (Administrator)safeDeleteDao.getObject("jt_administrator", "id = "+id);
    }

    private void update(Administrator newAdmin) throws Exception {
        Administrator oldAdmin = getById(newAdmin.getId());
        String[] modifyProperties=new String[]{"nickname", "adminpwd", "mobile", "email"};
        CommonUtils.modifyObj(newAdmin, oldAdmin, modifyProperties);
        safeDeleteDao.update(oldAdmin);
    }

    public void saveOrUpdate(Administrator admin) throws Exception {
        if(admin.getId() != null){
            update(admin);
        }else{
            save(admin);
        }
    }

    public void updateLoginTimeAndIp(Administrator admin) throws Exception {
        safeDeleteDao.update(admin);
    }

    public void deleteById(int adminId) throws Exception {
        Administrator admin = getById(adminId);
        safeDeleteDao.delete(admin);
    }

    public boolean checkPhone(String phone) throws Exception {
        Administrator admin = (Administrator)safeDeleteDao.getObject("jt_administrator", "phone = "+phone);
        return admin == null ? false : true;
    }

    public boolean checkNickname(String nickname) throws Exception {
        Administrator admin = (Administrator)safeDeleteDao.getObject("jt_administrator", "nickname = "+nickname);
        return admin == null ? false : true;
    }

    public Administrator login(String nicknameOrMobileOrEmail, String passwd) throws Exception {
        String where = "( nickname = '"+ nicknameOrMobileOrEmail +
                "' OR mobile = '"+ nicknameOrMobileOrEmail +
                "' OR email = '"+ nicknameOrMobileOrEmail+"') AND adminpwd = '"+ passwd+"'";
        return (Administrator)safeDeleteDao.getObject("jt_administrator", where);
    }

}
