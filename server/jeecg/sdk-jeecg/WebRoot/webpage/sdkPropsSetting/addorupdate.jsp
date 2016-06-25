<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认框设置添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkPropsSetting.do?save">
		<input id="id" name="id" type="hidden" value="${sdkPropsSetting.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
			<tr>
             <td align="center"><label class="Validform_label"> 渠道号: </label>
             </td>
             <td class="value">
              <select id="operatorPayChannelId"  name="operatorPayChannelId" required="true">
              <option value="">请选择</option>
               <c:forEach items="${payChannels}" var="payChannel">
                <option value="${payChannel.id}" <c:if test="${payChannel.id==sdkPropsSetting.operatorPayChannelId}">selected="selected"</c:if> >
                 ${payChannel.channelName}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			
			<tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkPropsSetting.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
				<td align="center"><label class="Validform_label"> 道具名称: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsName" id="propsName" value="${sdkPropsSetting.propsName}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 道具别名: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsAlias" id="propsAlias" value="${sdkPropsSetting.propsAlias}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
            <tr>
				<td align="center"><label class="Validform_label"> 金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="amount" id="amount" value="${sdkPropsSetting.amount}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 道具描述: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="propsDesc" id="propsDesc" value="${sdkPropsSetting.propsDesc}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
            <tr>
                <td align="center"><label class="Validform_label">使用状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="useStatus" name="useStatus">
                    <option value="1"  <c:if test="${sdkPropsSetting.useStatus==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${sdkPropsSetting.useStatus==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
			
		</table>
	</t:formvalid>
</body>