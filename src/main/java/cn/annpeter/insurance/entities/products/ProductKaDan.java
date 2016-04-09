package cn.annpeter.insurance.entities.products;

import cn.annpeter.insurance.entities.ProductCate;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by annpeter on 3/14/16.
 */
@Entity(name = "jt_p_kadan")
public class ProductKaDan{
    @Id
    @GeneratedValue
    Integer id;

    Integer product_id;

    @Column(length = 45)
    String description;

    Integer price;

    String img_small;

    String img_big;

    @Column(length = 45)
    String conditions;

    @Type(type = "text")
    String html_info;

    public Integer getId() {
        return id;
    }

    @Transient
    Product product;


    public ProductKaDan(){
    }

    public ProductKaDan(ProductKaDan k, Product p){
        this.product = p;

        this.id = k.id;
        this.product_id = k.product_id;
        this.description = k.description;
        this.price = k.price;
        this.img_small = k.img_small;
        this.img_big = k.img_big;
        this.conditions = k.conditions;
        this.html_info = k.html_info;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg_small() {
        return img_small;
    }

    public void setImg_small(String img_small) {
        this.img_small = img_small;
    }

    public String getImg_big() {
        return img_big;
    }

    public void setImg_big(String img_big) {
        this.img_big = img_big;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getHtml_info() {
        return html_info;
    }

    public void setHtml_info(String html_info) {
        this.html_info = html_info;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
