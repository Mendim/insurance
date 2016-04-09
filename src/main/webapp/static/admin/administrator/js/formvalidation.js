/**
 * Created by user on 2016/3/27.
 */

$(function (){
    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        return this.optional(element) || (length == 11 && /^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[07])\d{8}$/.test(value));
    }, "请正确填写您的手机号码。");

    jQuery.validator.addMethod("stringCheck", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);
    }, "只能包含中文、英文、数字、下划线等字符");
    $('form.form-horizontal').validate({
            rules:{
                //管理员真实姓名
                'administrator.truename':{
                    required:true,
                    rangelength:[2,20],
                    stringCheck:true
                },
                //管理员登陆姓名
                'administrator.nickname':{
                    required:true,
                    rangelength:[2,16],
                },
                //密码
                'administrator.adminpwd':{
                    required:true,
                    minlength:6,
                    maxlength:18
                },
                //确认密码
                'administrator.adminpwdconfirm':{
                    required:true,
                    equalTo:"#administratorpassword"
                },
                //手机号
                'administrator.mobile':{
                    required:true,
                    isMobile:true
                }
            },
            messages:{
                //管理员真实姓名
                'administrator.truename':{
                    required:'真实姓名不得为空',
                    rangelength:'请输入{0}-{1}个字符'
                },
                //管理员登陆姓名
                'administrator.nickname':{
                    required:'登陆姓名不得为空',
                    rangelength:'请输入{0}-{1}个字符'
                },
                //密码
                'administrator.adminpwd':{
                    required:'请输入您的密码',
                    minlength:'密码不得低于{0}位',
                    maxlength:'密码不得超过{0}位'
                },
                //确认密码
                'administrator.adminpwdconfirm':{
                    required:'请确认您输入的密码',
                    equalTo:'两次密码输入不一致'
                },
                //手机号
                'administrator.mobile':{
                    required:'请输入您的手机号',
                }
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( "help-block" );

                if ( element.prop( "type" ) === "checkbox" ) {
                    error.insertAfter( element.parent( "label" ) );
                } else {
                    error.insertAfter( element );
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).parents( ".col-md-6" ).addClass( "has-error" ).removeClass( "has-success" );
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).parents( ".col-md-6" ).addClass( "has-success" ).removeClass( "has-error" );
            }

        }
    )
})
