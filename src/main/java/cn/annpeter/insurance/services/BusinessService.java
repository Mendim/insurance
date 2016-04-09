package cn.annpeter.insurance.services;


import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Business;
import cn.annpeter.insurance.entities.JsonBean.busniess.JsonBussinessCommit;
import cn.annpeter.insurance.entities.JsonBean.busniess.JsonReqBusinessCommit;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.Exceptions.DataErrorException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by annpeter on 3/28/16.
 */
@Service
public class BusinessService{

    @Resource
    SafeDeleteDao safeDeleteDao;


    public void save(List<Business> businessList) throws Exception {

        safeDeleteDao.save(businessList);
    }

    public Business getById(int id) throws Exception {
        return (Business)safeDeleteDao.getObject("jt_business", " id = "+ id);
    }

    public boolean isValidOrderId(String orderId) throws Exception {
        return safeDeleteDao.getCount("jt_business", " order_id = '" +orderId +"'") == 0 ? true : false;
    }
}
