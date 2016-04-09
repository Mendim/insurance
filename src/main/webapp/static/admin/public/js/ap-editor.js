/**
 * editorId为指定的input的id,该input用于提交表单
 * 每次当editor失去焦点,会将用户输入的内容放在该input中
 * @param editorId
 */
function apEditorInit(editorId){

    $('#'+editorId).summernote({
        toolbar: [
            //['style', ['style']], // no style button
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            //['insert', ['picture', 'link']], // no insert buttons
            //['table', ['table']], // no table button
            //['help', ['help']] //no help button
        ],
        height: 137, //set editable area's height
        onblur: function(){
            $('#'+editorId).val($('#'+editorId).code());
        },
        oninit: function(){
            $('#'+editorId).code($('#'+editorId).val());
        }
    });


}