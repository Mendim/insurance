package cn.annpeter.insurance.actions.app;

import cn.annpeter.insurance.actions.BaseJsonReqAction;
import cn.annpeter.insurance.entities.ReceiveAddr;
import cn.annpeter.insurance.services.ReceiveAddrService;
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

/**
 * Created by annpeter on 3/21/16.
 */
@Namespace("/app/receaddr")
@ParentPackage("appDefault")
@Controller
public class ReceiveAddrAction extends BaseJsonReqAction {
    @Resource
    ReceiveAddrService receiveAddrService;


    @Action(value = "list", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String list() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在请求收货地址列表");
        }

        String memberIdStr = getReqParameter("id");
        if(memberIdStr == null){
            sendFailMessage("请求参数错误");
        }else {
            List<ReceiveAddr> result = receiveAddrService.list(Integer.parseInt(memberIdStr));

            sendSuccessMessage("请求收货地址成功", result);
        }

        return SUCCESS;
    }



    @Action(value = "add", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String add() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在添加收货地址");
        }

        Gson gson = new Gson();
        ReceiveAddr receiveAddr =  gson.fromJson(getReqJsonStr(), ReceiveAddr.class);
        if(receiveAddr == null){
            sendFailMessage("数据参数错误");
        }else {
            receiveAddrService.add(receiveAddr);
            sendSuccessMessage("添加收货地址成功", new ArrayList<>());
        }

        return SUCCESS;
    }


    @Action(value = "delete", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String delete() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在删除收货地址");
        }

        String receiveAddrStr = getReqParameter("id");
        if(receiveAddrStr == null){
            sendFailMessage("请求参数错误");
        }else {
            receiveAddrService.delete(Integer.parseInt(receiveAddrStr));
            sendSuccessMessage("删除收货地址成功", new ArrayList<>());
        }

        return SUCCESS;
    }


    @Action(value = "profile", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String profile() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在修改收货地址");
        }

        Gson gson = new Gson();
        ReceiveAddr receiveAddr =  gson.fromJson(getReqJsonStr(), ReceiveAddr.class);

        if(receiveAddr == null){
            sendFailMessage("请求参数错误");
        }else {
            receiveAddrService.update(receiveAddr);
            sendSuccessMessage("修改收货地址成功", new ArrayList<>());
        }

        return SUCCESS;
    }
}
