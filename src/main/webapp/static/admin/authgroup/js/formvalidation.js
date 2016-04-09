/**
 * Created by user on 2016/3/301.
 */
$(function(){
    jQuery.validator.addMethod("stringCheck", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);
    }, "只能包含中文、英文、数字、下划线等字符");
    $('form.form-horizontal').validate({
        rules:{
            //
            'authGroup.name':{
                required:true,
                maxlength:20,
                stringCheck:true
            },
            //
            'authGroup.description':{
                required:true,
                maxlength:80
            },
        },
        messages:{
            'authGroup.title':{
                required:'请输入组名',
                maxlength:'不得超过{0}个字符'
            },
            'authGroup.description':{
                required:'请输入组描述',
                maxlength:'不得超过{0}个字符'
            },

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
            $( element ).parents( ".col-sm-10" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".col-sm-10" ).addClass( "has-success" ).removeClass( "has-error" );
        }

    })
})