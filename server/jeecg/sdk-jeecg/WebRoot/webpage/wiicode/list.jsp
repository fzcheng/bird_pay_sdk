<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="wiiCodeDataList" title="计费列表" actionUrl="wiicode.do?datagrid&gameId=${gameId}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="所属游戏" field="sdkGame_name"  width="5"></t:dgCol>
   <t:dgCol title="计费项名" field="name"  width="10" query="true"></t:dgCol>
   <t:dgCol title="计费项编号" field="payCode"  width="10"></t:dgCol>
    <t:dgCol title="价格" field="price"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>     
   <t:dgDelOpt url="wiicode.do?del&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="wiicode.do?addorupdate&gameId=${gameId}"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="wiicode.do?addorupdate&gameId=${gameId}"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>