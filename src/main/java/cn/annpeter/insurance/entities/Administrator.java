package cn.annpeter.insurance.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户登录可以通过昵称, 手机号, 邮箱进行登录
 *
 * Created by annpeter on 3/23/16.
 */
@Entity(name = "jt_administrator")
public class Administrator {
    @Id
    @GeneratedValue
    Integer id;

    Integer group_id;

    @Transient
    String groupName;   //不记录入库,查询时使用

    /**
     * 登陆昵称可修改
     */
    @Column(length = 20, unique = true, nullable = false)
    String nickname;

    /**
     * 真实姓名无法修改
     */
    @Column(length = 20, updatable = false)
    String truename;

    @Column(length = 64)
    String adminpwd;

    @Column(length = 13, unique = true)
    String mobile;

    @Column(length = 128, unique = true)
    String email;

    Integer login;

    @Column(length = 255)
    String last_login_ip;

    @Temporal(TemporalType.TIMESTAMP)
    Date last_login_time;

    @Temporal(TemporalType.TIMESTAMP)
    Date createtime;

    Short _delete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Short get_delete() {
        return _delete;
    }

    public void set_delete(Short _delete) {
        this._delete = _delete;
    }
}
