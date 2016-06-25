<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="gameCmp.do?save">
    <input id="cpId" name="cpId" type="hidden" value="${gameCmp.cpId }">
    <input id="apiKey" name="apiKey" type="hidden" value="${apiKey}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right"><label class="Validform_label"> 游戏商名称: </label></td>
        <td class="value"><input class="inputxt" name="name" id="name" value="${gameCmp.name}" datatype="s" ajaxurl="gameCmp.do?valid&id=${gameCmp.cpId}">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录账号: </label></td>
        <td class="value"><input class="inputxt" name="loginName" id="loginName" value="${gameCmp.loginName}" datatype="/^[a-zA-Z]\w{5,17}$/"
          errormsg="以字母开头,只能包含字符、数字和下划线，6~18位" ajaxurl="gameCmp.do?valid&id=${gameCmp.cpId}"> <span class="Validform_checktip">以字母开头,只能包含字符、数字和下划线，6~18位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="pwd" id="pwd" value="${gameCmp.pwd}" datatype="s6-32"> <span
          class="Validform_checktip">密码至少6个字符,最多32个字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 重复密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="rePwd" id="rePwd" value="${gameCmp.pwd}" datatype="s6-32" recheck="pwd"> <span
          class="Validform_checktip">重复登录密码</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 联系方式: </label></td>
        <td class="value"><input class="inputxt" name="email" id="email" value="${gameCmp.email}" datatype="*"> <span class="Validform_checktip"></span></td>
      </tr>

    </table>
  </t:formvalid>
</body>