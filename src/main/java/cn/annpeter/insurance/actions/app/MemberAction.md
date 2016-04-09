#Member交流模块
	

###请求验证码(/app/member/checkphone)
*   使用post方式上传手机号mobile

*   返回json样例

	```
        {
          "result": [],
          "message": "短信发送成功",
          "status": 1
        }
	```

###用户登录(/app/member/login)
*   使用post方式上传用户account(目前account只支持mobile)
    用户密码userpwd

*   返回json样例

	```
        {
          "result": [
            {
              "id": "1",
              "city": "无锡",
              "mobile": "123456789",
              "username": "Test"
            }
          ],
          "message": "登陆成功",
          "status": 1
        }
	```

###用户注册(/app/member/register)
* 请求json样例
	
	```
        var jsObj={
            "realname": "test1",
            "userpwd": "test1",
            "mobile": "100",
            "city": "无锡",
            "code": "2121"
        }
        var str=JSON.stringify(jsObj);
        {"reqJsonStr":str}
	```
	
* 返回json样例

	```
        {
          "result": [],
          "message": "注册成功,
          "status": 1
        }
	```

###找回密码(/app/member/retrieve)
* 请求json样例

	```
        var jsObj={
                "userpwd": "12345"
                "code": "12345",
                "mobile": "123456"
            }
        var str=JSON.stringify(jsObj);
        {"reqJsonStr":str}
	```
	
* 返回json样例

	```
        {
          "result": [],
          "message": "重设密码成功",
          "status": 1
        }
	```