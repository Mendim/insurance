package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Member;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.org.rapid_framework.page.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by yt on 2016/3/20.
 */
@Service
public class MemberService{
    @Resource
    SafeDeleteDao safeDeleteDao;

    /**
     * 目前支持account为mobile
     * @param account
     * @param passwd
     * @return
     * @throws Exception
     */
    public Member signIn(String account, String passwd) throws Exception {
        String where = " mobile = '"+ account+"' AND userpwd = '"+ passwd+"'";
        return (Member)safeDeleteDao.getObject("jt_member", where);
    }

    /**
     * 已注册返回true,没有注册返回false
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean checkMobileExists(String mobile) throws Exception {
        String where = " mobile = '"+ mobile+"'";
        return safeDeleteDao.getCount("jt_member", where)==0 ? false:true;
    }

    /**
     * 管理员修改用户的信息
     * @param newMember
     * @throws Exception
     */
    public void adminUpdate(Member newMember) throws Exception {
        //使用新的member查找老的member，避免不可改变的区域被新的用户修改

        Member oldMember = (Member) safeDeleteDao.getObject("jt_member", " id = "+ newMember.getId());

        String[] modifyProperties=new String[]{"username", "headimg", "sn", "city", "mobile", "tel_num", "qq", "email","fax"};

        CommonUtils.modifyObj(newMember,oldMember,modifyProperties);
        safeDeleteDao.update(oldMember);
    }


    public void save(Member member) throws Exception {
        safeDeleteDao.save(member);
    }

    public Member getById(int id) throws Exception {
        return (Member)safeDeleteDao.getObject("jt_member", " id = "+id);
    }


    public ArrayList<Member> queryByPage(final String where, final Page page) throws Exception {
        return (ArrayList<Member>) safeDeleteDao.listByPage("jt_member", where, page);
    }

    public void delete(int id) throws Exception {
        Member member = getById(id);
        safeDeleteDao.delete(member);
    }

    public void updatePasswd(String mobile, String userpwd) throws Exception{
        String hql = "UPDATE jt_member m set m.userpwd = '"+ userpwd +"' WHERE m.mobile = '" + mobile +"' AND _delete != 1";
        safeDeleteDao.getSession().createQuery(hql).executeUpdate();
    }

    public Long getCount(String where) throws Exception {
        return safeDeleteDao.getCount("jt_member", where);
    }
}
