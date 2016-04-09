/**
 * Created by user on 2016/3/31.
 */
$(function(){
    $('form.form-horizontal').validate({
        rules:{
            //产品名称
            'productKaDan.name':{
                required:true,
                maxlength:45,
            },
            //产品描述
            'productKaDan.description':{
                required:true,
                maxlength:45
            },
            //价格
            'productKaDan.price':{
                required:true,
                digits:true,
                maxlength:11
            },
            //保险分类
            'productKaDan.card_cate':{
                required:true,
                maxlength:45
            },
            //保险要求
            'productKaDan.conditions':{
                required:true,
                maxlength:45
            },
            //卡单内容
            'productKaDan.textinfo':{
                required:true,
                maxlength:0
            },
            'productKaDan.product.start_date':{
                required:true,
                minlength:5
            },
            'productKaDan.product.end_date':{
                required:true,
                minlength:5
            }
        },
        messages:{
            //产品名称
            'productKaDan.name':{
                required:'请输入产品名称',
                maxlength:'不得超过{0}个字符'
            },
            //产品描述
            'productKaDan.description':{
                required:'请输入产品描述',
                maxlength:'不得超过{0}个字符'
            },
            //价格
            'productKaDan.price':{
                required:'请输入产品价格',
                digits:'请输入正整数',
                maxlength:'价格不得超过{0}位'
            },
            //保险分类
            'productKaDan.card_cate':{
                required:'请输入保险分类',
                maxlength:'不得超过{0}个字符'
            },
            //保险要求
            'productKaDan.conditions':{
                required:'请输入保险要求',
                maxlength:'不得超过{0}个字符'
            },
            //卡单内容
            'productKaDan.textinfo':{
                required:'请输入卡单内容',
                maxlength:'不得超过{0}个字符'
            },
            'productKaDan.product.start_date':{
                required:'请选择开始时间',
                minlength:'请选择开始时间'
            },
            'productKaDan.product.end_date':{
                required:'请选择结束时间',
                minlength:'请选择结束时间'
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

    })
})