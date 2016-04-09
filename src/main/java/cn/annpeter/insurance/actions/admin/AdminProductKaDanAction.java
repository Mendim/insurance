package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseRequestAction;
import cn.annpeter.insurance.entities.Member;
import cn.annpeter.insurance.entities.Supplier;
import cn.annpeter.insurance.entities.products.ProductKaDan;
import cn.annpeter.insurance.services.ProductCateService;
import cn.annpeter.insurance.services.ProductKaDanService;
import cn.annpeter.insurance.services.SupplierService;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.WebUtils;
import cn.org.rapid_framework.page.Page;
import com.google.gson.Gson;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.List;


/**
 * Created by annpeter on 3/17/16.
 * 后台对卡单的增删改查操作
 */
@Namespace("/admin/productkadan")
@ParentPackage("adminDefault")
@Controller
public class AdminProductKaDanAction extends BaseRequestAction {

    @Resource
    ProductKaDanService productKaDanService;
    @Resource
    SupplierService supplierService;
    @Resource
    ProductCateService productCateService;

    ProductKaDan productKaDan = null;
    private Page page = null;

    /**
     * 显示所有卡单
     * @return
     */
    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/productkadan/list.jsp")
    })
    public String list() throws Exception {
        List<ProductKaDan> kaDanArrayList = null;

        String objPage = ServletActionContext.getRequest().getParameter("page");

        if (objPage == null) page = new Page<Member>(0, Constant.pageSize, productKaDanService.getCount().intValue());
        else {
            page = new Page<Member>(Integer.parseInt(objPage), Constant.pageSize, productKaDanService.getCount().intValue());
        }

        kaDanArrayList = productKaDanService.list(page);

        getRequest().put("ProductKaDanArrayList", kaDanArrayList);
        getRequest().put("Page", page);

        return SUCCESS;
    }

    /**
     * @return
     */
    @Action(value = "addorprofile", results = {
            @Result(name = "success", location = "/views/admin/productkadan/addOrProfile.jsp")
    })
    public String addorprofile() throws Exception {
        if(productKaDan != null){
            productKaDanService.saveOrUpdate(productKaDan);
            //重定向至list页面
            WebUtils.redrictToUrl(getContextPath()+"/admin/productkadan/list");
            return null;
        }else {
            String id = getReqParameter("id");
            if(id != null){
                productKaDan = productKaDanService.getById(Integer.parseInt(id));
                getRequest().put("ProductKaDan", productKaDan);
            }
        }
        //获取所有的提供商
        List<Supplier> supplierArrayList = supplierService.list();
        getRequest().put("SupplierArrayList", supplierArrayList);

        //所有的产品类别
        List<?> productCateList =  productCateService.list();
        Gson gson = new Gson();
        String jsonTree = gson.toJson(productCateList);
        getRequest().put("jsonTree", jsonTree);
        return SUCCESS;
    }


    @Action(value = "delete", results = {

    })
    public String delete() throws Exception {
        String id = getReqParameter("id");

        productKaDanService.deleteById(Integer.valueOf(id));

        //重定向至list页面
        WebUtils.redrictToUrl(getContextPath()+"/admin/productkadan/list");
        return null;
    }

    /**
     * set和get主要是提供给strtus提交表单时初始化productKaDan使用
     * get方法可有可无,但一定要有set方法
     * @return
     */
    public ProductKaDan getProductKaDan() {
        return productKaDan;
    }
    public void setProductKaDan(ProductKaDan productKaDan) {
        this.productKaDan = productKaDan;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
