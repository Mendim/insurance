##收货地址接口交流

###添加收货地址(/app/receaddr/add)
* 申请json样例

    ```
    var jsObj = {
                    "member_id":"1",
                    "isdefault":"1",
                    "address": "中国",
                    "contactor":"annpeter",
                    "tel":"1231231"
                	};

        var str = JSON.stringify(jsObj);

    	{"reqJsonStr":str}
    ```


###查看所有地址(/app/receaddr/list)
* 返回json样例

    ```
        {
        	  "result":[
                    {
                        "id":"1",
                           "member_id":"1",
                           "isdefault":"1",
                           "address": "中国",
                           "contactor":"annpeter",
                           "tel":"1231231",
                       },
                    {
                        "id":"2",
                           "member_id":"1",
                           "isdefault":"1",
                           "address": "中国",
                           "contactor":"annpeter",
                           "tel":"1231231",
                       }
                  ],
        	  "message": "获取购物车成功",
        	  "status": 1
        	}

        ```

###修改地址(/app/receaddr/profile)