<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

function openAdList(adverId){
	addOneTab('广告信息管理列表','ad.do?list&adverId='+adverId);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="advertisersList" title="广告主管理列表" actionUrl="advertisers.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="广告主id" field="adverId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="广告主名称" field="name"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="联系人" field="contactsName"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="联系电话" field="contactsPhone"  width="10"  query="true"></t:dgCol>
   <t:dgFunOpt title="管理广告计划"  funname="openAdList(adverId)" ></t:dgFunOpt> 
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="advertisers.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="advertisers.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="advertisers.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>