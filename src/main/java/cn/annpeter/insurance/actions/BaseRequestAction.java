package cn.annpeter.insurance.actions;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by annpeter on 3/15/16.
 *
 * 所有的非文件上传下载请求都继承自该类
 */
@Controller
public class BaseRequestAction extends ActionSupport implements RequestAware, SessionAware{

    public Logger logger = Logger.getLogger(this.getClass());

    private Map<String, Object> request;
    private Map<String, Object> session;
    private String contextPath;

    /**
     * 定义基本的返回类型
     */
    public static String NOTFOUNG = "notfound";
    public static String JSON = "json";

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


    public Map<String, Object> getRequest(){
        return this.request;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * 获取url问号后面的参数
     * @param key
     * @return
     */
    public String getReqParameter(String key){
        return ServletActionContext.getRequest().getParameter(key);
    }


    public String getContextPath() {
        if(contextPath == null){
            ActionContext actionContext = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
            contextPath= request.getContextPath();
        }
        return contextPath;
    }

    public void printActionBeforeDebugInfo(String msg){

        ActionContext context= ActionContext.getContext();
        Map parameterMap = context.getParameters();

        String jsonStr = null;
        if(parameterMap.size() != 0){
            Gson gson = new Gson();
            jsonStr = gson.toJson(parameterMap);
        }

        System.out.println();
        System.out.println("访问参数:"+jsonStr);
        System.out.println(msg);
    }

    public void printActionAfterDebugInfo(String msg){
        System.out.println();
        System.out.println(msg);
    }
}
