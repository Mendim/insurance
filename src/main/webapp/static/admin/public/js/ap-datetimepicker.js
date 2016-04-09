function apDatetimepicker(BtnNameId){
    $('#'+BtnNameId).datetimepicker({
        language : 'zh-CN',
        autoclose : true,
        format : "yyyy-mm-dd hh:ii",
        pickerPosition : "bottom-left",
        todayBtn : 1,
        todayHighlight : true,
        minuteStep : 1
    });
    $("#"+BtnNameId).on("dp.show",function (e) {
        var newtop = $('.bootstrap-datetimepicker-widget').position().top - 45;
        $('.bootstrap-datetimepicker-widget').css('top', newtop + 'px');
    });
}