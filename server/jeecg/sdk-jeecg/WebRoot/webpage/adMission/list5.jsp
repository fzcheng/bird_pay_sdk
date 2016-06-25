<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

function openAdMissionSubList(missionId){
	addOneTab('广告子任务列表','adMissionSub.do?list&missionId='+missionId);
}

</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="adMission5List" title="广告任务管理列表" actionUrl="adMission.do?datagrid&showType=${showType}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="missionId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="任务策略Id" field="missionStrategyId"  width="10"></t:dgCol>
   <t:dgCol title="计划Id" field="adId"  width="10"></t:dgCol>
   <t:dgCol title="显示类型" field="showType"  width="10"></t:dgCol>
    <t:dgToolBar title="增加" icon="icon-add" funname="add" url="adMission.do?addorupdate&showType=${showType}"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="adMission.do?addorupdate&showType=${showType}"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>