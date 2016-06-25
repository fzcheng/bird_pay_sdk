<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gamePlanSetList" title="方案设置列表" actionUrl="gamePlanSet.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="gameName"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="gamePackageName"  width="10" ></t:dgCol>
    <t:dgCol title="所属CP" field="cpName"  width="10"></t:dgCol>
    <t:dgCol title="使用方案" field="usedPlan"  width="10" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>     
   <t:dgToolBar title="设置" icon="icon-edit" funname="update" url="gamePlanSet.do?setPlan"></t:dgToolBar>

  </t:datagrid>
 </div>
</div>