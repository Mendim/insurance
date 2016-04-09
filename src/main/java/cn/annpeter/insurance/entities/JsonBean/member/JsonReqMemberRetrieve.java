package cn.annpeter.insurance.entities.JsonBean.member;

import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016/3/22.
 */
@Component
public class JsonReqMemberRetrieve {
    private String userpwd;
    private String code;
    private String mobile;

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
