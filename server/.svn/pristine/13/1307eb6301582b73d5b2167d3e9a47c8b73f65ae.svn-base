<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>

<body>
 
	<script>
		
		function removeShieldDiv(obj){
			$(obj).parent().parent().remove();
		}
		function addShieldDiv(obj){
			var html=$(obj).parent().parent().parent().find("tr").eq(1).prop("outerHTML");
			$(obj).parent().parent().parent().append(html);
			var size=$(obj).parent().parent().parent().find("tr").size()-1;
			$(obj).parent().parent().parent().find("tr").eq(size).find("td").eq(2).remove();
			$(obj).parent().parent().parent().find("tr").eq(size).append("<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeShieldDiv(this);return false;'><font color=blue>移除</font></a></td>")
		}
		
		function removeSendDiv(obj){
			var index=$(obj).parent().parent().parent().find("tr").index($(obj).parent().parent())-1;
			$(obj).parent().parent().remove();
			$(".shieldDivClass").eq(index).remove();
		}
		function addSendDiv(obj){
			
			var html=$(obj).parent().parent().parent().find("tr").eq(1).prop("outerHTML");
			$(obj).parent().parent().parent().append(html);
			var size=$(obj).parent().parent().parent().find("tr").size()-1;
			$(obj).parent().parent().parent().find("tr").eq(size).find("td").eq(2).remove();
			$(obj).parent().parent().parent().find("tr").eq(size).append("<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeSendDiv(this);return false;'><font color=blue>移除</font></a></td>")
			$(".shieldDivClass").parent().append($(".shieldDivClass").eq(0).prop("outerHTML"));
			
			
		}
		
		
	</script>
	<script type="text/javascript">
	function onCheckData(){
		
		if (!editOptPayingParamsEnd()) {
    		return false;
  		}
  
  		$("#chargetipPeriods").val("");
  		var rows = $('#optPayingParam').datagrid('getRows');
  		if (rows.length > 0) {
    		var rowsJson = JSON.stringify(rows);
    		$("#chargetipPeriods").val(rowsJson);
  		}
  		
  		if (!editcstParamsEnd()) {
    		return false;
  		}
  
  		$("#chargesuceesstipPeriods").val("");
  		var rows = $('#cstParam').datagrid('getRows');
  		if (rows.length > 0) {
    		var rowsJson = JSON.stringify(rows);
    		$("#chargesuceesstipPeriods").val(rowsJson);
  		}
	
		
		var mmdo_content_split="#";
		var mmdo_sub_content_split="|";
		//组合send
		var numberArray;
		var sendNumberClass=$(".sendTable .sendNumberClass");
		var size=sendNumberClass.size();
		for(var i=0;i<size;i++){
			if(i!=0){
				numberArray+=mmdo_content_split+sendNumberClass.eq(i).val();
			}else{
				numberArray=sendNumberClass.eq(i).val();
			}
		}
		$("#number").val(numberArray);
		
		
		var contentArray;
		var sendContentClass=$(".sendTable .sendContentClass");
		var contentSize=sendContentClass.size();
		for(var j=0;j<contentSize;j++){
			if(j!=0){
				contentArray+=mmdo_content_split+sendContentClass.eq(j).val();
			}else{
				contentArray=sendContentClass.eq(j).val();
			}
		}
		$("#sendContent").val(contentArray);
		
		//组合shield
		var shieldDivClass=$(".shieldDivClass");
		var shieldSize=shieldDivClass.size();
		var shieldNumber;
		var shieldKeyword;
		for(var n=0;n<shieldSize;n++){
			var shieldDivSubClass=shieldDivClass.eq(n);
			var shieldNumberDivClass=shieldDivSubClass.find(".shieldNumberDivClass");
			var shieldNumberSize=shieldNumberDivClass.size();
			var shieldNumberArray;
			for(var k=0;k<shieldNumberSize;k++){
				if(k==0){
					shieldNumberArray=shieldNumberDivClass.eq(k).val();
				}else{
					shieldNumberArray+=mmdo_sub_content_split+shieldNumberDivClass.eq(k).val();
				}
			}
			
			var shieldKeywordDivClass=shieldDivSubClass.find(".shieldKeywordDivClass");
			var shieldKeywordSize=shieldKeywordDivClass.size();
			var shieldKeywordArray;
			
			for(var l=0;l<shieldKeywordSize;l++){
				if(l==0){
					shieldKeywordArray=shieldKeywordDivClass.eq(l).val();
				}else{
					shieldKeywordArray+=mmdo_sub_content_split+shieldKeywordDivClass.eq(l).val();
				}
			}
			
			if(n==0){
				shieldNumber=shieldNumberArray;
				shieldKeyword=shieldKeywordArray;
			}else{
				shieldNumber+=mmdo_content_split+shieldNumberArray;
				shieldKeyword+=mmdo_content_split+shieldKeywordArray;
			}
		}
		$("#shieldNumber").val(shieldNumber);
		$("#shieldKeyword").val(shieldKeyword);
		return true;
				
	}
	
	
	
	
	
	//----以下是计费提示框打开时段列表设置-----
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
    		url : 'mmdoSetting.do?periods&id='+'${mmdoSetting.id}',
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
    $('#optPayingParam').datagrid('appendRow', {}); //添加一空行
    editIndex = $('#optPayingParam').datagrid('getRows').length - 1; //返回当前页的记录。
    $('#optPayingParam').datagrid('beginEdit', editIndex); //对最后一行（即新增的空行）开启编辑
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
//----以上是计费提示框打开时段列表设置-----



//----以下是计费成功提示框打开时段列表设置-----
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
    		url : 'mmdoSetting.do?cstperiods&id='+'${mmdoSetting.id}',
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
//----以上是计费成功提示框打开时段列表设置-----
		
</script>

	<t:formvalid formid="formobj" layout="div" dialog="true" action="mmdoSetting.do?save" beforeSubmit="onCheckData">
		<input id="id" name="id" type="hidden" value="${mmdoSetting.id}">
		<input type="hidden" id="propsID" value="${mmdoSetting.propsid}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
            <tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
                <!--  <option value="">全部</option> -->
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==mmdoSetting.gameId}">selected="selected"</c:if>>
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
            <tr>
             <td align="center"><label class="Validform_label"> 支付渠道: </label>
             </td>
             <td class="value">
              <select id="operatorPayChannelId"  name="operatorPayChannelId"  datatype="*">
               <c:forEach items="${payChannels}" var="payChannel">
                <option value="${payChannel.id}" <c:if test="${payChannel.id==mmdoSetting.operatorPayChannelId}">selected="selected"</c:if>>
                 ${payChannel.channelName}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			<tr>
				<td align="center"><label class="Validform_label"> 金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="amount" id="amount" value="${mmdoSetting.amount}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label"> 实际发送金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="realAmount" id="realAmount" value="${mmdoSetting.realAmount}" > </td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 间隔: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="interval" id="interval" value="${mmdoSetting.interval}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            <tr>
                <td align="center"><label class="Validform_label"> 优先级: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="idx" id="idx" value="${mmdoSetting.idx}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 道具ID: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="propsid" id="propsid" value="${mmdoSetting.propsid}" > </td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="useStatus" name="useStatus">
                    <option value="1"  <c:if test="${mmdoSetting.useStatus==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${mmdoSetting.useStatus==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
            
            <tr>
             <td align="center"><label class="Validform_label"> 计费代码配置的原本游戏: </label>
             </td>
             <td class="value">
              <select id="originalGameId"  name="originalGameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==mmdoSetting.originalGameId}">selected="selected"</c:if>>
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
            
            <tr>
				<td align="center"><label class="Validform_label"> 代计费原游戏名: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="originalGameName" id="originalGameName" value="${mmdoSetting.originalGameName}"> </td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 支付中提示语: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="sendingtip" id="sendingtip" value="${mmdoSetting.sendingtip}"> </td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 统计次道具使用次数的标志符: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="tjpropsname" id="tjpropsname" value="${mmdoSetting.tjpropsname}"> </td>
			</tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 计费提示框状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="chargetip" name="chargetip">
                    <option value="1"  <c:if test="${mmdoSetting.chargetip==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${mmdoSetting.chargetip==0}">selected</c:if>>停用</option>
                </select>               
                </td>
            </tr>
            
            <tr>
          		<td align="right"><label class="Validform_label"> 计费提示框打开时段列表: </label></td>
         		<td class="value">
          		<input type="hidden" name="chargetipPeriods" id="chargetipPeriods">
          		<table id="optPayingParam" data-options="toolbar:'#optPayingParamTb'"></table>
          		<div id="optPayingParamTb" style="padding:1px;height:auto">
            	<div>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addOptPayingParamRow()">添加</a>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetOptPayingParamRows()">撤销</a>
            	</div>
          		</div>
          		</td>
      		</tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 计费成功提示框状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="chargesuceesstip" name="chargesuceesstip">
                    <option value="1"  <c:if test="${mmdoSetting.chargesuceesstip==1}">selected</c:if>>使用</option> 
                    <option value="0"  <c:if test="${mmdoSetting.chargesuceesstip==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
            <tr>
          		<td align="right"><label class="Validform_label"> 计费成功提示框打开时段列表: </label></td>
         		<td class="value">
          		<input type="hidden" name="chargesuceesstipPeriods" id="chargesuceesstipPeriods">
          		<table id="cstParam" data-options="toolbar:'#cstParamTb'"></table>
          		<div id="cstParamTb" style="padding:1px;height:auto">
            	<div>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addcstParamRow()">添加</a>
              		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="resetcstParamRows()">撤销</a>
            	</div>
          		</div>
          		</td>
      		</tr>
      		
      		<tr>
				<td align="center"><label class="Validform_label"> loading提示框弹出秒数: </label></td>
				<c:choose>
        			<c:when test="${empty mmdoSetting.loadingtipmin}">
        		 	<td class="value" style="width: 400px"><input class="longInput" name="loadingtipmin" id="loadingtipmin" value="3"></td>
        			</c:when>
        			<c:otherwise>
        			<td class="value" style="width: 400px"><input class="longInput" name="loadingtipmin" id="loadingtipmin" value="${mmdoSetting.loadingtipmin}"></td>
        			</c:otherwise>
        		</c:choose>
				
			</tr>
			
            <tr>
                <td align="center"><label class="Validform_label"> 失败提示框状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="chargefailtip" name="chargefailtip">
                    <option value="1"  <c:if test="${mmdoSetting.chargefailtip==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${mmdoSetting.chargefailtip==0}">selected</c:if>>停用</option>
                </select>               
                </td>
            </tr>
      		
      		<tr>
                <td align="center"><label class="Validform_label"> 是否补点计费: </label></td>
                <td class="value" style="width: 400px">
                <select id="additional" name="additional">
                    <option value="0"  <c:if test="${mmdoSetting.additional==0}">selected</c:if>>停用</option> 
                    <option value="1"  <c:if test="${mmdoSetting.additional==1}">selected</c:if>>使用</option>
                </select>
                </td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 补点计费列表(以#为分隔符): </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="addList" id="addList" value="${mmdoSetting.addList}" > </td>
            </tr>
            
			<tr>
				<td align="center"><label class="Validform_label"> 发送信息配置: </label></td>
				<td><input type="hidden" value="${mmdoSetting.number}" name="number" id="number"> <input type="hidden" value="${mmdoSetting.sendContent}" name="sendContent" id="sendContent">
					<div class=sendDiv>
						<table class="sendTable">
							<tr>
								<td><label class="Validform_label">发送端口号</label></td>
								<td><label class="Validform_label">发送内容</label></td>
								<td></td>
							</tr>
							<c:if test="${opt=='add'}" >
								<tr>
								<td><input class="sendNumberClass" value="" datatype="*"> <span class="Validform_checktip"></span></td>
								<td><input class="sendContentClass" value="" datatype="*"> <span class="Validform_checktip"></span></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="addSendDiv(this);return false;"><font color=blue>增加</font></a>
								</td>
								</tr>
							</c:if>
							<c:if test="${opt=='edit'}">
								<!-- 修改-->
								<c:forEach items="${sendMap}" var="send" varStatus="i">
									<tr>
										<td><input class="sendNumberClass" value="${send.key}" datatype="*"> <span class="Validform_checktip"></span></td>
										<td><input class="sendContentClass" value="${send.value}" datatype="*"> <span class="Validform_checktip"></span></td>
										<td><c:if test="${ i.count == 1}">
				       					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="addSendDiv(this);return false;"><font color=blue>增加</font></a>
											</c:if> <c:if test="${ i.count != 1}">
				       					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="removeSendDiv(this);return false;"><font color=blue>移除</font></a>
											</c:if></td>
									</tr>

								</c:forEach>
							</c:if>
						</table>
					</div></td>

			</tr>

			<tr>
				<td align="center"><label class="Validform_label"> 屏蔽端口: </label></td>
				<td class="value" style="width: 400px">
					<div>
						<input type="hidden" name="shieldNumber" id="shieldNumber" value="${mmdoSetting.shieldNumber}"> <input type="hidden" name="shieldKeyword" id="shieldKeyword"
							value="${mmdoSetting.shieldKeyword}">
						<c:if test="${opt=='add'}" >
							<div class="shieldDivClass">
							<table>
										<tr>
											<td><label class="Validform_label">屏蔽端口号</label></td>
											<td><label class="Validform_label">屏蔽关键字</label></td>
											<td></td>
										</tr>
										<tr>
												<td><input class="shieldNumberDivClass" value="" datatype="*"> <span class="Validform_checktip"></span></td>
												<td><input class="shieldKeywordDivClass" value="" datatype="*"> <span class="Validform_checktip"></span></td>
												<td>
				       					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="addShieldDiv(this);return false;"><font color=blue>增加</font></a>
												</td>
											</tr>
							</div>
						</c:if>
						<c:if test="${opt=='edit'}">
							<c:forEach items="${shieldGroupList}" var="shieldGroupMap" varStatus="i">
								<div class="shieldDivClass">
									<table>
										<tr>
											<td><label class="Validform_label">屏蔽端口号</label></td>
											<td><label class="Validform_label">屏蔽关键字</label></td>
											<td></td>
										</tr>
										<c:forEach items="${shieldGroupMap}" var="shieldNumber" varStatus="i">
											<tr>
												<td><input class="shieldNumberDivClass" value="${shieldNumber.key}" datatype="*"> <span class="Validform_checktip"></span></td>
												<td><input class="shieldKeywordDivClass" value="${shieldNumber.value}" datatype="*"> <span class="Validform_checktip"></span></td>
												<td><c:if test="${ i.count == 1}">
				       					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="addShieldDiv(this);return false;"><font color=blue>增加</font></a>
													</c:if> <c:if test="${ i.count != 1}">
				       					&nbsp;&nbsp;&nbsp;&nbsp;<a href="#j" onclick="removeShieldDiv(this);return false;"><font color=blue>移除</font></a>
													</c:if></td>
											</tr>

										</c:forEach>
									</table>
								</div>
							</c:forEach>
						</c:if>

					</div>
				</td>
			</tr>

		</table>
	</t:formvalid>


</body>