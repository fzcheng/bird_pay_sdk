<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>计费方式编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="payment.do?save">
    <input id="id" name="id" type="hidden" value="${payment.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right"><label class="Validform_label"> 计费方式中文名: </label></td>
        <td class="value"><input class="inputxt" name="payCnName" id="payCnName" value="${payment.payCnName}" datatype="s2-10" errormsg="计费方式中文名为2~10字符">
          <span class="Validform_checktip">计费方式中文名为2~10字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费方式名: </label></td>
        <td class="value">
          <input class="inputxt" name="payName" id="payName" value="${payment.payName}" ajaxurl="payment.do?valid&id=${payment.id}"
            datatype="/^[0-9a-zA-Z]{2,20}$/" errormsg="计费方式名只能包含字符、数字，2~20位">
          <span class="Validform_checktip">计费方式名只能包含字符、数字，2~20位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计费类型: </label></td>
        <td class="value">
          <input class="inputxt" name="payType" id="payType" value="${payment.payType}" datatype="n1-2"
            errormsg="请填写数字,1~2位" ajaxurl="payment.do?valid&id=${payment.id}">
          <span class="Validform_checktip">请填写数字,1~2位</span></td>
      </tr>
    </table>
  </t:formvalid>
</body>