<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGamePayrepeat" title="游戏使用支付通道情况" actionUrl="sdkGamePayrepeat.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" query="true" width="10"></t:dgCol>
   <t:dgCol title="是否使用" field="usestate" replace="否_0,是_1" query="true" width="10"></t:dgCol>
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
   <t:dgCol title="更改时间" field="updatedTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGamePayrepeat.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGamePayrepeat.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGamePayrepeat.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>