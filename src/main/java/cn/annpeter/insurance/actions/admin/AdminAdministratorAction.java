package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.services.AdministratorService;
import cn.annpeter.insurance.services.AuthGroupService;
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
 * Created by annpeter on 3/23/16.
 */
@Namespace("/admin/administrator")
@ParentPackage("adminDefault")
@Controller
public class AdminAdministratorAction extends BaseJsonResAction {

    @Resource
    AdministratorService administratorService;

    @Resource
    AuthGroupService authGroupService;

    private Page page = null;
    private Administrator administrator = null;


    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/administrator/list.jsp")
    })
    public String list() throws Exception {
        String pageIndexStr = ServletActionContext.getRequest().getParameter("page");

        int pageIndex = (pageIndexStr!=null)?Integer.parseInt(pageIndexStr):0;
        page = new Page<Administrator>(pageIndex, Constant.pageSize, (int) administratorService.getCount(null).intValue());

        List<Administrator> adminArrayList = administratorService.getByPage(page);

        List<AuthGroup> authGroupArrayList = authGroupService.list();
        getRequest().put("AuthGroupArrayList", authGroupArrayList);

        getRequest().put("AdministratorArrayList", adminArrayList);
        getRequest().put("BoolShowGroupName", true);
        getRequest().put("Page", page);

        return SUCCESS;
    }

    @Action(value = "add", results = {
            @Result(name = "success", location = "/views/admin/administrator/addOrProfile.jsp")
    })
    public String add() throws Exception {
        if(administrator != null){
            administratorService.saveOrUpdate(administrator);

            WebUtils.redrictToUrl(getContextPath()+"/admin/administrator/list");
        }
        List<AuthGroup> authGroupArrayList = authGroupService.list();
        getRequest().put("AuthGroupArrayList", authGroupArrayList);
        return SUCCESS;
    }



    /**
     * 管理员展示修改自己的信息
     * @return
     * @throws Exception
     */
    @Action(value = "profile", results = {
            @Result(name = "success", location = "/views/admin/administrator/addOrProfile.jsp")
    })
    public String profile() throws Exception {
        List<AuthGroup> authGroupArrayList = authGroupService.list();
        getRequest().put("AuthGroupArrayList", authGroupArrayList);

        Administrator adminLogined = (Administrator) getSession().get("Administrator");
        getRequest().put("Administrator", adminLogined);
        return SUCCESS;
    }

    @Action(value = "logout", results = {
            @Result(name = "success", location = "/views/admin/index/login.jsp")
    })
    public String logout(){
        getSession().remove("Administrator");
        WebUtils.redrictToUrl(getContextPath()+"/admin/index/login");
        return SUCCESS;
    }


    @Action(value = "delete", results = {
            @Result(name = "success", location = "/views/admin/administrator/list.jsp")
    })
    public String delete() throws Exception {
        String adminId = this.getReqParameter("id");

        if(adminId != null){
            administratorService.deleteById(Integer.parseInt(adminId));
            WebUtils.redrictToUrl(getContextPath()+"/admin/administrator/list");
        }
        return SUCCESS;
    }


    @Action(value = "checkphone", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String checkphone() throws Exception {
        String phone = this.getReqParameter("phone");
        boolean isReg = administratorService.checkPhone(phone);
        if(isReg){
            sendFailMessage("该手机号已被注册");
        }else{
            sendSuccessMessage("该手机号尚未被注册", null);
        }
        return SUCCESS;
    }

    @Action(value = "checknickname", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String checknickname() throws Exception {
        String nickname = this.getReqParameter("nickname");
        boolean isReg = administratorService.checkNickname(nickname);
        if(isReg){
            sendFailMessage("该手机号已被注册");
        }else{
            sendSuccessMessage("该手机号尚未被注册", null);
        }
        return SUCCESS;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
