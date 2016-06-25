<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);
function openGameRemain(id){
	addOneTab('新用户留存','exCgReportController.do?list&id=game_remain&_q_game_id='+id);
}
function openGameCore(id){
	addOneTab('核心数据','exCgReportController.do?list&id=hh&_q_game_id='+id);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="preSdkGameList" title="方案设置列表" actionUrl="preSdkGame.do?datagrid"  idField="gameId"  fit="true" >
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="gameId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="name"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="packageName"  width="10" ></t:dgCol>
    <t:dgCol title="所属CP" field="sdkGameCp_name"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgFunOpt title="核心数据"  funname="openGameCore(id)" ></t:dgFunOpt>  
     <t:dgFunOpt title="新用户留存"  funname="openGameRemain(id)" ></t:dgFunOpt>  
   <t:dgToolBar title="查看" icon="icon-search" funname="" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>