<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ap.do?save">
    <input id="id" name="id" type="hidden" value="${ap.id}">
    <input id="apid" name="apid" type="hidden" value="${apid}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right"><label class="Validform_label"> 开发者名称: </label></td>
        <td class="value"><input class="inputxt" name="apName" id="apName" value="${ap.apName}" datatype="s" ajaxurl="ap.do?valid&id=${ap.id}">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录账号: </label></td>
        <td class="value"><input class="inputxt" name="loginEmail" id="loginEmail" value="${ap.loginEmail}" datatype="/^[a-zA-Z]\w{5,17}$/"
          errormsg="以字母开头,只能包含字符、数字和下划线，6~18位" ajaxurl="ap.do?valid&id=${ap.id}"> <span class="Validform_checktip">以字母开头,只能包含字符、数字和下划线，6~18位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="loginPwd" id="loginPwd" value="${ap.loginPwd}" datatype="s6-32"> <span
          class="Validform_checktip">密码至少6个字符,最多32个字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 重复密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="reLoginPwd" id="reLoginPwd" value="${ap.loginPwd}" datatype="s6-32" recheck="loginPwd"> <span
          class="Validform_checktip">重复登录密码</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 联系人: </label></td>
        <td class="value"><input class="inputxt" name="contactsName" id="contactsName" value="${ap.contactsName}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 联系方式: </label></td>
        <td class="value"><input class="inputxt" name="contactsPhone" id="contactsPhone" value="${ap.contactsPhone}" datatype="*"> <span class="Validform_checktip"></span></td>
      </tr>

    </table>
  </t:formvalid>
</body>