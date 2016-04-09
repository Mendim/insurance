package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.products.Product;
import cn.annpeter.insurance.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by annpeter on 4/6/16.
 */
@Service
public class ProductService {
    @Resource
    SafeDeleteDao safeDeleteDao;

    public Product getById(int id) throws Exception {
        return (Product)safeDeleteDao.getObject("jt_product", " id = "+id);
    }

    /**
     * 修改时一定要将product的id传入
     * @param product
     * @throws Exception
     */
    public void update(Product product) throws Exception {
        Product oldProduct = getById(product.getId());
        String[] propertiesProduct = new String[]{"title", "summary", "cate_id", "supplier_id", "start_date", "end_date", "sort"};
        CommonUtils.modifyObj(product, oldProduct, propertiesProduct);
        safeDeleteDao.update(oldProduct);
    }

    public Serializable save(Product product) throws Exception {
        return safeDeleteDao.save(product);
    }
}
