package cn.annpeter.insurance.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by annpeter on 3/18/16.
 */
@Entity(name = "jt_supplier")
public class Supplier {

    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 45)
    String title;

    @Column(length = 45)
    String etitle;

    String logo;

    @Column(length = 128)
    String activeurl;

    @Column(length = 15)
    String tel;


    Short _delete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEtitle() {
        return etitle;
    }

    public void setEtitle(String etitle) {
        this.etitle = etitle;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Short get_delete() {
        return _delete;
    }

    public void set_delete(Short _delete) {
        this._delete = _delete;
    }

    public String getActiveurl() {
        return activeurl;
    }

    public void setActiveurl(String activeurl) {
        this.activeurl = activeurl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
