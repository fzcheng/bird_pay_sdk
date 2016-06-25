<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="cnlList" title="渠道列表"  actionUrl="cnl.do?data" fit="true" idField="id" >
   <t:dgCol title="编号" field="id"  width="5" ></t:dgCol>
   <t:dgCol title="渠道商" field="pid"  width="10" replace="${replace}"></t:dgCol>
   <t:dgCol title="渠道名称" field="channelName"  width="10"></t:dgCol>
   <t:dgCol title="渠道编号" field="channelNum"  width="10"></t:dgCol>
   <t:dgCol title="备注"  field="memo"  width="10"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="cnl.do?del&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？" ></t:dgConfOpt>  
   	<t:dgToolBar title="添加" icon="icon-add" funname="add" url="cnl.do?edit" ></t:dgToolBar>
   	<t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="cnl.do?edit" ></t:dgToolBar>
    <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="cnl.do?edit"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>