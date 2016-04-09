/**
 * 图片上传组件
 * @param btnNameId   被点击的图片上传按钮的id
 * @param contextPath   当前项目路径, 传入request.getContextPath()即可
 */
function apSingleFileuploadInit(btnNameId, contextPath){
    this.btnNameId = btnNameId;
    this.contextPath = contextPath;

    this.uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: btnNameId,
        url: contextPath+'/admin/singlefileupload/index',
        flash_swf_url: contextPath+'/static/admin/public/swf/Moxie.swf',
        silverlight_xap_url: contextPath+'/static/admin/public/swf/Moxie.swf',
        multipart:true,
        filters: {
            max_file_size: '5mb',
            mime_types: [
                {title: "Files", extensions: "jpg,jpeg,png,bmp,gif"}
            ],
            prevent_duplicates:false
        },
        file_data_name:'fileData',
        multi_selection: false,

        init: {
            FilesAdded: function (up, files) {
                up.start();
            },
            FileUploaded: function(up, file, data) {
                data = $.parseJSON(data.response);
                if(data!=undefined){
                    if(data.status==undefined||data.status==0){
                        alert("["+data.code+"]"+data.message)
                    }else{
                        var status = data.status;
                        if (status != undefined && status == 1) {
                            var url = data.result[0].url;
                            var parent = $("#"+btnNameId).parent().parent();
                            var imgUrl = parent.children('input');
                            var img = parent.children('img');
                            $(imgUrl).val(url);
                            $(img).attr('src', url).show();
                        }
                    }
                }
            },
            UploadProgress: function (up, file) {
                var $percent = $("#"+btnNameId+" span.info");
                if(file.percent==100){
                    $percent.text("");
                }else{
                    $percent.text(file.percent + '%');
                }
            },

            Error: function (up, err) {
                alert("[" + err.code + "]" + err.message)
            }
        }
    });

    this.uploader.init();
    var img = $("#"+btnNameId).parent().parent().children('img');
    if(img.attr('src')){
        img.show();
    }
}