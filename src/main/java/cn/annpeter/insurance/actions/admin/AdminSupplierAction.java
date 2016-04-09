package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.Supplier;
import cn.annpeter.insurance.services.SupplierService;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.WebUtils;
import cn.org.rapid_framework.page.Page;
import org.apache.struts2.ServletActionContext;
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
@Namespace("/admin/supplier")
@ParentPackage("adminDefault")
@Controller
public class AdminSupplierAction extends BaseJsonResAction{

    @Resource
    SupplierService supplierService;

    private Page page = null;
    private Supplier supplier = null;

    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/supplier/list.jsp")
    })
    public String list() throws Exception {

        String pageIndexStr = ServletActionContext.getRequest().getParameter("page");

        int pageIndex = (pageIndexStr!=null)?Integer.parseInt(pageIndexStr):0;
        page = new Page<Administrator>(pageIndex, Constant.pageSize, (int) supplierService.getCount(null));

        List<Supplier> supplierArrayList = supplierService.queryByPage( page);

        getRequest().put("SupplierArrayList", supplierArrayList);
        getRequest().put("Page", page);

        return SUCCESS;
    }


    /**
     * @return
     */
    @Action(value = "addorprofile", results = {
            @Result(name = "success", location = "/views/admin/supplier/addOrProfile.jsp")
    })
    public String addorprofile() throws Exception {
        if(supplier != null){//回来修改
            supplier = supplierService.saveOrUpdate(supplier);
            getRequest().put("Supplier", supplier);
            //重定向至list页面
            WebUtils.redrictToUrl(getContextPath()+"/admin/supplier/list");
        }else {
            //去显示简介页面
            String id = getReqParameter("id");
            if(id != null){
                Supplier supplier = supplierService.getById(Integer.parseInt(id));
                getRequest().put("Supplier", supplier);
            }
        }
        //去添加页面
        return SUCCESS;
    }


    @Action(value = "delete", results = {
            @Result(name = "notfound", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String delete() throws Exception {
        String id = getReqParameter("id");
        supplierService.delete(Integer.valueOf(id));
        //重定向至list页面
        WebUtils.redrictToUrl(getContextPath()+"/admin/supplier/list");
        return SUCCESS;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
