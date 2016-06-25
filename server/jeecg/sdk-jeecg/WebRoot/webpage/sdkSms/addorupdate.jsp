<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>企信通添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkSms.do?save">
		<input id="id" name="id" type="hidden" value="${sdkSms.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			
			<tr>
					<td align="center">
						<label class="Validform_label">
						手机运营商类型
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="operationType" <c:if test="${sdkSms.operationType==1}" >checked</c:if> >中国移动 &nbsp;
					<input type="radio"  value="2" name="operationType" <c:if test="${sdkSms.operationType==2}" >checked</c:if>>中国联通 &nbsp;
					<input type="radio"  value="3" name="operationType" <c:if test="${sdkSms.operationType==3}" >checked</c:if> >中国电信 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label"> 上行端口: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="upPort" id="upPort" value="${sdkSms.upPort}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 是否使用
				</label></td>
				<td class="value">
					<input type="radio"  value="1" name="useState" <c:if test="${sdkSms.useState==1}" >checked</c:if> >使用 &nbsp;
					<input type="radio"  value="0" name="useState" <c:if test="${sdkSms.useState==0}" >checked</c:if>>停用 &nbsp;
					<span class="Validform_checktip"></span>
					</td>
			</tr>			
		</table>
	</t:formvalid>
</body>