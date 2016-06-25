<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" action="mmdoSetting.do?saveSetting">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td style="width: 200px;height:40px">运营商</td>
			<td style="width: 200px">每天限额</td>
			<td style="width: 200px">每月限额</td>
			</tr>
			<c:forEach items="${limits}" var="limit" varStatus="idx">
				<tr>
					<td align="center">
					<label class="Validform_label">
					 <c:if test="${limit.operatorType==1}">移动</c:if><c:if test="${limit.operatorType==2}">联通</c:if> <c:if test="${limit.operatorType==3}">电信</c:if>
					</label>
                    <input type="hidden" name="limits[${idx.index}].id" value="${limit.id}" />
                    <input type="hidden" name="limits[${idx.index}].operatorType" value="${limit.operatorType}" />
                    </td>
					<td class="value" style="width: 400px"><input name="limits[${idx.index}].dayLimit" value="${limit.dayLimit}"  datatype="*"> <span class="Validform_checktip"></span></td>
					<td class="value" style="width: 400px"><input name="limits[${idx.index}].monthLimit" value="${limit.monthLimit}"  datatype="*"> <span class="Validform_checktip"></span></td>
				</tr>

			</c:forEach>


		</table>
	</t:formvalid>


</body>