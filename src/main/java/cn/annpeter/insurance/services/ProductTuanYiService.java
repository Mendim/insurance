package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Score;
import cn.annpeter.insurance.entities.products.Product;
import cn.annpeter.insurance.entities.products.ProductTuanYi;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by annpeter on 4/1/16.
 */
@Service
public class ProductTuanYiService{

    @Resource
    SafeDeleteDao safeDeleteDao;

    @Resource
    ProductCateService productCateService;
    @Resource
    SupplierService supplierService;
    @Resource
    ScoreService scoreService;
    @Resource
    ProductService productService;

    public void save(ProductTuanYi newProductTuanYi, Score score) throws Exception {
        Product product = newProductTuanYi.getProduct();

        score.setProduct_id(product.getId());
        scoreService.save(score);

        Integer productId = (Integer)productService.save(product);

        newProductTuanYi.setProduct_id(productId);
        safeDeleteDao.save(newProductTuanYi);
    }


    public List<Object> list() throws Exception {
        String sql = "SELECT s.id AS supplierId, s.title AS supplierTitle, p.title AS productTitle, t.product_id AS productId, " +
                            " (SELECT  COUNT(t.is_pri) " +
                                " FROM jt_p_tuanyi AS t LEFT JOIN jt_product AS p ON t.product_id = p.id " +
                                    " LEFT JOIN jt_supplier AS s ON s.id = p.supplier_id " +
                                        " WHERE (t.is_pri = 0 AND p._delete != 1 AND s._delete != 1) GROUP BY s.id ) AS attachCount " +
                        " FROM jt_p_tuanyi AS t LEFT JOIN jt_product AS p ON t.product_id = p.id " +
                                    " LEFT JOIN jt_supplier AS s ON s.id = p.supplier_id " +
                                    " WHERE (t.is_pri = 1 AND p._delete != 1 AND s._delete != 1) GROUP BY s.id;";

        return safeDeleteDao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    public List<ProductTuanYi> listAttach(int productId) throws Exception{
        return (List<ProductTuanYi>)safeDeleteDao.list("jt_p_tuanyi", " is_pri = 0 AND  product_id = " + productId);
    }

}
