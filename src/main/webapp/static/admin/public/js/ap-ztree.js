/**
 * 单选择树,可以在树的节点上点击选择单个节点,有子节点的节点不能选择,只能选择叶节点.
 * nodesJson是node组成的json
 * @param nodesJson
 */
function apSingleSelectTreeInit(nodesJson, selectNodeId){

    var setting = {
        view: {
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: zTreeOnClick,
            beforeClick: zTreeBeforeClick
        }
    };

    function zTreeBeforeClick(treeId, treeNode, clickFlag){
        if(treeNode.isParent){
            var zTree = $.fn.zTree.getZTreeObj("ProductCateTree");
            zTree.expandNode(treeNode);
            return false;
        }
    }
    function zTreeOnClick(event, treeId, treeNode){
        if(treeNode.isParent){
            return false;
        }else{
            $('#ProductCateId').val(treeNode.id);
        }
    }

    for(var i=0;i<nodesJson.length;i++){
        if(!nodesJson[i].pId){
            nodesJson[i].open = true;
            break;
        }
    }
    var treeObj = $.fn.zTree.init($("#ProductCateTree"), setting, nodesJson);
    var selectNode = treeObj.getNodeByParam("id", selectNodeId);
    treeObj.selectNode(selectNode);
}