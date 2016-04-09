package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonReqAction;
import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.entities.auth.AuthGroup;
import cn.annpeter.insurance.entities.auth.AuthTree;
import cn.annpeter.insurance.services.AdministratorService;
import cn.annpeter.insurance.services.AuthGroupService;
import cn.annpeter.insurance.services.AuthTreeService;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.WebUtils;
import cn.org.rapid_framework.page.Page;
import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by annpeter on 3/23/16.
 */
@Namespace("/admin/authgroup")
@ParentPackage("adminDefault")
@Controller
public class AdminAuthGroupAction extends BaseJsonReqAction {


    @Resource
    AuthGroupService authGroupService;

    @Resource
    AuthTreeService authTreeService;

    @Resource
    AdministratorService administratorService;

    private AuthGroup authGroup = null;
    private Page page = null;

    /**
     * ajax请求检测用户权限
     * @return
     */
    @Action(value = "checkauth", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String checkauth() throws Exception{
        String action = getReqParameter("action");
        String method = getReqParameter("method");

        if(Constant.DEBUG){
            sendSuccessMessage("Debug授权成功", new ArrayList<>());
            return SUCCESS;
        }

        Administrator administrator = (Administrator)getSession().get("Administrator");

        //admin可以查看自己的信息,可以退出
        if((action.equals("index")  && method.equals("index"))||
                (action.equals("upload")  && method.equals("index"))||
                (action.equals("administrator")  && method.equals("admininfo"))||
                (action.equals("administrator")  && method.equals("logout"))){
            //授权成功
            sendSuccessMessage(administrator.toString()+action+"/"+method+"/"+"授权成功", new ArrayList<>());
            return SUCCESS;
        }

        boolean vaild = authGroupService.checkActionMethodVaild(administrator, action, method);
        if(vaild){
            //授权成功
            sendSuccessMessage(administrator.toString()+action+"/"+method+"/"+"授权成功", new ArrayList<>());
        }else{
            //授权失败
            sendFailMessage(administrator.toString()+action+"/"+method+"/"+"授权失败", "0");
        }
        return SUCCESS;
    }


    /**
     * 组列表
     * @return
     */
    @Action(value = "list", results = {
            @Result(name = "success", location = "/views/admin/authgroup/list.jsp")
    })
    public String list() throws Exception {

        List<AuthGroup> authGroupArrayList = null;

        String objPage = ServletActionContext.getRequest().getParameter("page");

        if (objPage == null) {
            page = new Page<AuthGroup>(0, 13, (int) authGroupService.getCount(null));
        } else {
            page = new Page<AuthGroup>(Integer.parseInt(objPage), 13, (int) authGroupService.getCount(null));
        }

        authGroupArrayList = authGroupService.queryByPage(null, page);

        getRequest().put("AuthGroupArrayList", authGroupArrayList);
        getRequest().put("Page", page);

        return SUCCESS;
    }



    /**
     * 组内成员列表
     * @return
     */
    @Action(value = "ingrouplist", results = {
            @Result(name = "success", location = "/views/admin/administrator/list.jsp")
    })
    public String ingrouplist() throws Exception {
        //获取组id
        Integer groupId = Integer.parseInt(this.getReqParameter("id"));

        String objPage = ServletActionContext.getRequest().getParameter("page");

        if (objPage == null) {
            page = new Page<AuthGroup>(0, Constant.pageSize, administratorService.getCount("group_id = "+groupId).intValue());
        } else {
            page = new Page<AuthGroup>(Integer.parseInt(objPage), Constant.pageSize, (int) authGroupService.getCount(null));
        }

        List<Administrator> adminArrayList = administratorService.getByPage(page, groupId);

        getRequest().put("AdministratorArrayList", adminArrayList);
        getRequest().put("Page", page);

        return SUCCESS;
    }


    /**
     * 添加管理组
     * @return
     */
    @Action(value = "addorprofile", results = {
            @Result(name = "success", location = "/views/admin/authgroup/addOrProfile.jsp")
    })
    public String addorprofile() throws Exception {
        if(authGroup != null){
            //保存修改管理组信息
            authGroupService.saveOrUpdate(authGroup);
            WebUtils.redrictToUrl(getContextPath()+"/admin/authgroup/list");
            return SUCCESS;
        }else {

            String groupIdStr = this.getReqParameter("id");
            if(groupIdStr != null){//去修改管理组信息页面
                Integer groupId = Integer.parseInt(groupIdStr);
                authGroup = authGroupService.getById(groupId);

                String[] authRuleArr = authGroup.getAuthRule().split(",");

                Set<Object> treeSet = new HashSet<>();
                AuthTree authTree = authTreeService.getTree();

                //在权限树上将所有点击过的点记录下来
                deepTreeToCheckClicked(authTree.getChildren(), authRuleArr);
                //将权限树转化到treeSet中,便于转化为JSON
                deepTreeToGetAll(authTree.getChildren(), treeSet);

                Gson gson = new Gson();
                String jsonTree = gson.toJson(treeSet);

                getRequest().put("jsonTree", jsonTree);
                getRequest().put("AuthGroup", authGroup);
            }else{
                //去添加新的管理组页面
                List<?> authNodeTree = authTreeService.list();
                Gson gson = new Gson();
                String jsonTree = gson.toJson(authNodeTree);
                getRequest().put("jsonTree", jsonTree);
            }


            return SUCCESS;
        }
    }


    /**
     * 删除分组, 删除分组的同时,组内的所有成员都会被删除,删除成员操作在service中完成
     * @return
     */
    @Action(value = "delete", results = {
            @Result(name = "success", location = "/views/admin/authgroup/list.jsp")
    })
    public String delete() throws Exception {
        String groupIdStr = this.getReqParameter("id");

        if(groupIdStr != null){
            authGroupService.delete(Integer.parseInt(groupIdStr));
            //删除后重定向到list界面
            WebUtils.redrictToUrl(getContextPath()+"/admin/authgroup/list");
        }
        return SUCCESS;
    }


    /**
     * 在权限树上将所有点击过的点记录下来
     * @param authTree
     * @param authRuleArr
     */
    private void deepTreeToCheckClicked(List<AuthTree> authTree, String[] authRuleArr){
        if(authTree != null){
            Iterator<AuthTree> iterator = authTree.iterator();

            while (iterator.hasNext()){

                AuthTree treeNode = iterator.next();
                for(int i = 0; i < authRuleArr.length; i++){

                    if(treeNode.getId()  == Integer.parseInt(authRuleArr[i])){
                        treeNode.setClicked("true");

                        AuthTree tempNode = treeNode;
                        while (tempNode.getId() !=  1){
                            AuthTree parentNode = tempNode.getParent();
                            parentNode.setClicked("true");
                            tempNode = parentNode;
                        }
                    }
                }

                deepTreeToCheckClicked(treeNode.getChildren(), authRuleArr);
            }
        }
    }

    /**
     * 将权限树转化到treeSet中,便于转化为JSON
     * @param authTree
     * @param treeSet
     */
    private void deepTreeToGetAll(List<AuthTree> authTree, Set<Object> treeSet){
        if(authTree != null) {
            Iterator<AuthTree> iterator = authTree.iterator();

            while (iterator.hasNext()) {

                AuthTree treeNode = iterator.next();
                Map<String, Object> map = new HashMap<>();
                map.put("id", treeNode.getId());
                map.put("pId", treeNode.getParent().getId());
                map.put("name", treeNode.getName());
                map.put("checked", treeNode.getClicked());

                treeSet.add(map);
                deepTreeToGetAll(treeNode.getChildren(), treeSet);
            }
        }
    }



    public AuthGroup getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(AuthGroup authGroup) {
        this.authGroup = authGroup;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
