<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="outChannelList" title="外部渠道商列表"  actionUrl="outChannel.do?getOneList" fit="true" idField="id" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" ></t:dgCol>
   <t:dgCol title="渠道商名称" field="name"  width="10"></t:dgCol>
   <t:dgCol title="游戏名称"  field="gameName"  width="10" query="true"></t:dgCol>   
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="outChannel.do?delOutChannel&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？" ></t:dgConfOpt>  
   	<t:dgToolBar title="添加" icon="icon-add" funname="add" url="outChannel.do?addorupdateOutChannel" ></t:dgToolBar>
   	<t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="outChannel.do?addorupdateOutChannel" ></t:dgToolBar>
    <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="outChannel.do?addorupdateOutChannel"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>