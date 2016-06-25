<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGamePlanList" title="游戏方案列表" actionUrl="sdkGamePlan.do?datagrid&planId=${planId}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="gameName"  width="10" query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="gamePkg"  width="10"></t:dgCol>
    <t:dgCol title="顺序号" field="idx"  width="10"></t:dgCol>
    <t:dgCol title="所属于方案" field="sdkPlan_name"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>     
   <t:dgDelOpt url="sdkGamePlan.do?del&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="sdkGamePlan.do?addorupdate&planId=${planId}"></t:dgToolBar>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="sdkGamePlan.do?addorupdate&planId=${planId}"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>