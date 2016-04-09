package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Supplier;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.org.rapid_framework.page.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by annpeter on 3/20/16.
 */
@Service
public class SupplierService{

    @Resource
    SafeDeleteDao safeDeleteDao;

    public List<Supplier> list() throws Exception {
        return (List<Supplier>) safeDeleteDao.list("jt_supplier", null);
    }

    private void save(Supplier supplier) throws Exception {
        safeDeleteDao.save(supplier);
    }

    private void update(Supplier newSupplier) throws Exception {
        Supplier oldSupplier = getById(newSupplier.getId());
        String[] properties = new String[]{"title", "logo", "etitle", "activeurl", "tel"};
        CommonUtils.modifyObj(newSupplier, oldSupplier, properties);
        safeDeleteDao.update(oldSupplier);
    }

    public Supplier saveOrUpdate(Supplier supplier) throws Exception {
        if(supplier.getId() != null){
            update(supplier);
        }else{
            save(supplier);
        }
        return supplier;
    }

    public Supplier getById(int id) throws Exception {
        return (Supplier)safeDeleteDao.getObject("jt_supplier", " id = " + id);
    }

    public void delete(int id) throws Exception {
        Supplier supplier = getById(id);
        safeDeleteDao.delete(supplier);
    }

    public ArrayList<Supplier> queryByPage(final Page page) throws Exception {
        return (ArrayList<Supplier>) safeDeleteDao.listByPage("jt_supplier", null, page);
    }

    public long getCount(String where) throws Exception {
        return safeDeleteDao.getCount("jt_supplier", where);
    }
}
