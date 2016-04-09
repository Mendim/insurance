##订单模块接口

###发起订单请求(/app/business/commit)

    ```
	var jsObj = {
                "memberId":"1",
                "jsonBussinessCommitList":[
						{"productId":1, "customerId":1, "receiveAddrId":1},
						{"productId":1, "customerId":1, "receiveAddrId":1},
						{"productId":1, "customerId":1, "receiveAddrId":1}
					]
            	};

    var str = JSON.stringify(jsObj);

	{"reqJsonStr":str}

	```