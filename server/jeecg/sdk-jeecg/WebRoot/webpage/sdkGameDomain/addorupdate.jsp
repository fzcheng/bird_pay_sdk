<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>动态域名添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkGameDomain.do?save">
		<input id="id" name="id" type="hidden" value="${sdkGameDomain.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			<tr>
				<td align="center"><label class="Validform_label"> 域名: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="domain" id="domain" value="${sdkGameDomain.domain}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
					<td align="center">
						<label class="Validform_label">
						状态
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="status" checked="checked"  <c:if test="${sdkGameDomain.status==1}" >checked</c:if> >可用 &nbsp;
					<input type="radio"  value="0" name="status" <c:if test="${sdkGameDomain.status==0}" >checked</c:if>>不可用 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
		</table>
	</t:formvalid>
</body>