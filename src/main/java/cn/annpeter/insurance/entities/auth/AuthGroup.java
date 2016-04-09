package cn.annpeter.insurance.entities.auth;

import cn.annpeter.insurance.entities.Administrator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by annpeter on 3/23/16.
 */


@Entity(name = "jt_auth_group")
public class AuthGroup {
    @Id
    @GeneratedValue
    Integer id;

    String name;//组名

    @Column(length = 80)
    String description;//组的描述信息

    Short _delete;

    @Column(length = 255)
    String authRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short get_delete() {
        return _delete;
    }

    public void set_delete(Short _delete) {
        this._delete = _delete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthRule() {
        return authRule;
    }

    public void setAuthRule(String authRule) {
        this.authRule = authRule;
    }

}
