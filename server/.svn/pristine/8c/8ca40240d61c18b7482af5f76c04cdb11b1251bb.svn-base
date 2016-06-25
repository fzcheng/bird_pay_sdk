<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <style type="text/css">
 .longInput{
 	width:350px;
 }
 </style>
 <script type="text/javascript">
 </script>
  <t:formvalid formid="formobj" layout="div" dialog="true" action="outChannel.do?saveOutChannel">
   <script type="text/javascript">
  </script>
			<input id="id" name="id" type="hidden" value="${outChannel.id}">
			<input id="createTime" name="createTime" type="hidden" value="${outChannel.createTime}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							外部渠道名称:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="longInput" datatype="*" name="name" id="name"  value="${outChannel.name}" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							游戏名称:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="longInput" datatype="*" name="gameName" id="gameName"  value="${outChannel.gameName}" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				 
			</table>
		</t:formvalid>
 </body>