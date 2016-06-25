<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
    <t:datagrid name="paymentDatagrid" title="计费方式列表" actionUrl="payment.do?datagrid" idField="id" fit="true">
      <t:dgCol title="ID" field="id" width="5"></t:dgCol>
      <t:dgCol title="计费方式中文名" field="payCnName" width="10"></t:dgCol>
      <t:dgCol title="计费方式名" field="payName" width="10"></t:dgCol>
      <t:dgCol title="计费类型" field="payType" width="10"></t:dgCol>
      <t:dgCol title="操作" field="opt"></t:dgCol>
      <t:dgDelOpt url="payment.do?del&id={id}" title="删除"></t:dgDelOpt>
      <t:dgToolBar title="增加" icon="icon-add" funname="add" url="payment.do?edit"></t:dgToolBar>
      <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="payment.do?edit"></t:dgToolBar>
    </t:datagrid>
  </div>
</div>