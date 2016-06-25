<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>运营商计费通道编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="optpaypassage.do?save" beforeSubmit="onCheckOptPassageParams">
    <input id="id" name="id" type="hidden" value="${passage.id}">
    <input id="sdkPayment_id" name="sdkPayment.id" type="hidden" value="9">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" style="width: 120px;"><label class="Validform_label"> 运营商: </label></td>
        <td class="value" style="width: 480px;"><select id="sdkOperatorPayment_id" name="sdkOperatorPayment.id" datatype="*">
            <option value="">请选择</option>
            <c:forEach items="${payments}" var="payment">
              <option value="${payment.id}" <c:if test="${payment.id == passage.sdkOperatorPayment.id}">selected="selected"</c:if>>${payment.name}</option>
            </c:forEach>
        </select> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费通道名: </label></td>
        <td class="value"><input class="inputxt" name="name" id="name" value="${passage.name}" datatype="s2-10" errormsg="计费方式中文名为2~10字符"> <span
          class="Validform_checktip">计费通道名为2~10字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费通道编号: </label></td>
        <td class="value"><input class="inputxt" name="code" id="code" value="${passage.code}" ajaxurl="optpaypassage.do?valid&id=${passage.id}"
          datatype="/^[0-9a-zA-Z_]{2,20}$/" errormsg="计费通道编号只能包含字符、数字，2~20位"> <span class="Validform_checktip">计费通道编号只能包含字符、数字、_，2~20位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 支持sdk最低版本: </label></td>
        <td class="value"><input class="inputxt" name="sdkMinVer" id="sdkMinVer" value="${passage.sdkMinVer}" datatype="s2-10" errormsg="支持sdk最低版本，2~20位">
          <span class="Validform_checktip"> 支持sdk最低版本，2~20位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费省份: </label></td>
        <td class="value"><select id="billingProvinceId" name="billingProvinceId">
            <option value="">全网</option>
            <c:forEach items="${provinces}" var="province">
              <option value="${province.id}" <c:if test="${province.id == passage.billingProvinceId}">selected="selected"</c:if>>${province.provincenm}</option>
            </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 指令方式: </label></td>
        <td class="value"><select id="smsType" name="smsType" datatype="*">
            <option value="">请选择</option>
            <option value="1" <c:if test="${passage.smsType==1}">selected</c:if>>短信指令</option>
            <option value="2" <c:if test="${passage.smsType==2}">selected</c:if>>网络获取</option>
            <option value="3" <c:if test="${passage.smsType==3}">selected</c:if>>SDK接入</option>
        </select></td>
      </tr>
      <tr id="urlTr" <c:if test="${passage.smsType!=2}">style="display: none"</c:if>>
        <td align="right"><label class="Validform_label"> 获取地址: </label></td>
        <td class="value"><input class="inputxt" name="smsFetchUrl" id="smsFetchUrl" value="${passage.smsFetchUrl}" <c:if test="${passage.smsType!=2}">ignore="ignore"</c:if>
          datatype="url" errormsg="请填写正确的URL地址"> <span class="Validform_checktip">获取地址为URL地址</span></td>
      </tr>
      <tr>
          <td align="right"><label class="Validform_label"> 接入参数: </label></td>
          <td class="value">
          <input type="hidden" name="insertParams" id="insertParams">
          <input type="hidden" name="updateParams" id="updateParams">
          <input type="hidden" name="deleteParams" id="deleteParams">
          <table id="optPassageParam" data-options="toolbar:'#optPassageParamTb'"></table>
          <div id="optPassageParamTb" style="padding:1px;height:auto">
            <div>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addOptPassageParamRow()">添加</a>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetOptPassageParamRows()">撤销</a>
            </div>
          </div>
          </td>
      </tr>
    </table>
  </t:formvalid>
<script type="text/javascript">
$(function() {
  $("#smsType").change(function() {
    var val = $(this).val();
    if (val == 2) {
      $("#smsFetchUrl").removeAttr("ignore");
      $("#urlTr").show();
    } else {
      $("#urlTr").hide();
      $("#smsFetchUrl").val("");
      $("#smsFetchUrl").attr("ignore","ignore");
    }
  });
  $('#optPassageParam').datagrid({
    url : 'optpaypassage.do?params&payPassageId='+'${passage.id}',
    rownumbers : true,
    singleSelect : true,
    pagination : false,
    idField : 'id',
    fitColumns : true,
    fit : false,
    nowarp : false,
    border : true,
    columns : [ [ 
      {field : 'name',title : '名称',width : 80,editor : {type : 'validatebox',options : {required : true}}}, 
      {field : 'param',title : '参数',width : 80,editor : {type : 'validatebox',options : {required : true}}}, 
      {field : 'val',title : '参数值',width : 80,editor : {type : 'validatebox',options : {required : true}}}, 
      {field : 'opt',title : '操作',width : 45,
        formatter : function(value, rec, index) {
          var d = '';
          d += '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="delOptPassageParamRow(' + index + ')">删除</a>';
          return d;
        }} ] ],
    onLoadSuccess:editOptPassageParamRows
  });
});

var editIndex = undefined;
function isOptPassageParamAdded() {
  if (editIndex == undefined) {
    return true;
  }
  if ($('#optPassageParam').datagrid('validateRow', editIndex)) {
    editIndex = undefined;
    return true;
  } else {
    return false;
  }
}

function addOptPassageParamRow() {
  if (isOptPassageParamAdded()) {
    $('#optPassageParam').datagrid('appendRow', {});
    editIndex = $('#optPassageParam').datagrid('getRows').length - 1;
    $('#optPassageParam').datagrid('beginEdit', editIndex);
    $('.easyui-linkbutton').linkbutton({});
  }
}

function resetOptPassageParamRows() {
  $('#optPassageParam').datagrid('rejectChanges');
  editIndex = undefined;
}

function delOptPassageParamRow(idx) {
  $('#optPassageParam').datagrid('cancelEdit', idx).datagrid('deleteRow', idx);
  editIndex = undefined;
}

function editOptPassageParamRows() {
  $('.easyui-linkbutton').linkbutton({});
  var len = $('#optPassageParam').datagrid('getRows').length;
  for (var i = 0; i < len; i++) {
    $('#optPassageParam').datagrid('beginEdit', i);
  }
}

function editOptPassageParamsEnd() {
  var len = $('#optPassageParam').datagrid('getRows').length;
  var ended = true;
  for (var i = 0; i < len; i++) {
    ended = $('#optPassageParam').datagrid('validateRow', i);
    if (!ended) {
      $('#optPassageParam').datagrid('selectRow', i);
      return ended;
    }
  }
  for (var i = 0; i < len; i++) {
    $('#optPassageParam').datagrid('endEdit', i);
  }
  editIndex = undefined;
  return ended;
}

function onCheckOptPassageParams() {
  if (!editOptPassageParamsEnd()) {
    return false;
  }
  var insRows = $('#optPassageParam').datagrid('getChanges', 'inserted');
  if (insRows.length > 0) {
    var insJson = JSON.stringify(insRows);
    $("#insertParams").val(insJson);
  }
  var updRows = $('#optPassageParam').datagrid('getChanges', 'updated');
  if (updRows.length > 0) {
    var updJson = JSON.stringify(updRows);
    $("#updateParams").val(updJson);
  }
  var delRows = $('#optPassageParam').datagrid('getChanges', 'deleted');
  if (delRows.length > 0) {
    var delJson = JSON.stringify(delRows);
    $("#deleteParams").val(delJson);
  }
  return true;
}
</script>
</body>