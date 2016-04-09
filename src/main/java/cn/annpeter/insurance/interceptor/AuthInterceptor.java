package cn.annpeter.insurance.interceptor;

import cn.annpeter.insurance.entities.Administrator;
import cn.annpeter.insurance.services.AuthGroupService;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.WebUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 用于检测用户授权
 *
 * Created by annpeter on 3/11/16.
 */
public class AuthInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = -8209905321100447926L;

    @Resource
    AuthGroupService authGroupService;

	@Override
	public String intercept(ActionInvocation actionInvocation) {

		try {

            if(Constant.DEBUG){
                actionInvocation.invoke();
                return "success";
            }

			ActionProxy actionProxy = actionInvocation.getProxy();
			String method = actionProxy.getMethod();
            String namespace = actionProxy.getNamespace();
            String action = namespace.substring(namespace.lastIndexOf("/")+1, namespace.length());

			//检测本次行为是否为登陆，如果不是登陆，检测用户权限
			if(!method.equals("login")){
				ActionContext actionContext = actionInvocation.getInvocationContext();
                HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
                Administrator adminLogined = (Administrator) actionContext.getSession().get("Administrator");
				
				if(adminLogined != null){
                    //用户已登陆, 进一步检验路径权限

                    //如果路径是index,index直接授权
                    //如果路径是authgroup,checkauth, ajax请求检查权限,提高用户体验
                    //上传不拦截
                    //admin查看自己的信息
                    //admin登出
                    if((action.equals("authgroup") && method.equals("checkauth"))||
                            (action.equals("index")  && method.equals("index"))||
                            (action.equals("upload")  && method.equals("index"))||
                            (action.equals("administrator")  && method.equals("admininfo"))||
                            (action.equals("administrator")  && method.equals("logout"))){
                        actionInvocation.invoke();
                        return "success";
                    }else{
                        //这里正式检查权限,防止黑客等入侵
                        boolean isVaild = authGroupService.checkActionMethodVaild(adminLogined, action, method);
                        if(isVaild){
                            actionInvocation.invoke();
                            return "success";
                        }else {

                            WebUtils.redrictToUrl(request.getContextPath()+"/admin/index/index?auth=false");
                            return null;
                        }
                    }
				}else{
//					if(!actionProxy.getActionName().equals("index")){
//						//本次登陆Action不在index，所以重定向至index然后到登陆
//						WebUtils.redrictToUrl("admin/index/login");
//					}
					//本package中的Action,应该可以直接跳转,无需重定向,耗费资源
                    WebUtils.redrictToUrl(request.getContextPath()+"/admin/index/login");
				}
			}else{
				//本次行为即为登陆，无需检测授权
				actionInvocation.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
