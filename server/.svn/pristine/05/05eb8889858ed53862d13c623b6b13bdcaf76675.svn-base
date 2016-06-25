<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gamePushData" title="游戏商列表" actionUrl="pushMsg.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" query="true"></t:dgCol>
   <t:dgCol title="游戏" field="gameInfo_name"  width="5"></t:dgCol>
   <t:dgCol title="消息类型" field="contentType"  width="10"></t:dgCol>
   <t:dgCol title="标题" field="title"  width="10"></t:dgCol>
    <t:dgCol title="消息内容" field="message"  width="10"  ></t:dgCol>
    <t:dgCol title="消息ID"  field="msgId"  width="10"></t:dgCol>
   <t:dgCol title="消息时间" field="msgTime"  formatter="yyyy-MM-dd hh:mm:ss" width="15"></t:dgCol>
    <t:dgCol title="URL地址"  field="url"  width="10"></t:dgCol>   
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="pushMsg.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="pushMsg.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>