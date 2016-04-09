package cn.annpeter.insurance.entities.JsonBean.busniess;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annpeter on 3/27/16.
 */
@Component
public class JsonReqBusinessCommit {
    Integer memberId;

    List<JsonBussinessCommit> jsonBussinessCommitList;

    public JsonReqBusinessCommit(){
        if(jsonBussinessCommitList == null){
            jsonBussinessCommitList = new ArrayList<>();
        }
    }


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public List<JsonBussinessCommit> getJsonBussinessCommitList() {
        return jsonBussinessCommitList;
    }

    public void setJsonBussinessCommitList(List<JsonBussinessCommit> jsonBussinessCommitList) {
        this.jsonBussinessCommitList = jsonBussinessCommitList;
    }
}
