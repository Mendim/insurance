package cn.annpeter.insurance.actions.app;

import cn.annpeter.insurance.actions.BaseJsonReqAction;
import cn.annpeter.insurance.entities.Business;
import cn.annpeter.insurance.entities.JsonBean.busniess.JsonBussinessCommit;
import cn.annpeter.insurance.entities.JsonBean.busniess.JsonReqBusinessCommit;
import cn.annpeter.insurance.services.BusinessService;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.Exceptions.DataErrorException;
import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by annpeter on 3/27/16.
 */
@Namespace("/app/business")
@ParentPackage("appDefault")
@Controller
public class BusinessAction extends BaseJsonReqAction{

    @Resource
    BusinessService businessService;

    @Action(value = "commit", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String commit() throws Exception {

        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在请求提交订单");
        }

        String reqJsonStr = getReqJsonStr();
        Gson gson = new Gson();
        JsonReqBusinessCommit businessReq = gson.fromJson(reqJsonStr, JsonReqBusinessCommit.class);


        boolean dataValid = false;
        //生成一个合法的随机订单编号
        String orderId = getValidOrderId();

        Iterator<JsonBussinessCommit> it = businessReq.getJsonBussinessCommitList().iterator();
        Integer memberId = businessReq.getMemberId();
        if( memberId == null){
            dataValid = true;
        }

        List businessList = new ArrayList<>();
        while (it.hasNext() && !dataValid) {
            JsonBussinessCommit item = it.next();

            Integer customerId = item.getCustomerId();
            Integer productId = item.getProductId();
            Integer receiveAddrId = item.getReceiveAddrId();

            if(item.getCustomerId() == null ||
                    item.getProductId() == null ||
                    item.getReceiveAddrId() == null){
                dataValid = true;
                break;
            }

            Business business = new Business(orderId, memberId, productId, customerId,
                    (float)1, (float)1, 1, receiveAddrId, Business.statusEnum.WaitToPay.ordinal());
            businessList.add(business);
        }


        if(dataValid){
            sendFailMessage("数据错误");
        }else{
            businessService.save(businessList);
            sendSuccessMessage("订单提交成功", new ArrayList<>());
        }

        return SUCCESS;
    }



    /**
     * 生成一个合法的id,保证数据库内不会冲突
     * @return
     * @throws Exception
     */
    private String getValidOrderId() throws Exception {
        String orderId = null;
        String timeStr = CommonUtils.getDateStr(new Date(), "yyyyMMDDHHmmssSSS");
        do {
            String randomStr = CommonUtils.getRandomCharAndNumr(10);
            orderId = timeStr + randomStr;
        }while (!businessService.isValidOrderId(orderId));

        return orderId;
    }

}
