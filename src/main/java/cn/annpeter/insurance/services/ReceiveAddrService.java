package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.entities.ReceiveAddr;
import cn.annpeter.insurance.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 3/21/16.
 */
@Service
public class ReceiveAddrService{
    @Resource
    Dao dao;

    public List<ReceiveAddr> list(int memberId) throws Exception {
        return  (List<ReceiveAddr>)dao.list("jt_address", " member_id = "+memberId);
    }

    public ReceiveAddr getById(int id) throws Exception {
        return (ReceiveAddr)dao.getObject("jt_address", "id = " + id);
    }

    public void add(ReceiveAddr receiveAddr) throws Exception {
        dao.save(receiveAddr);
    }

    public void update(ReceiveAddr newReceiveAddr) throws Exception {
        ReceiveAddr oldReceiveAddr = getById(newReceiveAddr.getId());
        String[] properties = new String[]{"id", "member_id", "isdefault", "address", "contactor", "tel"};

        CommonUtils.modifyObj(newReceiveAddr, oldReceiveAddr, properties);
        dao.update(oldReceiveAddr);
    }

    public void delete(int id) throws Exception {
        ReceiveAddr receiveAddr = getById(id);
        dao.delete(receiveAddr);
    }
}
