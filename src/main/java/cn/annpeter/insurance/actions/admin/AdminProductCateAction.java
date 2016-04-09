package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.services.ProductCateService;
import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 3/20/16.
 */
@Namespace("/admin/productcate")
@ParentPackage("adminDefault")
@Controller
public class AdminProductCateAction extends BaseJsonResAction{

    @Resource
    ProductCateService productCateService;


    /**
     * @return
     */
    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/productcate/list.jsp")
    })
    public String list() throws Exception {
        List<?> productCateList =  productCateService.list();
        Gson gson = new Gson();
        String jsonTree = gson.toJson(productCateList);
        getRequest().put("jsonTree", jsonTree);
        return SUCCESS;
    }

    @Action(value = "delete", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String delete() throws Exception {
        String id = getReqParameter("id");

        productCateService.delete(Integer.parseInt(id));
        sendSuccessMessage("成功删除", null);

        return SUCCESS;
    }


    @Action(value = "saveorupdate", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String saveorupdate() throws Exception {
        String pid = getReqParameter("pId");
        String id = getReqParameter("id");
        String name = getReqParameter("name");

        productCateService.saveorupdate(Integer.parseInt(pid), Integer.parseInt(id),name);
        sendSuccessMessage("操作成功", null);
        return SUCCESS;
    }


}
