package cn.annpeter.insurance.entities.products;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by annpeter on 3/30/16.
 */
@Entity(name = "jt_p_tuanyi")
public class ProductTuanYi {
    @Id
    @GeneratedValue
    Integer id;

    Integer product_id;

    Short is_pri;   //是否为主险

    @Column(length = 45)
    String description;

    @Column(length = 45)
    String detail;

    @Column(length = 45)
    String form_detail;

    @Column(length = 45)
    String info;

    @Column(length = 45)
    String name;

    @Transient
    Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Short getIs_pri() {
        return is_pri;
    }

    public void setIs_pri(Short is_pri) {
        this.is_pri = is_pri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getForm_detail() {
        return form_detail;
    }

    public void setForm_detail(String form_detail) {
        this.form_detail = form_detail;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
