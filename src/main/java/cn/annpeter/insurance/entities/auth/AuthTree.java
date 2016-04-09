package cn.annpeter.insurance.entities.auth;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jt_auth_tree用于描述后台所有路径,这个是棵树.当后台添加新的模块时,手动在数据库里添加就行了.
 * Created by annpeter on 3/23/16.
 */
@Entity(name = "jt_auth_tree")
public class AuthTree {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    String action;

    String method;

    @Transient
    String clicked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pId")
    private AuthTree parent;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="parent")
    List<AuthTree> children;

    public AuthTree(){
        if(children == null){
            children = new ArrayList<>();
        }
        clicked = "false";
    }

    public AuthTree(Integer id){
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public AuthTree getParent() {
        return parent;
    }

    public void setParent(AuthTree parent) {
        this.parent = parent;
    }

    public List<AuthTree> getChildren() {
        return children;
    }

    public void setChildren(List<AuthTree> children) {
        this.children = children;
    }

    public String getClicked() {
        return clicked;
    }

    public void setClicked(String clicked) {
        this.clicked = clicked;
    }
}
