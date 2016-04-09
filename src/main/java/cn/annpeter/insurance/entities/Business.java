package cn.annpeter.insurance.entities;

import cn.annpeter.insurance.entities.products.Product;

import javax.persistence.*;

/**
 * Created by annpeter on 3/21/16.
 */
@Entity(name = "jt_business")
public class Business {

    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 30)
    String order_id;

    Integer member_id;

    Integer product_id;

    Integer customer_id;

    @Column(scale = 2)
    Float fee;

    @Column(scale = 2)
    Float rebate;

    Integer score;

    Integer scoreowner_id;

    Integer receiveaddr_id;

    Integer status; //订单状态. 1表示待付款;2表示待发货;3表示待签收;4表示签收完成,进入历史订单状态


    public enum  statusEnum{
        WaitToPay,
        WaitToSend,
        WaitToSign,
        Signed
    }

    Integer _delete;



    public Business(){}

    public Business(String order_id, Integer member_id, Integer product_id, Integer customer_id,
                    Float fee, Float rebate, Integer score, Integer receiveaddr_id, Integer status) {
        this.order_id = order_id;
        this.member_id = member_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.fee = fee;
        this.rebate = rebate;
        this.score = score;
        this.receiveaddr_id = receiveaddr_id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getRebate() {
        return rebate;
    }

    public void setRebate(Float rebate) {
        this.rebate = rebate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScoreowner_id() {
        return scoreowner_id;
    }

    public void setScoreowner_id(Integer scoreowner_id) {
        this.scoreowner_id = scoreowner_id;
    }

    public Integer getReceiveaddr_id() {
        return receiveaddr_id;
    }

    public void setReceiveaddr_id(Integer receiveaddr_id) {
        this.receiveaddr_id = receiveaddr_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer get_delete() {
        return _delete;
    }

    public void set_delete(Integer _delete) {
        this._delete = _delete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
