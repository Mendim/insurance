package cn.annpeter.insurance.actions;

/**
 * Created by annpeter on 3/17/16.
 */

import cn.annpeter.insurance.actions.BaseRequestAction;
import cn.annpeter.insurance.utils.Constant;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by annpeter on 3/15/16.
 */
@Controller
public class BaseJsonResAction extends BaseRequestAction {

    private InputStream inputStream;
    private Gson gson;
    private Map<String, Object> jsonMap;

    public BaseJsonResAction(){
        gson = new Gson();
        jsonMap = new HashMap<String, Object>();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    /**
     * 组装并发送要传送的内容
     * @param message
     * @param result
     * @throws UnsupportedEncodingException
     */
    public void sendSuccessMessage(String message, Object result) throws Exception {

        try{
            jsonMap.put("result", result);
            jsonMap.put("message", message);
            jsonMap.put("status", 1);

            if(Constant.DEBUG){
                printActionAfterDebugInfo(gson.toJson(jsonMap).toString());
            }
            inputStream = new ByteArrayInputStream(gson.toJson(jsonMap).getBytes("UTF-8"));
            inputStream.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void sendFailMessage(String message, Object result) throws Exception {
        jsonMap.put("result", result);
        sendFailMessage(message);
    }

    public void sendFailMessage(String message) throws Exception {
        jsonMap.put("message", message);
        jsonMap.put("status", 0);

        if(Constant.DEBUG){
            printActionAfterDebugInfo(jsonMap.toString());
        }
        inputStream = new ByteArrayInputStream(gson.toJson(jsonMap).getBytes("UTF-8"));
        inputStream.close();
    }
}