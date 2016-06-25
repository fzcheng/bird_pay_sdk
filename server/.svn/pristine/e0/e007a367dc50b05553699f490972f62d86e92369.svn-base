<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>运营商计费方式编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="operatorpayment.do?save">
    <input id="id" name="id" type="hidden" value="${payment.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right"><label class="Validform_label"> 计费方式名称: </label></td>
        <td class="value"><input class="inputxt" name="name" id="name" value="${payment.name}" datatype="s2-10" errormsg="计费方式名为2~10字符">
          <span class="Validform_checktip">计费方式中文名为2~10字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费类型: </label></td>
        <td class="value">
          <input class="inputxt" name="type" id="type" value="${payment.type}" datatype="n1-2"
            errormsg="请填写数字,1~2位" ajaxurl="operatorpayment.do?valid&id=${payment.id}">
          <span class="Validform_checktip">请填写数字,1~2位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 每日限额(元): </label></td>
        <td class="value">
          <input class="inputxt" name="dayLimit" id="dayLimit" value="${payment.dayLimit}" datatype="/^[1-9]d*.d*|0.d*[1-9]d*$/" errormsg="请输入数字">
          <span class="Validform_checktip">请输入数字</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 每月限额(元): </label></td>
        <td class="value">
          <input class="inputxt" name="monthLimit" id="monthLimit" value="${payment.monthLimit}" datatype="/^[1-9]d*.d*|0.d*[1-9]d*$/" errormsg="请输入数字">
          <span class="Validform_checktip">请输入数字</span></td>
      </tr>
    </table>
  </t:formvalid>
</body>