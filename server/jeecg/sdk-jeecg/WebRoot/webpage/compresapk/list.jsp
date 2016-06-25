<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="makeApkData" title="打包记录"  actionUrl="makeApk.do?datagrid"  idField="id" fit="true">
    
   <t:dgToolBar title="新建打包" icon="icon-add" funname="add" url="makeApk.do?first"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>