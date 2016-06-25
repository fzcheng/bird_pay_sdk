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
  <t:datagrid name="adList" title="广告信息管理列表" actionUrl="push.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="推送id" field="pushid"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="类型" field="type"  width="10"></t:dgCol>
   <t:dgCol title="标题" field="title"  width="10"></t:dgCol>
    <t:dgCol title="内容" field="content"  width="10"></t:dgCol>
   <t:dgToolBar title="Android发布" icon="icon-add" funname="add" url="push.do?addorupdate&type=0"></t:dgToolBar>
   <t:dgToolBar title="IOS发布" icon="icon-edit" funname="add" url="push.do?addorupdate&type=1"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>