package cn.annpeter.insurance.entities.JsonBean.shoopingcart;

import org.springframework.stereotype.Component;

/**
 * Created by annpeter on 3/20/16.
 */
@Component
public class JsonShoppingCartModify {
    Integer id;   //产品id
    Integer num;    //产品当前的数量.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "JsonShoppingCartModify{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
