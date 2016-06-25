<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>屏蔽设置</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="optpaychannel.do?saveShielding" beforeSubmit="onCheckOptShieldingParams">
    <input id="id" name="id" type="hidden" value="${passage.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" style="width: 120px;"><label class="Validform_label"> 屏蔽省份: </label></td>
        <td class="value" style="width: 480px;">
        <input type="hidden" name="shieldingProvinceIds" id="shieldingProvinceIds">
        <select class="easyui-combobox" id="provinceIdStrs" name="provinceIdStrs" data-options="multiple:true" style="width:400px;">
          <c:forEach items="${provinces}" var="p">
            <option value="${p.id}">${p.provincenm}</option>
          </c:forEach>
        </select>
        </td>
      </tr>
      <tr>
          <td align="right"><label class="Validform_label"> 屏蔽号段: </label></td>
          <td class="value">
          <input type="hidden" name="shieldingSegments" id="shieldingSegments">
          <table id="optShieldingParam" data-options="toolbar:'#optShieldingParamTb'"></table>
          <div id="optShieldingParamTb" style="padding:1px;height:auto">
            <div>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addOptShieldingParamRow()">添加</a>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetOptShieldingParamRows()">撤销</a>
            </div>
          </div>
          </td>
      </tr>
    </table>
  </t:formvalid>
<script type="text/javascript">
$(function() {
  $('#provinceIdStrs').combobox('setValues', [${passage.shieldingProvinceIds}]);
  
  $('#optShieldingParam').datagrid({
    url : 'optpaychannel.do?segments&id='+'${passage.id}',
    rownumbers : true,
    singleSelect : true,
    pagination : false,
    idField : 'id',
    fitColumns : true,
    fit : false,
    nowarp : false,
    border : true,
    columns : [ [ 
      {field : 'segment',title : '号段',width : 120,editor : {type : 'numberbox',options : {required : true}}}, 
      {field : 'opt',title : '操作',width : 45,
        formatter : function(value, rec, index) {
          var d = '';
          d += '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="delOptShieldingParamRow(' + index + ')">删除</a>';
          return d;
        }} ] ],
    onLoadSuccess:editOptShieldingParamRows
  });
});

function editOptShieldingParamRows() {
  $('.easyui-linkbutton').linkbutton({});
  var len = $('#optShieldingParam').datagrid('getRows').length;
  for (var i = 0; i < len; i++) {
    $('#optShieldingParam').datagrid('beginEdit', i);
  }
}

var editIndex = undefined;
function isOptShieldingParamAdded() {
  if (editIndex == undefined) {
    return true;
  }
  if ($('#optShieldingParam').datagrid('validateRow', editIndex)) {
    editIndex = undefined;
    return true;
  } else {
    return false;
  }
}

function addOptShieldingParamRow() {
  if (isOptShieldingParamAdded()) {
    $('#optShieldingParam').datagrid('appendRow', {});
    editIndex = $('#optShieldingParam').datagrid('getRows').length - 1;
    $('#optShieldingParam').datagrid('beginEdit', editIndex);
    $('.easyui-linkbutton').linkbutton({});
  }
}

function resetOptShieldingParamRows() {
  $('#optShieldingParam').datagrid('rejectChanges');
  editIndex = undefined;
}

function delOptShieldingParamRow(idx) {
  $('#optShieldingParam').datagrid('cancelEdit', idx).datagrid('deleteRow', idx);
  editIndex = undefined;
  //var rows = $('#optShieldingParam').datagrid("getRows");
  //$('#optShieldingParam').datagrid("loadData",rows);
}

function editOptShieldingParamsEnd() {
  var len = $('#optShieldingParam').datagrid('getRows').length;
  var ended = true;
  for (var i = 0; i < len; i++) {
    ended = $('#optShieldingParam').datagrid('validateRow', i);
    if (!ended) {
      $('#optShieldingParam').datagrid('selectRow', i);
      return ended;
    }
  }
  for (var i = 0; i < len; i++) {
    $('#optShieldingParam').datagrid('endEdit', i);
  }
  editIndex = undefined;
  return ended;
}

function onCheckOptShieldingParams() {
  if (!editOptShieldingParamsEnd()) {
    return false;
  }
  
  $("#shieldingSegments").val("");
  var rows = $('#optShieldingParam').datagrid('getRows');
  if (rows.length > 0) {
    var segments = "";
    for (var i = 0; i < rows.length; i++) {
      segments = segments + rows[i].segment + ",";
    }
    segments = segments.substring(0, segments.length - 1);
    $("#shieldingSegments").val(segments);
  }
  
  $("#shieldingProvinceIds").val("");
  var shieldingProvinceIds = "";
  var provinceIds = $("input[name='provinceIdStrs']");
  if (provinceIds.length > 0) {
    for (var i = 0; i < provinceIds.length; i++) {
      shieldingProvinceIds = shieldingProvinceIds + provinceIds[i].value + ",";
    }
    shieldingProvinceIds = shieldingProvinceIds.substring(0, shieldingProvinceIds.length - 1);
    $("#shieldingProvinceIds").val(shieldingProvinceIds);
  }

  return true;
}
</script>
</body>