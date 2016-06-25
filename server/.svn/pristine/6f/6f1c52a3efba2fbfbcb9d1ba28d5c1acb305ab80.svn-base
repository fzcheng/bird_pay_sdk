<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>屏蔽设置</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="optpaypassage.do?save" beforeSubmit="onCheckOptPassageParams">
    <input id="id" name="id" type="hidden" value="${id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" rowspan="2"><label class="Validform_label"> 屏蔽省份: </label></td>
        <td class="value"><input id="allOperatorFlag" name="allOperatorFlag" value="1" type="checkbox" /> <label for="allOperatorFlag">全网</label></td>
      </tr>
      <tr>
        <td class="value">
          <input class="easyui-combobox" name="language" 
            data-options="
              url:'../combobox/combobox_data1.json',
              valueField:'id',
              textField:'text',
              multiple:true,
              panelHeight:'auto'">
        </td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 屏蔽号段: </label></td>
        <td class="value"></td>
      </tr>
    </table>
  </t:formvalid>
</body>