<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="sdkNewServer.do?save">
  			<input id="nsId" name="nsId" type="hidden" value="${sdkNewServer.nsId}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
						游戏包名:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="gamePkg" id="gamePkg"  value="${sdkNewServer.gamePkg}" datatype="s1-256" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
						开服时间:
						</label>
					</td>
					<td class="value">
					<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="startDate" id="startDate"  
					value="<fmt:formatDate value='${sdkNewServer.startTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
						开服类型:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="type" id="type"  value="${sdkNewServer.type}" datatype="s1-16" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>		 
			</table>
		</t:formvalid>
 </body>