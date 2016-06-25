<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelPartner.do?save">
    <input id="id" name="id" type="hidden" value="${id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" width="15%"><label class="Validform_label"> 渠道商名称: </label></td>
        <td class="value" width="85%"><input class="inputxt" name="partnerName" id="partnerName" value="${tmp.partnerName}" datatype="s2-20">
        <span class="Validform_checktip">渠道商名称范围在2~20位字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录账号: </label></td>
        <td class="value"><input class="inputxt" name="loginName" id="loginName" value="${tmp.loginName}" 
            datatype="/^[a-zA-Z]\w{5,17}$/" errormsg="以字母开头,只能包含字符、数字和下划线，6~18位" ajaxurl="channelPartner.do?valid&id=${id}">
        <span class="Validform_checktip">以字母开头,只能包含字符、数字和下划线，6~18位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 登录密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="loginPasswd" id="loginPasswd" value="${tmp.loginPasswd}" datatype="s6-32">
        <span class="Validform_checktip">密码至少6个字符,最多32个字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 重复密码: </label></td>
        <td class="value"><input type="password" class="inputxt" name="reLoginPasswd" id="reLoginPasswd" value="${tmp.loginPasswd}" datatype="s6-32" recheck="loginPasswd">
        <span class="Validform_checktip">重复登录密码</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 请选择渠道: </label></td>
        <td class="value"><select id="channelIds" name="channelIds" multiple="multiple" size=5>
            <c:forEach items="${sdkChannels}" var="sdkChannel">
              <option value="${sdkChannel.id }" <c:if test="${sdkChannel.pid!=0}">selected</c:if>>${sdkChannel.channelName}</option>
            </c:forEach></td>
      </tr>
    </table>
  </t:formvalid>
</body>