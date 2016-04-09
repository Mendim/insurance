package cn.annpeter.insurance.entities.JsonBean.member;

import org.springframework.stereotype.Component;

/**
 * Created by yt on 2016/3/22.
 */
@Component
public class JsonReqMemberRegister {
    private String realname;
    private String userpwd;
    private String mobile;
    private String city;
    private String code;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
