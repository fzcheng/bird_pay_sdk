<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("input[name='startTime_begin']").attr("class","easyui-datetimebox");
$("input[name='startTime_end']").attr("class","easyui-datetimebox");

}
);

$("input[name='startTime_begin']").datetimebox({
	onSelect: function(date){
		//alert(date);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		
	}
});

$("input[name='startTime_end']").datetimebox({
	onSelect: function(date){
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});

function setPlan(id){
	addOneTab('方案编辑','sdkGamePlan.do?list&planId='+id);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkNewServerList" title="游戏新服管理列表" actionUrl="sdkNewServer.do?datagrid"  idField="nsId"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="nsId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="gameName"  width="10" query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="gamePkg"  width="10"></t:dgCol>
   <t:dgCol title="开服类型" field="type"  width="10"></t:dgCol>
   <t:dgCol title="开服时间" field="startTime"  width="10" formatter="yyyy-MM-dd hh:mm:ss" query="true"  queryMode="group"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>  
   <t:dgDelOpt url="sdkNewServer.do?del&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="sdkNewServer.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>