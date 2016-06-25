<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
function setPlan(id){
	addOneTab('方案编辑','sdkGamePlan.do?list&planId='+id);
}
function hideAdd(){
	$("a[icon=icon-add]").css("display","none");
}

var tmp;
function checkadd(data){
 if(data.rows.length>0){
 	hideAdd();
 }
 
	//if(count>0){
	//	hideAdd();
	//}
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkBulletinList" title="方案列表" onLoadSuccess="checkadd" actionUrl="sdkBulletin.do?datagrid"  idField="bulletinId"  fit="true">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="bulletinId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="公告内容" field="detail"  width="10" query="true"></t:dgCol>
   <t:dgCol title="公告类型" field="type"  width="10" replace="无反应_0,网页链接_1,游戏下载_2"></t:dgCol>
   <t:dgCol title="状态" field="status"  width="5" replace="已停止_0,已启动_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="15"></t:dgCol>  
   <t:dgConfOpt title="启动" url="sdkBulletin.do?start&id={id}" message="你确定要启动吗？" ></t:dgConfOpt>  
   <t:dgConfOpt title="停止" url="sdkBulletin.do?stop&id={id}" message="你确定要停止吗？"></t:dgConfOpt> 
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="sdkBulletin.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkBulletin.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>