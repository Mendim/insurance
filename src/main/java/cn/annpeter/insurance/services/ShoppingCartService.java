package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.Dao;
import cn.annpeter.insurance.entities.JsonBean.shoopingcart.JsonReqShoppingCartModify;
import cn.annpeter.insurance.entities.JsonBean.shoopingcart.JsonShoppingCartModify;
import cn.annpeter.insurance.entities.ShoppingCart;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by annpeter on 3/19/16.
 */
@Service
public class ShoppingCartService{

    @Resource
    Dao dao;

    public List<Object> list(int member_id) throws Exception {

        String sql = "SELECT p.id AS productId, p.title AS productTitle, s.id AS supplierId, s.title AS supplierTitle, " +
                        " s.logo AS supplierLogo, k.price AS kadanPrice, k.img_small AS kadanImgSmall, sh.num AS cardNum" +
                            " FROM jt_shoppingcart  sh LEFT JOIN jt_product AS  p ON sh.product_id = p.id " +
                                                    " LEFT  JOIN jt_p_kadan AS k ON p.id = k.product_id " +
                                                    " LEFT JOIN jt_supplier AS s ON s.id = p.supplier_id " +
                                                    " WHERE p._delete != 1 AND sh.member_id = "+member_id;

        return dao.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    /**
     * 兼具添加,修改,删除功能
     * @param cartModify
     * @throws Exception
     */
    public void modify(JsonReqShoppingCartModify cartModify) throws Exception {

        Integer memberId = cartModify.getMember_id();

        List<ShoppingCart> oldList = (List<ShoppingCart>)dao.list("jt_shoppingcart", " member_id = " + memberId);
        if(oldList == null){
            oldList = new ArrayList<>();
        }

        List<JsonShoppingCartModify> newList = cartModify.getJsonShoppingCartModifies();

        //根据newList来判断oldList中的操作.
        Iterator<ShoppingCart> oldIt = oldList.iterator();
        Iterator<JsonShoppingCartModify> newIt = newList.iterator();

        while (newIt.hasNext()){
            JsonShoppingCartModify newItem = newIt.next();
            Integer id = newItem.getId();
            Integer num = newItem.getNum();

            boolean oldHave = false;    //判断当前item在oldList中是否存在
            //如果存在就进行修改即可(如果为0则删除)
            //如果不存在,则新增.
            while (oldIt.hasNext()){
                ShoppingCart oldItem = oldIt.next();

                if(oldItem.getProduct_id() == id){
                    //新的num为0,则从购物车中删除
                    if(num == 0){
                        dao.delete(oldItem);
                    }else{
                        //如果新的num和老的num不同,则更新
                        if(num != oldItem.getNum()){
                            //update
                            oldItem.setNum(num);
                            dao.update(oldItem);
                        }
                    }
                    oldHave = true;
                    break;
                }
            }

            //如果在遍历oldList后oldHave依然为false,则表明当前item在oldList中不存在,则进行增加操作
            if(oldHave == false){
                //insert
                ShoppingCart newShoppingCartItem = new ShoppingCart(memberId, id, num);
                dao.save(newShoppingCartItem);
            }
        }
    }



}
