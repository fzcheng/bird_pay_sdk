<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<script type="text/javascript">

 $(function(){$('#sdk_operation_analyze_List').datagrid(
	{
	title: '游戏运营分析',
	url:'gameOperationController.do?datagrid',
	fit:true,
	fitColumns:true,
	singleSelect:true,
	sortOrder:'asc',
	rownumbers:true,
	showFooter:true,
	nowWrap:false,
	frozenColumns:[[]],
	columns:[[
			{field:'apiName',title:'指标',width:50},
			{field:'apiValue',title:'指标值',width:50}
			]]
	});
 });
	//导出
	function exportXls() {
		var submitUrl = "";
		var queryParams = "";
		$('#operation_analyze_Listtb').find('*').each(function(){
				queryParams+= "&"+$(this).attr('name')+"="+$(this).val();
			}
		);
		submitUrl+=queryParams;
		submitUrl = encodeURI(submitUrl);
		 window.location.href=submitUrl;
	}
</script>
<table width="100%"   id="sdk_operation_analyze_List" toolbar="opttt"></table>
<div id="opttt" style="padding:3px; height: auto">
	<div name="searchColums">
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">游戏名称：</span>
		<select name = "game_id" WIDTH="100" style="width: 104px">
		<option value = "">---请选择---</option>
			<% List list =(List) request.getAttribute("game"); %>
			<% for(int i = 0; i < list.size(); i++){ Map map = (Map) list.get(i); %>
				<option value = "<%= map.get("game_id") %>"> <%= map.get("game_name") %> </option>
			<%} %>
		</select>
		</span>
		
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">日期：</span>
		<input type="text" name="stat_day_begin"  style="width: 94px"  class="easyui-datebox" />
		<span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
		<input type="text" name="stat_day__end"  style="width: 94px" class="easyui-datebox" />
		</span>
	</div>
	<div style="height:30px;" class="datagrid-toolbar">
		<span style="float:left;" >
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="exportXls();">导出excel</a>
		</span>
		<span style="float:right">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="Listsearch()">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="">重置</a>
		</span>
	</div>
</div>
