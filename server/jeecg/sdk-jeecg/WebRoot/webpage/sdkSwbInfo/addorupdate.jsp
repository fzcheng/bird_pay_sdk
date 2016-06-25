<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>三网添加添加</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
	$(".demoform").Validform({
		
	});
</script>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkSwbInfo.do?save">
		<input id="id" name="id" type="hidden" value="${sdkSwbInfo.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			<tr>
				<td align="center"><label class="Validform_label">运营商</label></td>
				<td class="value" style="width: 400px">
	                <select id="operatorType" name="operatorType">
	                    <option value="1"  <c:if test="${sdkSwbInfo.operatorType==1}">selected</c:if>>中国移动</option>
	                    <option value="2"  <c:if test="${sdkSwbInfo.operatorType==2}">selected</c:if>>中国联通</option>
	                    <option value="3"  <c:if test="${sdkSwbInfo.operatorType==3}">selected</c:if>>中国电信</option>
	                </select>
                </td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">游戏</label></td>
				<td class="value" style="width: 400px">
				<select id="gameId" name="gameId">
					<c:forEach items="${games}" var="game">
						<option value="${game.gameId}" <c:if test="${game.gameId==sdkSwbInfo.gameId}">selected="selected"</c:if>>${game.name}</option>
					</c:forEach>
				</select> 
				<span class="Validform_checktip"></span>
				</td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label">游戏申请的appId</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="appId" id="appId" value="${sdkSwbInfo.appId}" datatype="*">
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label">游戏申请的appKey</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="appKey" id="appKey" value="${sdkSwbInfo.appKey}" datatype="*">
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label">沃商店为开发者分配的VAC资质编号(CPID)</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="cpId" id="cpId" value="${sdkSwbInfo.cpId}" ignore="ignore" datatype="*">(中国联通必填)
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">开发者在沃商店开发者社区的唯一编码(CPCODE)</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="cpCode" id="cpCode" value="${sdkSwbInfo.cpCode}" ignore="ignore" datatype="*">(中国联通必填)
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">公司名称</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="company" id="company" value="${sdkSwbInfo.company}" ignore="ignore" datatype="*">(中国联通必填)
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">电话号码</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="phone" id="phone" value="${sdkSwbInfo.phone}" ignore="ignore" datatype="*">(中国联通必填)
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">渠道</label></td>
				<td class="value" style="width: 400px">
					<input class="longInput" name="channel" id="channel" value="${sdkSwbInfo.channel}" ignore="ignore" datatype="*">(中国联通必填)
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label">使用状态</label></td>
				<td class="value" style="width: 400px">
	                <select id="useStatus" name="useStatus">
	                    <option value="1"  <c:if test="${sdkSwbInfo.useStatus==1}">selected</c:if>>使用</option>
	                    <option value="0"  <c:if test="${sdkSwbInfo.useStatus==0}">selected</c:if>>停用</option>
	                </select>
                </td>
			</tr>
		</table>
	</t:formvalid>
</body>