<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>

<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="adMission.do?save">
    <input id="id" name="id" type="hidden" value="${adMission.id}">
    <input id="adverId" name="adverId" type="hidden" value="${adMission.adId}">
    <input id="showType" name="showType" type="hidden" value="${adMission.showType}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
       	<tr>
			<td align="right">
				<label class="Validform_label">
				任务开始时间:
				</label>
			</td>
			<td class="value">
			<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="beginTime" id="beginTime"  
			value="<fmt:formatDate value='${adMission.beginTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	    			<span class="Validform_checktip"></span>
			</td>
		</tr>
      	<tr>
			<td align="right">
				<label class="Validform_label">
				任务截止时间:
				</label>
			</td>
			<td class="value">
			<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="endTime" id="endTime"  
			value="<fmt:formatDate value='${adMission.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	    			<span class="Validform_checktip"></span>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				显示类型:
				</label>
			</td>
			<td class="value" style="width: 400px" disabled="disabled">
			<input class="longInput" value="开屏广告" datatype="s1-64"   disabled="disabled">
			</td>
		</tr>
    </table>
  </t:formvalid>
</body>