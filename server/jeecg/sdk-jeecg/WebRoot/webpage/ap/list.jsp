<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

function openAppList(apid){
	addOneTab('应用信息管理列表','app.do?list&apid='+apid);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="apList" title="开发者管理列表" actionUrl="ap.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="开发者id" field="apid"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="开发者名称" field="apName"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="联系人" field="contactsName"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="联系电话" field="contactsPhone"  width="10"  query="true"></t:dgCol>
   <t:dgFunOpt title="管理开发者应用"  funname="openAppList(apid)" ></t:dgFunOpt> 
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="ap.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="ap.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>