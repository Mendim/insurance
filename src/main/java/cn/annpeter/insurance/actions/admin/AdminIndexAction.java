package cn.annpeter.insurance.actions.admin;


import cn.annpeter.insurance.actions.BaseRequestAction;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.services.AdministratorService;
import cn.annpeter.insurance.services.AuthGroupService;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.WebUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于后台首页的展示操作和管理员登陆
 * Created by annpeter on 3/10/16.
 */
@Namespace("/admin/index")
@ParentPackage("adminDefault")
@Controller
public class AdminIndexAction extends BaseRequestAction {

    @Resource
    private AdministratorService administratorService;
    private Administrator administrator;

    @Resource
    AuthGroupService authGroupService;


    @Action(value = "index", results = {
            @Result(name = "success", location = "/views/admin/index/index.jsp")
    })
    public String index() {
        return SUCCESS;
    }


    @Action(value = "login", results = {
            @Result(name = "success", location = "/views/admin/index/login.jsp"),
    })
    public String login() throws Exception {

        String account = getReqParameter("account");
        String passwd = getReqParameter("passwd");
        if(account != null && passwd != null){
            Administrator admin = administratorService.login(account, passwd);

            if(admin != null){
                ActionContext actionContext = ActionContext.getContext();
                Map<String, Object> session = actionContext.getSession();
                session.put("Administrator", admin);

                //更新用户登录信息
                admin.setLast_login_time(new Date());
                admin.setLast_login_ip(CommonUtils.getIpAddr());
                //这里的admin直接是hibernate的原始对象,可直接修改
                administratorService.updateLoginTimeAndIp(admin);

                //登陆成功，重定向至首页
                WebUtils.redrictToUrl(getContextPath()+"/admin/index/index");
                return SUCCESS;
            }else{
                //用户不合法，登陆失败
                getRequest().put("userInvalid", 0);
            }
        }
        return SUCCESS;
    }


    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
