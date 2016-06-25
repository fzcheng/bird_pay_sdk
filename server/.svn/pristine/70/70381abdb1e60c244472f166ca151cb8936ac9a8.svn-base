<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>渠道编辑</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="gameChannel.do?save">
    <input id="id" name="id" type="hidden" value="${gameChannel.id}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
      <tr>
        <td align="right" width="15%"><label class="Validform_label"> 游戏: </label></td>
        <td class="value" width="85%">
          <select id="gameId"  name="gameId" class="inputxt" datatype="*">
            <option value="">请选择</option>
           <c:forEach items="${games}" var="game">
            <option value="${game.gameId}" <c:if test="${game.gameId==gameChannel.gameId}">selected="selected"</c:if>>
             ${game.name}
            </option>
           </c:forEach>
          </select>
          <span class="Validform_checktip"></span>
        </td>
      </tr>
      <tr>
        <td align="right" width="15%"><label class="Validform_label"> 渠道: </label></td>
        <td class="value" width="85%">
          <select id="channelId"  name="channelId" class="inputxt" datatype="*">
            <option value="">请选择</option>
           <c:forEach items="${channels}" var="channel">
            <option value="${channel.id}" <c:if test="${channel.id==gameChannel.channelId}">selected="selected"</c:if>>
             ${channel.channelName}
            </option>
           </c:forEach>
          </select>
          <span class="Validform_checktip"></span>
        </td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 渠道支付扣量: </label></td>
        <!-- <td class="value"><input class="inputxt" name="payDeductPctStr" id="payDeductPctStr" value="${gameChannel.payDeductPctStr}" datatype="/^(100|\d{1}|[1-9]\d{1})(\.\d(\d)?)?%$/" errormsg="扣量为百分数，范围0.00%~100.00%">
        <span class="Validform_checktip">扣量为百分数，范围0.00%~100.00%</span></td>-->
        <td class="value"><input class="inputxt" name="payDeductPctStr" id="payDeductPctStr" value="${gameChannel.payDeductPctStr}" errormsg="扣量为百分数，范围0.00%~100.00%">
        </td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 渠道注册扣量: </label></td>
        <td class="value"><input class="inputxt" name="regDeductPctStr" id="regDeductPctStr" value="${gameChannel.regDeductPctStr}" datatype="/^(100|\d{1}|[1-9]\d{1})(\.\d(\d)?)?%$/" errormsg="扣量为百分数，范围0.00%~100.00%">
        <span class="Validform_checktip">扣量为百分数，范围0.00%~100.00%</span></td>
      </tr>
    </table>
  </t:formvalid>
</body>