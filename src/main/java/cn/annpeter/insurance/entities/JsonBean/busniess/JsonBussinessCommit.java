package cn.annpeter.insurance.entities.JsonBean.busniess;

import org.springframework.stereotype.Component;

/**
 * Created by annpeter on 3/27/16.
 */
@Component
public class JsonBussinessCommit {
    Integer productId;
    Integer customerId;
    Integer receiveAddrId;
    Integer num;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getReceiveAddrId() {
        return receiveAddrId;
    }

    public void setReceiveAddrId(Integer receiveAddrId) {
        this.receiveAddrId = receiveAddrId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
