<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
    <t:datagrid name="gameChannelList" title="游戏投放渠道列表" actionUrl="gameChannel.do?data" fit="true" idField="id" queryMode="group">
      <t:dgCol title="编号" field="id" width="5"></t:dgCol>
      <t:dgCol title="游戏名" field="gameId" width="10" replace="${gamesReplace}" query="true"></t:dgCol>
      <t:dgCol title="渠道名" field="channelId" width="10" replace="${channelsReplace}"></t:dgCol>
      <t:dgCol title="渠道支付扣量" field="payDeductPctStr" width="10"></t:dgCol>
      <t:dgCol title="渠道注册扣量" field="regDeductPctStr" width="10"></t:dgCol>
      <t:dgCol title="操作" field="opt"></t:dgCol>
      <t:dgConfOpt title="删除" url="gameChannel.do?del&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？"></t:dgConfOpt>
      <t:dgToolBar title="添加" icon="icon-add" funname="add" url="gameChannel.do?edit"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="gameChannel.do?edit"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="gameChannel.do?edit"></t:dgToolBar>
    </t:datagrid>
  </div>
</div>