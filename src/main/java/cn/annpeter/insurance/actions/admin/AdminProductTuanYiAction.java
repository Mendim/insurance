package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.entities.Score;
import cn.annpeter.insurance.entities.Supplier;
import cn.annpeter.insurance.entities.products.ProductTuanYi;
import cn.annpeter.insurance.services.ProductCateService;
import cn.annpeter.insurance.services.ProductTuanYiService;
import cn.annpeter.insurance.services.SupplierService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by annpeter on 4/1/16.
 */
@Namespace("/admin/producttuanyi")
@ParentPackage("adminDefault")
@Controller
public class AdminProductTuanYiAction extends BaseJsonResAction{

    @Resource
    ProductTuanYiService productTuanYiService;
    @Resource
    SupplierService supplierService;
    @Resource
    ProductCateService productCatService;

    ProductTuanYi productTuanYi;
    Score score;

    @Action(value = "addorprofile", results = {
            @Result(name = "success", location = "/views/admin/producttuanyi/addOrProfile.jsp"),
            @Result(name = "json", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String addorprofile() throws Exception {
        if(productTuanYi != null){

            //添加
            if(productTuanYi.getId() == null){
                productTuanYiService.save(productTuanYi, score);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("productId", productTuanYi.getProduct_id());

                sendSuccessMessage("操作成功, 是否继续添加附加险种", jsonObject);
                return JSON;
            }else{//修改

            }
        }

        // 如果productIdStr不为空,去添加附加险页面
        // 如果productIdStr为空,则去添加主险页面
        String productIdStr = getReqParameter("productId");
        if(productIdStr != null){
            getRequest().put("ProductId", productIdStr);
        }


        //获取所有的提供商
        List<Supplier> supplierArrayList = supplierService.list();
        getRequest().put("SupplierArrayList", supplierArrayList);

        return SUCCESS;
    }


    /**
     * 根据提供商list所有团体意外险
     * @return
     * @throws Exception
     */
    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/producttuanyi/list.jsp")
    })
    public String list() throws Exception {
        List<Object> productTuanYiArrayList = productTuanYiService.list();
        getRequest().put("ProductTuanYiArrayList", productTuanYiArrayList);
        return SUCCESS;
    }


    /**
     * 显示所有的附加险,参数主险的productId
     * @return
     */
    @Action(value = "listattach", results = {
            @Result(name = "json", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String listAttach() throws Exception {
        String productIdStr = getReqParameter("id");

        Integer productId = Integer.parseInt(productIdStr);

        List<ProductTuanYi> productTuanYiAttachList= productTuanYiService.listAttach(productId);


        String template = "<div class='col-md-4'>" +
                "    <section class='tile color transparent-black'>" +
                "        <!-- tile header -->" +
                "        <!-- /tile header -->" +
                "        " +
                "        <!-- tile widget -->" +
                "        <div class='tile-widget'>" +
                "" +
                "" +
                "        </div>" +
                "        <!-- /tile widget -->" +
                "" +
                "        <!-- tile body -->" +
                "        <div class='tile-body'>" +
                "" +
                "        </div>" +
                "        <!-- /tile body -->" +
                "" +
                "        <!-- tile footer -->" +
                "        <!-- /tile footer -->" +
                "    </section>" +
                "</div>";

        for (ProductTuanYi item: productTuanYiAttachList){

        }

        return JSON;
    }



    public ProductTuanYi getProductTuanYi() {
        return productTuanYi;
    }

    public void setProductTuanYi(ProductTuanYi productTuanYi) {
        this.productTuanYi = productTuanYi;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
