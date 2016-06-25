<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="sdkInformation.do?save">
  			<input id="infoId" name="infoId" type="hidden" value="${sdkInformation.infoId}">
  			<input id="gameId" name="gameId" type="hidden" value="${gameId}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
						资讯标题:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="title" id="title"  value="${sdkInformation.title}" datatype="*" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
								<tr>
					<td align="right">
						<label class="Validform_label">
						资讯类型:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="type" id="type"  value="${sdkInformation.type}" datatype="*" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
						简介:
						</label>
					</td>
					<td class="value">
					<textarea rows="10" cols="80"  name="detail" id="detail"   datatype="*" >${sdkInformation.detail}</textarea>
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
	<tr>
					<td align="right">
						<label class="Validform_label">
						资讯链接:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="weburl" id="weburl"  datatype="url" value="${sdkInformation.weburl}" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>