<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gamePushList" title="Push管理列表" actionUrl="pushMsg.do?datagrid2"  idField="gameId"  fit="true" >
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="gameId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="name"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="packageName"  width="10" ></t:dgCol>
    <t:dgCol title="所属CP" field="sdkGameCp_name"  width="10"></t:dgCol>     
   <t:dgToolBar title="发送消息" icon="icon-edit" funname="update" url="pushMsg.do?sendMsg"></t:dgToolBar>

  </t:datagrid>
 </div>
</div>