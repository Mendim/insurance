package cn.annpeter.insurance.utils;

import cn.annpeter.insurance.entities.configs.Navigater;
import cn.annpeter.insurance.entities.configs.NavigaterTree;
import cn.annpeter.insurance.entities.configs.Navigaters;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

/**
 * Created by annpeter on 3/25/16.
 */

@Component
public class NavigaterUtils {

    String formatNoSub;
    String formatHaveSub;

    @Resource
    private NavigaterTree navigaterTree;

    HttpSession session;
    String action;
    String method;

    private String actionDesc = "保险专家后台管理";
    private String methodDesc = "";


    private NavigaterUtils(){
        //无子节点的html模板
        formatNoSub =   "<li class='%s'>"+       //是否高亮active
                            "<a href='%s'>"+       //url路径
                                "<i class='%s'></i> %s"+  //图标样式,名字
                            "</a>"+
                        "</li>";

        //有子节点的html模板
        formatHaveSub = "<li class='dropdown %s'>"+
                            "<a href='' class='dropdown-toggle' data-toggle='dropdown'>"+
                                "<i class='%s'></i>%s<b class='fa fa-plus dropdown-plus'></b>"+ //图标样式,名字
                            "</a>"+
                            "<ul class='dropdown-menu'>"+
                                "%s"+   //子标签
                            "</ul>"+
                        "</li>";
    }

    public void refresh(HttpServletRequest request, String action, String method){
        this.session = request.getSession();

        if(this.action == action && this.method== method && session.getAttribute("LeftNavHtml")!=null){
            return;
        }

        //可以在本类中做一个缓存, 加快速度...
        //每当有一个新的路径时,就做缓存
        this.action = action;
        this.method = method;


        String leftNavHtml = getNavigaterString(navigaterTree.getNavigatersRoot());
        session.setAttribute("LeftNavHtml", leftNavHtml);
        session.setAttribute("ActionDesc", actionDesc);
        session.setAttribute("MethodDesc", methodDesc);
    }


    //根据配置文件navigater.xml获取到导航栏的目录树
    private String getNavigaterString(Navigaters navigaters) {
        String retString = null;

        Iterator<Navigater> niIterator = navigaters.getNavigater().iterator();

        String thisTime = "";
        String active = "active";
        while (niIterator.hasNext()) {
            Navigater navigater = (Navigater) niIterator.next();

            //这里以后可能会有错，请注意navigater.xml文件中url的配置
            //匹配设置当前的Action和Method,使当前模块展开并高亮
            //当为首页的时候就不要高亮展示了
            if(action.equals(navigater.getAction()) && method.equals((navigater.getMethod())) && action != "index" && method != "index"){
                active = "active";
                methodDesc = navigater.getDesc();
                Navigater parent = navigater.getParent();
                actionDesc = parent.getDesc();
            }else{
                //清除上次的tag值
                active = "";
            }

            //形成路径,当有子模块时,高级模块后面没有/
            String actionStr = navigater.getAction().length()==0?"":"/"+navigater.getAction();
            String methodStr = navigater.getAction().length()==0?"":"/"+navigater.getMethod();
            ActionContext actionContext = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
            String url = String.format("%s/admin%s%s", request.getContextPath(), actionStr, methodStr);


            if(navigater.getNavigaters() != null){
                retString = getNavigaterString(navigater.getNavigaters());
                thisTime += String.format(formatHaveSub, active,  navigater.getImg(), navigater.getTitle(), retString);
            }else{
                thisTime += String.format(formatNoSub, active, url, navigater.getImg(), navigater.getTitle());
            }
        }

        return thisTime;
    }

}
