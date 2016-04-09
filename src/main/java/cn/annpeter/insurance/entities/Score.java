package cn.annpeter.insurance.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by annpeter on 3/28/16.
 */
@Entity(name = "jt_score")
public class Score {
    @Id
    @GeneratedValue
    Integer id;

    Integer product_id;

    @Temporal(TemporalType.DATE)
    Date startdate;

    @Type(type = "text")
    String price_formula;   //价格公式 ，与职业分类等相关

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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getPrice_formula() {
        return price_formula;
    }

    public void setPrice_formula(String price_formula) {
        this.price_formula = price_formula;
    }
}
