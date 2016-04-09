package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.entities.Sms;
import cn.annpeter.insurance.utils.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
/**
 * Created by yt on 2016/3/29.
 */
@Service
public class SmsService{

    @Resource
    private Dao dao;

    public void save(Sms sms) throws Exception {
        dao.save(sms);
    }

    public Sms checkCode(String mobile, String code) throws Exception {
        return (Sms)dao.getObject("jt_sms"," mobile = '" + mobile + "' AND code = '"+code+"'");
    }
}
