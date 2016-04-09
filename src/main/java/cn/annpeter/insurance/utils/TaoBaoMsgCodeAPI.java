package cn.annpeter.insurance.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.stereotype.Component;


/**
 * Created by annpeter on 3/21/16.
 */
@Component
public class TaoBaoMsgCodeAPI {

    public static AlibabaAliqinFcSmsNumSendResponse sendCode(String mobile, String code) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(  "http://gw.api.taobao.com/router/rest",
                                                        "23296819", //appkey
                                                        "5fdf33a06e5bb01175b77c3486c97516"  //App Secret
                                                        );
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsParam("{'code':'" +code + "','product':'保险专家'}");

        //setSmsParam传入一个json,code代表验证码，product代表公司名
        req.setRecNum(mobile);                 //要发送的电话号码
        req.setSmsTemplateCode("SMS_4400327");       //短信模板

        return client.execute(req);
    }
}
