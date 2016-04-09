package cn.annpeter.insurance.entities.JsonBean.shoopingcart;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annpeter on 3/20/16.
 */
@Component
public class JsonReqShoppingCartModify {
    Integer member_id;

    List<JsonShoppingCartModify> jsonShoppingCartModifies;

    JsonReqShoppingCartModify(){
        if(jsonShoppingCartModifies == null){
            jsonShoppingCartModifies = new ArrayList<>();
        }
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public List<JsonShoppingCartModify> getJsonShoppingCartModifies() {
        return jsonShoppingCartModifies;
    }

    public void setJsonShoppingCartModifies(List<JsonShoppingCartModify> jsonShoppingCartModifies) {
        this.jsonShoppingCartModifies = jsonShoppingCartModifies;
    }


}
