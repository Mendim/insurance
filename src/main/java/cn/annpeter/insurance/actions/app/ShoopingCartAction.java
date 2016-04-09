package cn.annpeter.insurance.actions.app;

import cn.annpeter.insurance.actions.BaseJsonReqAction;
import cn.annpeter.insurance.entities.JsonBean.shoopingcart.JsonReqShoppingCartModify;
import cn.annpeter.insurance.services.ShoppingCartService;
import cn.annpeter.insurance.utils.Constant;
import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by annpeter on 3/19/16.
 */
@Namespace("/app/shoppingcart")
@ParentPackage("appDefault")
@Controller
public class ShoopingCartAction extends BaseJsonReqAction {

    @Resource
    ShoppingCartService shoppingCartService;

    @Action(value = "list", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String list() throws Exception {

        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在请求购物车列表");
        }

        String memberIdStr = getReqParameter("id");

        if(memberIdStr == null|| !Pattern.compile("^[0-9]*$").matcher(memberIdStr).find()){
            sendFailMessage("请发送正确的用户id");
            return SUCCESS;
        }
        int memberId = Integer.valueOf(memberIdStr);


        List shoppingCartList = shoppingCartService.list(memberId);

        sendSuccessMessage("获取购物车成功", shoppingCartList);
        return SUCCESS;
    }


    /**
     * 兼具添加, 删除, 修改的功能
     * @return
     */
    @Action(value = "modify", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String modify() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在修改购物车");
        }

        if(getReqJsonStr() == null){
            sendFailMessage("数据格式错误, 格式应为{'reqJsonStr':json}");
            return SUCCESS;
        }

        Gson gson = new Gson();
        JsonReqShoppingCartModify jsonReqShoppingCartModify =  gson.fromJson(getReqJsonStr(), JsonReqShoppingCartModify.class);

        if(jsonReqShoppingCartModify.getMember_id() == null){
            sendFailMessage("数据格式错误, 用户id为空");
            return SUCCESS;
        }

        shoppingCartService.modify(jsonReqShoppingCartModify);

        sendSuccessMessage("操作成功", new ArrayList<>());
        return SUCCESS;
    }

}
