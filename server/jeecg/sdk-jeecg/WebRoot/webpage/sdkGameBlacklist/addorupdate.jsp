<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>黑名单添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkGameBlacklist.do?save">
		<input id="id" name="id" type="hidden" value="${sdkGameBlacklist.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			<tr>
				<td align="center"><label class="Validform_label"> 国际移动用户识别码（IMSI）: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="imsi" id="imsi" value="${sdkGameBlacklist.imsi}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label"> 手机号码: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="mobile" id="mobile" value="${sdkGameBlacklist.mobile}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<!--  <tr>
				<td align="center"><label class="Validform_label"> 手机运营商类型(1:中国移动 2:中国联通 3:中国电信): </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="operatorType" id="operatorType" value="${sdkGameBlacklist.operatorType}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>-->
			
			<tr>
					<td align="center">
						<label class="Validform_label">
						手机运营商类型
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="operatorType" <c:if test="${sdkGameBlacklist.operatorType==1}" >checked</c:if> >中国移动 &nbsp;
					<input type="radio"  value="2" name="operatorType" <c:if test="${sdkGameBlacklist.operatorType==2}" >checked</c:if>>中国联通 &nbsp;
					<input type="radio"  value="3" name="operatorType" <c:if test="${sdkGameBlacklist.operatorType==3}" >checked</c:if> >中国电信 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
			
		</table>
	</t:formvalid>
</body>