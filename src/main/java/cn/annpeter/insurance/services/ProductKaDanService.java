package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.JsonBean.productkadan.JsonResProductKaDanProfile;
import cn.annpeter.insurance.entities.products.Product;
import cn.annpeter.insurance.entities.products.ProductKaDan;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.org.rapid_framework.page.Page;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 3/14/16.
 */
@Service
public class ProductKaDanService{

    @Resource
    SafeDeleteDao safeDeleteDao;
    @Resource
    Dao dao;

    @Resource
    ProductService productService;


    public List<ProductKaDan> list(Page page) throws Exception{
        String hql = "SELECT new cn.annpeter.insurance.entities.products.ProductKaDan(k, p) " +
                "FROM jt_p_kadan k, jt_product p WHERE k.product_id = p.id AND p._delete != 1";
        Query query = safeDeleteDao.getSession().createQuery(hql);
        query.setMaxResults(page.getPageSize());
        query.setFirstResult(page.getFirstResult());
        return query.list();
    }


    private void save(ProductKaDan productKaDan) throws Exception {
        //保存产品
        Integer productId = (Integer) productService.save(productKaDan.getProduct());

        productKaDan.setProduct_id(productId);
        //保存卡单
        dao.save(productKaDan);
    }




    /**
     * 这里使用了new出来的新的对象,所以update的时候不能使用此对象对session来更新
     * @param id
     * @return
     * @throws Exception
     */
    public Object jsonById(int id) throws Exception {
        String sql = "SELECT p.id AS productId, p.title AS productTitle, p.summary AS productSummary, k.description AS kadanDescription," +
                    " k.price AS kadanPrice, k.img_big AS kadanImgBig, k.conditions AS kadanConditions, k.html_info AS kadanHtmlInfo," +
                    " s.title AS supplierTitle, c.name AS productCateName" +
                        " FROM jt_p_kadan AS k LEFT JOIN jt_product AS p ON k.product_id = p.id " +
                        " LEFT JOIN jt_supplier AS s ON p.supplier_id = s.id " +
                        " LEFT JOIN jt_productcate AS c ON p.cate_id = c.id " +
                        " WHERE p._delete != 1 AND p.id = " +id;
        return dao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    public void deleteById(int id) throws Exception {
        //删除产品即可, 卡单的存在看产品的_delete是否为0
        safeDeleteDao.delete(getById(id).getProduct());
    }

    public void saveOrUpdate(ProductKaDan kaDan) throws Exception {
        if(kaDan.getId()!=null){
            update(kaDan);
        }else {
            save(kaDan);
        }
    }

    public ProductKaDan getById(int id) throws Exception {
        return (ProductKaDan)dao.getObject("jt_p_kadan", " id = "+ id);
    }

    private void update(ProductKaDan newKaDan) throws Exception {
        ProductKaDan oldKaDan = (ProductKaDan)dao.getObject("jt_p_kadan", " id = "+newKaDan.getId());

        //将原来product的id赋值给新的product,然后执行product的修改
        Product newProduct = newKaDan.getProduct();
        newProduct.setId(oldKaDan.getProduct_id());
        productService.update(newProduct);

        String[] propertiesKaDan = new String[]{"img_big", "img_small", "description", "price",
                                "conditions", "html_info"};
        CommonUtils.modifyObj(newKaDan, oldKaDan, propertiesKaDan);

        dao.update(oldKaDan);
    }

    public Long getCount() throws Exception {
        String hql = "SELECT COUNT(*) " +
                "FROM jt_p_kadan k, jt_product p WHERE k.product_id = p.id AND p._delete != 1 ";
        return  (Long) safeDeleteDao.getSession().createQuery(hql).uniqueResult();
    }

    public List<Object> jsonList(){
        String sql = "SELECT p.id, p.title, k.price, k.description, k.img_small " +
                        " FROM jt_p_kadan AS k LEFT JOIN jt_product AS p ON k.product_id = p.id  WHERE p._delete != 1";
        return safeDeleteDao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    public Object jsonGetById(int id){
        String sql = "SELECT p.id AS productId, p.name AS productTitle, p.summary AS productSummary, k.description AS kadanDescription," +
                        " k.price AS kadanPrice, k.img_big AS kadanImgBig, k.conditions AS kadanConditions, k.html_info AS htmlInfo," +
                        " s.title AS supplierTitle, c.name AS productCateName" +
                            " FROM jt_p_kadan AS k LEFT JOIN jt_product AS p ON k.product_id = p.id " +
                                                " LEFT JOIN jt_supplier AS s ON p.supplier_id = s.id " +
                                                " LEFT JOIN jt_productcate AS c ON p.cate_id = c.id " +
                                                " WHERE p._delete != 1 AND p.id = " +id;
        return safeDeleteDao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }
}
