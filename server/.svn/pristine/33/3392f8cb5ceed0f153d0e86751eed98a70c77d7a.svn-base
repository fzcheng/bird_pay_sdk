<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="adMissionSubList" title="广告子任务管理列表" actionUrl="adMissionSub.do?datagrid&missionId=${missionId}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="missionId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="子任务Id" field="missionSubId"  width="10"></t:dgCol>
   <t:dgCol title="排序" field="no"  width="10"></t:dgCol>
   <t:dgCol title="任务奖励" field="birdMoney"  width="10"></t:dgCol>
   <t:dgCol title="任务描述" field="missionDetail"  width="10"></t:dgCol>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="adMissionSub.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="adMissionSub.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>