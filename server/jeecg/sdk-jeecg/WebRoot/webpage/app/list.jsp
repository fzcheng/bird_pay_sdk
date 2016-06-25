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
  <t:datagrid name="appList" title="应用信息管理列表" actionUrl="app.do?datagrid&appid=${appid}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="应用id" field="appid"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="应用名" field="appName"  width="10"></t:dgCol>
    <t:dgCol title="应用key" field="appKey"  width="10"></t:dgCol>
    <t:dgCol title="应用描述" field="appDetail"  width="10"></t:dgCol>
    <t:dgCol title="应用包下载地址" field="packageUrl"  width="10"></t:dgCol>
   <t:dgCol title="应用大小" field="size"  width="10"></t:dgCol>
   <t:dgCol title="icon图标" field="icon"  width="10"></t:dgCol>
   <t:dgCol title="简介图片1" field="img1"  width="10"></t:dgCol>
   <t:dgCol title="简介图片2" field="img2"  width="10"></t:dgCol>
   <t:dgCol title="简介图片3" field="img3"  width="10"></t:dgCol>
   <t:dgCol title="简介图片4" field="img4"  width="10"></t:dgCol>
   <t:dgCol title="简介图片5" field="img5"  width="10"></t:dgCol>
   <t:dgCol title="版本名称" field="version"  width="10"></t:dgCol>
   <t:dgCol title="比率(%)" field="rate"  width="10"></t:dgCol>
   <t:dgCol title="比率单位" field="rateUnit"  width="10"></t:dgCol>
   <t:dgCol title="版本更新code" field="versionCode"  width="10"></t:dgCol>
   <t:dgCol title="包名" field="packageName"  width="10"></t:dgCol>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="app.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="app.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>