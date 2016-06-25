<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>道具添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
<script type="text/javascript">
function onCheckData(){
  		if (!editcstParamsEnd()) {
    		return false;
  		}
  
  		$("#periods").val("");
  		var rows = $('#cstParam').datagrid('getRows');
  		if (rows.length > 0) {
    		var rowsJson = JSON.stringify(rows);
    		$("#periods").val(rowsJson);
  		}
		return true;
	}

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
  
  		$('#cstParam').datagrid({
    		url : 'sdkProps.do?period&id='+'${sdkProps.id}',
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
          		d += '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="delcstParamRow(' + index + ')">删除</a>';
          		return d;
        	}} ] ],
    		onLoadSuccess:editcstParamRows
  		});
	});

function editcstParamRows() {
  $('.easyui-linkbutton').linkbutton({});
  var len = $('#cstParam').datagrid('getRows').length;
  for (var i = 0; i < len; i++) {
    $('#cstParam').datagrid('beginEdit', i);
  }
}

function delcstParamRow(idx) {
  $('#cstParam').datagrid('cancelEdit', idx).datagrid('deleteRow', idx);
  editIndex2 = undefined;
}

function resetcstParamRows() {
  $('#cstParam').datagrid('rejectChanges');
  editIndex2 = undefined;
}

function addcstParamRow() {
  if (iscstParamAdded()) {
    $('#cstParam').datagrid('appendRow', {}); //添加一空行
    editIndex2 = $('#cstParam').datagrid('getRows').length - 1; //返回当前页的记录。
    $('#cstParam').datagrid('beginEdit', editIndex2); //对最后一行（即新增的空行）开启编辑
    $('.easyui-linkbutton').linkbutton({});
  }
}

var editIndex2 = undefined;
function iscstParamAdded() {
  if (editIndex2 == undefined) {
    return true;
  }
  if ($('#cstParam').datagrid('validateRow', editIndex2)) {
    editIndex2 = undefined;
    return true;
  } else {
    return false;
  }
}

function editcstParamsEnd() {
  var len = $('#cstParam').datagrid('getRows').length;
  var ended = true;
  for (var i = 0; i < len; i++) {
    ended = $('#cstParam').datagrid('validateRow', i);
    if (!ended) {
      $('#cstParam').datagrid('selectRow', i);
      return ended;
    }
  }
  for (var i = 0; i < len; i++) {
    $('#cstParam').datagrid('endEdit', i);
  }
  editIndex2 = undefined;
  return ended;
}
</script>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkProps.do?save" beforeSubmit="onCheckData">
		<input id="id" name="id" type="hidden" value="${sdkProps.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
			<tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkProps.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
				<td align="center"><label class="Validform_label"> 道具名称: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsName" id="propsName" value="${sdkProps.propsName}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 道具别名: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsAlias" id="propsAlias" value="${sdkProps.propsAlias}" > </td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="amount" id="amount" value="${sdkProps.amount}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 道具描述: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsDesc" id="propsDesc" value="${sdkProps.propsDesc}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
            <tr>
                <td align="center"><label class="Validform_label">使用状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="useStatus" name="useStatus">
                    <option value="1"  <c:if test="${sdkProps.useStatus==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${sdkProps.useStatus==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
            
           <tr>
          		<td align="center"><label class="Validform_label"> 计费时段列表: </label></td>
         		<td class="value">
          		<input type="hidden" name="periods" id="periods">
          		<table id="cstParam" data-options="toolbar:'#cstParamTb'"></table>
          		<div id="cstParamTb" style="padding:1px;height:auto">
            	<div>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addcstParamRow()">添加</a>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetcstParamRows()">撤销</a>
            	</div>
          		</div>
          		</td>
      		</tr>
      
		</table>
	</t:formvalid>
	
	
</body>