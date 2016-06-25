<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameShenzhoufu" title="神州付信息表" actionUrl="sdkGameShenzhoufu.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5"></t:dgCol>  
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="8" ></t:dgCol>
   <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" width="8" query="true"></t:dgCol>
   <t:dgCol title="商户在神州付的唯一身份标识" field="merId" width="8"></t:dgCol>
   <t:dgCol title="商户密钥" field="privateKey" width="13"></t:dgCol>
   
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGameShenzhoufu.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGameShenzhoufu.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGameShenzhoufu.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>