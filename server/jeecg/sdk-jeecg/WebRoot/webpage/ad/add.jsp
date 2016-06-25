<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ad.do?save">
    <input id="id" name="id" type="hidden" value="${ad.id}">
    <input id="adid" name="adid" type="hidden" value="${adid}">
    <input id="adverId" name="adverId" type="hidden" value="${adverId}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
        <td align="right"><label class="Validform_label"> 计划名称: </label></td>
        <td class="value"><input class="inputxt" name="adname" id="adname" value="${ad.adname}" datatype="s">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
		<td align="right">
			<label class="Validform_label">
			计划开始时间:
			</label>
		</td>
		<td class="value">
		<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="beginTime" id="beginTime"  
		value="<fmt:formatDate value='${beginTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
    			<span class="Validform_checktip"></span>
			 
		</td>
	</tr>
      <tr>
		<td align="right">
			<label class="Validform_label">
			计划截止时间:
			</label>
		</td>
		<td class="value">
		<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="endTime" id="endTime"  
		value="<fmt:formatDate value='${endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
    			<span class="Validform_checktip"></span>
			 
		</td>
	<tr>
        <td align="right"><label class="Validform_label"> 投放资金: </label></td>
        <td class="value"><input class="inputxt" name="fund" id="fund" value="${ad.fund}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 计划描述: </label></td>
        <td class="value"><input class="inputxt" name="detail" id="detail" value="${ad.detail}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
    </table>
  </t:formvalid>
</body>