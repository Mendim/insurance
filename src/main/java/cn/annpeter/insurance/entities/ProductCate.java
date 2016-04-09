package cn.annpeter.insurance.entities;

import cn.annpeter.insurance.entities.products.Product;

import javax.persistence.*;
import java.util.List;

/**
 * Created by annpeter on 3/21/16.
 */
@Entity(name = "jt_productcate")
public class ProductCate {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    Integer id;

    /**
     * 标题
     */
    @Column(length = 45)
    String name;

    @Column(length = 45)
    String model;

    /**
     * 状态
     */
    Short _delete;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pId")
    private ProductCate parent;


    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="parent")
    List<ProductCate> children;


    public ProductCate(){

    }

    public ProductCate(Integer id, String name, String model) {
        this.id = id;
        this.name = name;
        this.model = model;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ProductCate getParent() {
        return parent;
    }

    public void setParent(ProductCate parent) {
        this.parent = parent;
    }

    public List<ProductCate> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCate> children) {
        this.children = children;
    }

    public Short get_delete() {
        return _delete;
    }

    public void set_delete(Short _delete) {
        this._delete = _delete;
    }
}
