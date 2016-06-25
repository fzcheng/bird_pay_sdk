<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkPropsSetting" title="道具设置列表" actionUrl="sdkPropsSetting.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>   
   <t:dgCol title="支付渠道" field="operatorPayChannelId" replace="${channelReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>  
   <t:dgCol title="道具名称" field="propsName" width="10" query="true"></t:dgCol>
   <t:dgCol title="道具别名" field="propsAlias" width="10" query="true"></t:dgCol>
   <t:dgCol title="金额(元)" field="amount" width="7" query="true"></t:dgCol>
   <t:dgCol title="使用状态" field="useStatus" replace="使用_1,停用_0" width="5" query="true"></t:dgCol>
   <t:dgCol title="道具描述" field="propsDesc" width="20" query="true"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkPropsSetting.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkPropsSetting.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkPropsSetting.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>