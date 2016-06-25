<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameDomainForCp" title="动态域名名单列表(CP)" actionUrl="sdkGameDomainForCp.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="游戏id" field="gameId" replace="${gameReplace}" width="5" query="true"></t:dgCol>
   <t:dgCol title="域名" field="domain" width="10" query="true"></t:dgCol>
   <t:dgCol title="状态" field="status" replace="不可用_0,可用_1" width="10" query="true"></t:dgCol>   
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGameDomainForCp.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGameDomainForCp.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGameDomainForCp.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>