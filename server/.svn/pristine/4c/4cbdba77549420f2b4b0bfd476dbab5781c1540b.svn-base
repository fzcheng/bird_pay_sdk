<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>计费设置</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="optpaychannel.do?savePaying" beforeSubmit="onCheckOptPayingParams">
    <input id="id" name="id" type="hidden" value="${passage.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" style="width: 120px;"><label class="Validform_label"> 计费省份: </label></td>
        <td class="value" style="width: 480px;">
        <select id="billingProvinceId" name="billingProvinceId">
            <option value="">全网</option>
            <c:forEach items="${provinces}" var="province">
              <option value="${province.id}" <c:if test="${province.id == passage.billingProvinceId}">selected="selected"</c:if>>${province.provincenm}</option>
            </c:forEach>
        </select>
        </td>
      </tr>
      <tr>
          <td align="right"><label class="Validform_label"> 计费时段: </label></td>
          <td class="value">
          <input type="hidden" name="billingPeriods" id="billingPeriods">
          <table id="optPayingParam" data-options="toolbar:'#optPayingParamTb'"></table>
          <div id="optPayingParamTb" style="padding:1px;height:auto">
            <div>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addOptPayingParamRow()">添加</a>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetOptPayingParamRows()">撤销</a>
            </div>
          </div>
          </td>
      </tr>
    </table>
  </t:formvalid>
  
<script type="text/javascript">
$(function() {
  $.extend($.fn.datagrid.defaults.editors, {
    timespinner : {
      init : function(container, options) {
        var input = $('<input/>').appendTo(container);
        input.timespinner(options);
        return input;
      },
      destroy: function(target){
        $(target).remove();
      },
      getValue: function(target){
        return $(target).val();
      },
      setValue: function(target, value){
        $(target).val(value);
      },
      resize : function(target, width) {
        var input = $(target);
        if ($.boxModel == true) {
          input.resize('resize', width - (input.outerWidth() - input.width()));
        } else {
          input.resize('resize', width);
        }
      }
    }
  });
  
  $('#optPayingParam').datagrid({
    url : 'optpaychannel.do?periods&id='+'${passage.id}',
    rownumbers : true,
    singleSelect : true,
    pagination : false,
    idField : 'id',
    fitColumns : true,
    fit : false,
    nowarp : false,
    border : true,
    columns : [ [ 
      {field : 'beginTime',title : '开始时间',width : 80,editor : {type : 'timespinner',options : {required : true}}}, 
      {field : 'endTime',title : '结束时间',width : 80,editor : {type : 'timespinner',options : {required : true}}}, 
      {field : 'opt',title : '操作',width : 45,
        formatter : function(value, rec, index) {
          var d = '';
          d += '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="delOptPayingParamRow(' + index + ')">删除</a>';
          return d;
        }} ] ],
    onLoadSuccess:editOptPayingParamRows
  });
});

function editOptPayingParamRows() {
  $('.easyui-linkbutton').linkbutton({});
  var len = $('#optPayingParam').datagrid('getRows').length;
  for (var i = 0; i < len; i++) {
    $('#optPayingParam').datagrid('beginEdit', i);
  }
}

var editIndex = undefined;
function isOptPayingParamAdded() {
  if (editIndex == undefined) {
    return true;
  }
  if ($('#optPayingParam').datagrid('validateRow', editIndex)) {
    editIndex = undefined;
    return true;
  } else {
    return false;
  }
}

function addOptPayingParamRow() {
  if (isOptPayingParamAdded()) {
    $('#optPayingParam').datagrid('appendRow', {});
    editIndex = $('#optPayingParam').datagrid('getRows').length - 1;
    $('#optPayingParam').datagrid('beginEdit', editIndex);
    $('.easyui-linkbutton').linkbutton({});
  }
}

function resetOptPayingParamRows() {
  $('#optPayingParam').datagrid('rejectChanges');
  editIndex = undefined;
}

function delOptPayingParamRow(idx) {
  $('#optPayingParam').datagrid('cancelEdit', idx).datagrid('deleteRow', idx);
  editIndex = undefined;
}

function editOptPayingParamsEnd() {
  var len = $('#optPayingParam').datagrid('getRows').length;
  var ended = true;
  for (var i = 0; i < len; i++) {
    ended = $('#optPayingParam').datagrid('validateRow', i);
    if (!ended) {
      $('#optPayingParam').datagrid('selectRow', i);
      return ended;
    }
  }
  for (var i = 0; i < len; i++) {
    $('#optPayingParam').datagrid('endEdit', i);
  }
  editIndex = undefined;
  return ended;
}

function onCheckOptPayingParams() {
  if (!editOptPayingParamsEnd()) {
    return false;
  }
  
  $("#billingPeriods").val("");
  var rows = $('#optPayingParam').datagrid('getRows');
  if (rows.length > 0) {
    var rowsJson = JSON.stringify(rows);
    $("#billingPeriods").val(rowsJson);
  }
  return true;
}
</script>
</body>