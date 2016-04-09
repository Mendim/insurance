package cn.annpeter.insurance.entities.JsonBean.productkadan;

import org.springframework.stereotype.Component;

/**
 * Created by annpeter on 4/7/16.
 */
@Component
public class JsonResProductKaDanProfile {

    String productId;
    String productTitle;
    String productSummary;
    String productCate;
    String productConditions;
    String productSupplier;

    String kadanPrice;
    String kadanDescription;
    String kadanImgBig;
    String kadanHtmlInfo;

    public JsonResProductKaDanProfile(String productId, String productTitle, String productSummary, String productCate,
                                      String productConditions, String productSupplier, String kadanPrice,
                                      String kadanDescription, String kadanImgBig, String kadanHtmlInfo) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productSummary = productSummary;
        this.productCate = productCate;
        this.productConditions = productConditions;
        this.productSupplier = productSupplier;
        this.kadanPrice = kadanPrice;
        this.kadanDescription = kadanDescription;
        this.kadanImgBig = kadanImgBig;
        this.kadanHtmlInfo = kadanHtmlInfo;
    }
}
