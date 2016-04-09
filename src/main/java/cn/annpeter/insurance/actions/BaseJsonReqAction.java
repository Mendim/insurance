package cn.annpeter.insurance.actions;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * Created by annpeter on 3/20/16.
 */
public class BaseJsonReqAction extends  BaseJsonResAction{
    String reqJsonStr;

    public String getReqJsonStr() {
        return reqJsonStr;
    }

    public void setReqJsonStr(String reqJsonStr) {
        this.reqJsonStr = reqJsonStr;
    }

    @Override
    public String toString() {
        return "BaseJsonReqAction{" +
                "reqJsonStr='" + reqJsonStr + '\'' +
                '}';
    }
}
