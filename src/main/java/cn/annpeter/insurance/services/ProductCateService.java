package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.ProductCate;
import cn.annpeter.insurance.utils.Exceptions.NotFoundException;
import net.sf.json.JSONArray;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by annpeter on 3/21/16.
 */
@Service
public class ProductCateService{

    @Resource
    SafeDeleteDao safeDeleteDao;

    public List<ProductCate> list() throws Exception {
        JSONArray jsonArray = new JSONArray();
        String sql = "SELECT id, pId, name FROM jt_productcate WHERE _delete != 1;";
        return safeDeleteDao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }


    public ProductCate getById(int id) throws Exception {
        return (ProductCate)safeDeleteDao.getObject("jt_productcate", " id = "+id);
    }

    public void delete(int id) throws Exception {
        ProductCate productCate = getById(id);
        delete(productCate);
    }

    private void delete(ProductCate productCate) throws Exception {
        if(productCate!=null){
            safeDeleteDao.delete(productCate);

            List list = productCate.getChildren();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                ProductCate temp = (ProductCate)iterator.next();
                //循环删除子节点
                delete(temp);
            }
        }
    }

    public void saveorupdate(int pid, int id, String name) throws Exception {
        ProductCate productCate = getById(id);
        if(productCate == null){
            productCate = new ProductCate();
            productCate.setId(id);
        }
        productCate.setName(name);
        ProductCate pProductCate = getById(pid);
        if(productCate == null){
            throw new NotFoundException("没有找到pid为" + pid + "的父节点");
        }
        productCate.setParent(pProductCate);

        safeDeleteDao.save(productCate);
    }

}
