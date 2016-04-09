package cn.annpeter.insurance.actions.admin;

import cn.annpeter.insurance.actions.BaseJsonResAction;
import cn.annpeter.insurance.entities.Member;
import cn.annpeter.insurance.services.MemberService;
import cn.annpeter.insurance.utils.Constant;
import cn.annpeter.insurance.utils.WebUtils;
import cn.org.rapid_framework.page.Page;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * 用户管理,用于用户的增删改查
 * Created by annpeter on 3/11/16.
 */
@Namespace("/admin/member")
@ParentPackage("adminDefault")
@Controller
public class AdminMemberAction extends BaseJsonResAction {


    @Resource
	private MemberService memberService;

	private Page page = null;
    private Member member; // 用于profile时提交用户信息


    /**
     * @return
     */
    @Action( value = "list", results = {
            @Result(name = "success", location = "/views/admin/member/list.jsp")
    })
	public String list() throws Exception {
		String objPage = ServletActionContext.getRequest().getParameter("page");
        ArrayList<Member> memberArrayList = null;

		if (objPage == null) {
			page = new Page<Member>(0, Constant.pageSize, memberService.getCount(null).intValue());
		} else {
			page = new Page<Member>(Integer.parseInt(objPage), Constant.pageSize, memberService.getCount(null).intValue());
		}

        memberArrayList = memberService.queryByPage(null, page);
        getRequest().put("MemberArrayList", memberArrayList);
        getRequest().put("Page", page);

		return SUCCESS;
	}

    // 显示用户详细信息
    @Action(value = "profile", results = {
            @Result(name = "success", location = "/views/admin/member/profile.jsp")
    })
	public String profile() throws Exception {
        //显示用户信息
        if(member == null){
            String uId = this.getReqParameter("id");
            member = memberService.getById(Integer.parseInt(uId));
        }else{//修改用户信息
            memberService.adminUpdate(member);
            //重定向至list页面
            WebUtils.redrictToUrl(getContextPath()+"/admin/member/list");
        }

        getRequest().put("Member", member);
        return SUCCESS;
	}

    @Action(value = "delete", results = {

    })
    public String delete() throws Exception {
        String memberIdStr = getReqParameter("id");
        if(memberIdStr == null || !Pattern.compile("^[0-9]*$").matcher(memberIdStr).find()){
            return ERROR;
        }

        memberService.delete(Integer.valueOf(memberIdStr));

        //重定向至list页面
        WebUtils.redrictToUrl(getContextPath()+"/admin/member/list");
        return null;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
