<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>渠道编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="cnl.do?save">
    <input id="id" name="id" type="hidden" value="${channel.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" width="15%"><label class="Validform_label"> 渠道商: </label></td>
        <td class="value" width="85%">
          <select id="pid"  name="pid" class="inputxt" datatype="*">
            <option value="">请选择</option>
           <c:forEach items="${partners}" var="partner">
            <option value="${partner.id}" <c:if test="${channel.pid==partner.id}">selected="selected"</c:if>>
             ${partner.partnerName}
            </option>
           </c:forEach>
          </select>
          <span class="Validform_checktip"></span>
        </td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 渠道名称: </label></td>
        <td class="value"><input class="inputxt" name="channelName" id="channelName" value="${channel.channelName}" datatype="s2-15" errormsg="渠道名称为2~10字符">
        <span class="Validform_checktip">渠道名称为2~15字符</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 渠道编号: </label></td>
        <td class="value"><input class="inputxt" name="channelNum" id="channelNum" value="${channel.channelNum}" ajaxurl="cnl.do?validChannelNum&id=${channel.id}" datatype="/^[0-9a-zA-Z]{2,20}$/" errormsg="渠道编号只能包含字符、数字，2~20位">
        <span class="Validform_checktip">渠道编号只能包含字符、数字，2~20位</span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 备注: </label></td>
        <td class="value"><input class="inputxt" name="memo" id="memo" value="${channel.memo}" datatype="s2-15" errormsg="备注为2~30字符">
        <span class="Validform_checktip">备注为2~30字符</span></td>
      </tr>
    </table>
  </t:formvalid>
</body>