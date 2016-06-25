<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
    <t:datagrid name="operatorPaymentDatagrid" title="运营商计费方式列表" actionUrl="operatorpayment.do?datagrid" idField="id" fit="true">
      <t:dgCol title="ID" field="id" width="5"></t:dgCol>
      <t:dgCol title="计费方式名称" field="name" width="10"></t:dgCol>
      <t:dgCol title="支付类型" field="type" width="10"></t:dgCol>
      <t:dgCol title="每日限额(元)" field="dayLimit" width="10"></t:dgCol>
      <t:dgCol title="每月限额(元)" field="monthLimit" width="10"></t:dgCol>
      <t:dgCol title="操作" field="opt"></t:dgCol>
      <t:dgDelOpt url="operatorpayment.do?del&id={id}" title="删除"></t:dgDelOpt>
      <t:dgToolBar title="增加" icon="icon-add" funname="add" url="operatorpayment.do?edit"></t:dgToolBar>
      <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="operatorpayment.do?edit"></t:dgToolBar>
    </t:datagrid>
  </div>
</div>