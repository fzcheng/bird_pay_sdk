<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
    <t:datagrid name="channelPartnerList" title="渠道商列表" actionUrl="channelPartner.do?data" fit="true" idField="id">
      <t:dgCol title="编号" field="id" width="5"></t:dgCol>
      <t:dgCol title="渠道商名称" field="partnerName" width="10" query="true"></t:dgCol>
      <t:dgCol title="登录账号" field="loginName" width="10"></t:dgCol>
      <t:dgCol title="修改时间" field="updateTime" width="10"></t:dgCol>
      <t:dgCol title="创建时间" field="createTime" width="10"></t:dgCol>
      <t:dgCol title="操作" field="opt"></t:dgCol>
      <t:dgConfOpt title="删除" url="channelPartner.do?del&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？"></t:dgConfOpt>
      <t:dgToolBar title="添加" icon="icon-add" funname="add" url="channelPartner.do?add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="channelPartner.do?update"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="channelPartner.do?detail"></t:dgToolBar>
    </t:datagrid>
  </div>
</div>