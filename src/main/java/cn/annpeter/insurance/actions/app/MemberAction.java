package cn.annpeter.insurance.actions.app;

import cn.annpeter.insurance.actions.BaseJsonReqAction;
import cn.annpeter.insurance.entities.JsonBean.member.JsonReqMemberRegister;
import cn.annpeter.insurance.entities.JsonBean.member.JsonReqMemberRetrieve;
import cn.annpeter.insurance.entities.Member;
import cn.annpeter.insurance.entities.Sms;
import cn.annpeter.insurance.services.MemberService;
import cn.annpeter.insurance.services.SmsService;
import cn.annpeter.insurance.utils.CommonUtils;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.TaoBaoMsgCodeAPI;
import com.google.gson.Gson;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by yt on 2016/3/18.
 */
@Namespace("/app/member")
@ParentPackage("appDefault")
@Controller
public class MemberAction extends BaseJsonReqAction{

    @Resource
    private MemberService memberService;
    @Resource
    SmsService smsService;

    @Action(value = "login", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String login() throws Exception {

        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在登陆");
        }

        String account = getReqParameter("account");
        String userpwd = getReqParameter("userpwd");
        if(account==null || userpwd == null || account.length() == 0 || userpwd.length() == 0){
            sendFailMessage("账号或密码为空,不符合要求");
            return SUCCESS;
        }

        //检验用户名和密码
        Member member = memberService.signIn(account, userpwd);

        if(member==null){
            sendFailMessage("账号或者密码错误");
            return SUCCESS;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", member.getId());
            jsonObject.put("mobile", member.getMobile());
            jsonObject.put("username", member.getUsername());
            jsonObject.put("city", member.getUsername());

            List list = new ArrayList<>();
            list.add(jsonObject);

            sendSuccessMessage("登陆成功", list);
        }
        return SUCCESS;
    }


    /*
     * 验证手机,发送验证码,此时不做此手机号是否注册的检查
     */
    @Action(value = "checkphone", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String checkphone() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在申请验证码");
        }

        String mobile = getReqParameter("mobile");
        if(mobile == null || mobile.length() != 11){
            sendFailMessage("请输入正确的手机号");
            return SUCCESS;
        }

        //生成code,6位随机数组成
        String code= CommonUtils.getRandomNum(6);
        Sms sms = new Sms();
        sms.setDate(new Date(System.currentTimeMillis()));
        sms.setCode(code);
        sms.setMobile(mobile);
        smsService.save(sms);

        //向淘宝发起验证码请求
        AlibabaAliqinFcSmsNumSendResponse rsp = TaoBaoMsgCodeAPI.sendCode(mobile, code);

        if(rsp==null){
            sendFailMessage("短息发送失败,请稍后重试");
            return SUCCESS;
        }else if(rsp.getErrorCode()!=null && rsp.getErrorCode().equals("15")){
            sendFailMessage("发送短信次数超过限制,请稍后再试");
            return SUCCESS;
        }

        sendSuccessMessage("验证码发送成功", new ArrayList<>());
        return SUCCESS;
    }


    /**
     * 用户注册
     * @return
     * @throws Exception
     */
    @Action(value = "register", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String register()throws Exception {

        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户正在注册");
            if(getReqJsonStr() == null){
                sendFailMessage("数据格式错误");
                return SUCCESS;
            }
        }

        Gson gson=new Gson();
        JsonReqMemberRegister json = gson.fromJson(getReqJsonStr(),
                JsonReqMemberRegister.class);
        if(json == null){
            sendFailMessage("数据格式错误");
            return SUCCESS;
        }

        //检查moble带上来的code和数据库中的code是否一致,时间是否符合要求
        //Timestamp time = new Timestamp()
        Sms sms =  smsService.checkCode(json.getMobile(), json.getCode());

        if(sms == null){
            sendFailMessage("验证码错误");
            return SUCCESS;
        }else if(new Date().getTime() > sms.getDate().getTime() + Constant.smsDuration*1000){
            sendFailMessage("验证码已过期");
            return SUCCESS;
        }else if(memberService.checkMobileExists(json.getMobile())){
            sendFailMessage("该号码已经注册");
            return SUCCESS;
        }else{
            //发送用户信息
            Member member = new Member(json.getMobile(), json.getRealname(), json.getUserpwd(), json.getCity());
            memberService.save(member);

            sendSuccessMessage("注册成功", new ArrayList<>());
            return SUCCESS;
        }
    }

    /*
    找回密码
     */
    @Action(value = "retrieve", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType","text/html",
                    "inputName","inputStream"
            })
    })
    public String retrieve() throws Exception {
        if(Constant.DEBUG){
            printActionBeforeDebugInfo("用户请求找回密码");
            if(getReqJsonStr() == null){
                sendFailMessage("数据格式错误");
                return SUCCESS;
            }
        }

        Gson gson=new Gson();
        JsonReqMemberRetrieve json = gson.fromJson(getReqJsonStr(), JsonReqMemberRetrieve.class);

        if(json == null){
            sendFailMessage("数据格式错误");
            return SUCCESS;
        }

        //检查moble带上来的code和数据库中的code是否一致,时间是否符合要求
        Sms sms =  smsService.checkCode(json.getMobile(), json.getCode());

        if(sms == null){
            sendFailMessage("验证码错误");
            return SUCCESS;
        }else if(new Date().getTime() > sms.getDate().getTime() + Constant.smsDuration*1000){
            sendFailMessage("验证码已过期"+(new Date().getTime()-sms.getDate().getTime() + Constant.smsDuration*1000));
            return SUCCESS;
        }else{
            memberService.updatePasswd(json.getMobile(), json.getUserpwd());
            sendSuccessMessage("重设密码成功",new ArrayList<>());
            getSession().put("code",null);
            return SUCCESS;
        }
    }



    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
