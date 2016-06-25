<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>游戏使用支付通道情况添加</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
	$(".demoform").Validform({
		
	});
</script>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkGamePayrepeat.do?save">
		<input id="id" name="id" type="hidden" value="${sdkGamePayrepeat.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			<tr>
				<td align="center"><label class="Validform_label">游戏</label></td>
				<td class="value" style="width: 400px">
				<select id="gameId" name="gameId">
					<c:forEach items="${games}" var="game">
						<option value="${game.gameId}" <c:if test="${game.gameId==sdkGamePayrepeat.gameId}">selected="selected"</c:if>>${game.name}</option>
					</c:forEach>
				</select> 
				<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">是否使用</label></td>
				<td class="value" style="width: 400px">
	                <select id="usestate" name="usestate">
	                	<option value="0"  <c:if test="${sdkGamePayrepeat.usestate==0}">selected</c:if>>否</option>
	                    <option value="1"  <c:if test="${sdkGamePayrepeat.usestate==1}">selected</c:if>>是</option>
	                </select>
                </td>
			</tr>
		</table>
	</t:formvalid>
</body>