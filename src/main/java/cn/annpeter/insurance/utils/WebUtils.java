package cn.annpeter.insurance.utils;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by annpeter on 3/11/16.
 */
@Component
public class WebUtils {

    /**
     * 获取当前项目的路径
     * @return
     */
	public static String getBasePath() {
		ActionContext actionContext = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
		String path = request.getContextPath();
		String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path;

		return basePath;
	}

    /**
     * 用于重定向
     * @param uri
     */
	public static void redrictToUrl(String uri) {
		ActionContext actionContext = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)actionContext.get(StrutsStatics.HTTP_RESPONSE);
		try {
			response.sendRedirect(getBasePath()+uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
