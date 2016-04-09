package cn.annpeter.insurance.actions.app;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.services.ProductKaDanService;
import cn.annpeter.insurance.utils.Constant;
import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by annpeter on 3/14/16.
 *
 * 对于产品卡单的请求管理
 */
@Namespace("/app/kadan")
@ParentPackage("appDefault")
@Controller
public class ProductKaDanAction extends BaseJsonResAction {

    @Resource
    private ProductKaDanService productKaDanService;



    @Action(value = "profile", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String profile() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在请求卡单详情");
        }

        //当id有误时,返回错误信息
        String productIdStr = getReqParameter("id");

        if(productIdStr == null|| !Pattern.compile("^[0-9]*$").matcher(productIdStr).find()){
            sendFailMessage("请发送正确的产品id");
            return SUCCESS;
        }
        int productId = Integer.valueOf(productIdStr);

        Object productKaDan = productKaDanService.jsonById(productId);

        if(productKaDan == null){
            sendFailMessage("查询的卡单不存在");
            return  SUCCESS;
        }

        List resultList = new ArrayList<>();
        //这里有个小BUG,待解决,如果直接将productKaDan加入result会报错
        resultList.add(productKaDan);

        sendSuccessMessage("请求卡单成功", resultList);
        return SUCCESS;
    }



    @Action(value = "list", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String list() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在请求卡单列表");
        }
        List result = productKaDanService.jsonList();

        sendSuccessMessage("请求卡单列表成功", result);
        return SUCCESS;
    }

}
