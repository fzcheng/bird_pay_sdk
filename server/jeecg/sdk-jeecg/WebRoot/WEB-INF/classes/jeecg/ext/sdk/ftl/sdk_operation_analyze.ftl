<script type="text/javascript">

 $(function(){
 $('#sdk_operation_analyze_List').datagrid(
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
			]],
	onLoadSuccess:function(data){ var d = data.game_id; var game_id = document.getElementById("game_id"); game_id.value=d; }
	});
 });
 function sdk_operation_analyze_Listsearch(){
 		var queryParams ="";
 		var game_id = document.getElementById("game_id");
 		queryParams = queryParams + "game_id="+game_id.value+"&";
 		
 		var stat_day_begin = $("#stat_day_begin").datebox('getValue');
 		queryParams = queryParams + "stat_day_begin="+stat_day_begin+"&"; 
 		var stat_day_end = $("#stat_day_end").datebox('getValue');
 		queryParams = queryParams + "stat_day_end="+stat_day_end+"&";
 		queryParams = queryParams  + "1";
 		
 		$('#sdk_operation_analyze_List').datagrid(
 			{url:'gameOperationController.do?datagrid&' + queryParams}
 		);
 	}
	
	//导出
	function exportXls() {
		var submitUrl = "gameOperationController.do?exportXls";
		 		var queryParams ="";
 		var game_id = document.getElementById("game_id");
 		queryParams = queryParams + "game_id="+game_id.value+"&";
 		
 		var stat_day_begin = $("#stat_day_begin").datebox('getValue');
 		queryParams = queryParams + "stat_day_begin="+stat_day_begin+"&"; 
 		var stat_day_end = $("#stat_day_end").datebox('getValue');
 		queryParams = queryParams + "stat_day_end="+stat_day_end+"&";
 		queryParams = queryParams  + "1";
		submitUrl+="&"+queryParams;
		alert(submitUrl);
		submitUrl = encodeURI(submitUrl);
		 window.location.href=submitUrl;
	}
</script>
<table width="100%"   id="sdk_operation_analyze_List" toolbar="#opttt"></table>
<div id="opttt" style="padding:3px; height: auto">
	<div name="searchColums">
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">游戏名称：</span>
		<select id="game_id" name = "game_id" WIDTH="100" style="width: 104px">
		<option value = "">---请选择---</option>
				<#list game as xd>
					<option value = "${xd['game_id']}">${xd['game_name']}</option>
				</#list>
		</select>
		</span>
		
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">日期：</span>
		<input type="text" id="stat_day_begin" name="stat_day_begin"  style="width: 94px"  class="easyui-datebox" />
		<span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
		<input type="text" id="stat_day_end" name="stat_day_end"  style="width: 94px" class="easyui-datebox" />
		</span>
	</div>
	<div style="height:30px;" class="datagrid-toolbar">
		<span style="float:left;" >
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="exportXls();">导出excel</a>
		</span>
		<span style="float:right">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="sdk_operation_analyze_Listsearch()">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="">重置</a>
		</span>
	</div>
</div>
