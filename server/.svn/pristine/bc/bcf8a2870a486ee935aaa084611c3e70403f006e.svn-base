<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
function setPlan(id){
	addOneTab('方案编辑','sdkGamePlan.do?list&planId='+id);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkPlanList" title="方案列表" actionUrl="sdkPlan.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="方案名称" field="name"  width="10" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>  
   <t:dgFunOpt title="设置"  funname="setPlan(id)" ></t:dgFunOpt>    
   <t:dgDelOpt url="sdkPlan.do?del&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="sdkPlan.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>