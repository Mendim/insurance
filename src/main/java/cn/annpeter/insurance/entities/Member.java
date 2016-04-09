package cn.annpeter.insurance.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by annpeter on 3/14/16.
 */
@Entity(name = "jt_member")
public class Member {

    @Id
    @GeneratedValue
    Integer id;

    Integer leader_id;

    @Column(length = 50)
    String username;    //用户昵称

    String headimg;

    @Column(length = 13)
    String mobile;

    @Column(length = 255)
    String userpwd;

    @Column(length = 20)
    String realname;

    @Column(length = 50)
    String city;

    Integer star;

    @Column(length = 20)
    String sn;

    @Column(length = 10)
    String qq;

    @Column(length = 20)
    String tel_num;

    @Column(length = 128)
    String email;

    @Column(length = 6)
    String city_id;

    @Column(length = 20)
    String fax;

    Integer logintimes;

    @Column(length = 39)
    String reg_ip;

    @Temporal(TemporalType.TIMESTAMP)
    Date reg_time;

    @Column(length = 255)
    String last_login_ip;

    @Temporal(TemporalType.TIMESTAMP)
    Date last_login_time;

    Short _delete;

    public Member(){

    }

    public Member(String mobile, String realname, String userpwd, String city) {
        this.mobile = mobile;
        this.realname = realname;
        this.userpwd = userpwd;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel_num() {
        return tel_num;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(Integer logintimes) {
        this.logintimes = logintimes;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public Date getReg_time() {
        return reg_time;
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
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

    public Short get_delete() {
        return _delete;
    }

    public void set_delete(Short _delete) {
        this._delete = _delete;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
