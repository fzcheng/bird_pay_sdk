<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认框设置添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="channelGameConfirm.do?save">
		<input id="id" name="id" type="hidden" value="${sdkChannelGameConfirm.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
			<tr>
             <td align="center"><label class="Validform_label"> 渠道号: </label>
             </td>
             <td class="value">
              <select id="channelCode"  name="channelCode" required="true">
              <option value="">请选择</option>
               <c:forEach items="${payChannels}" var="payChannel">
                <option value="${payChannel.channelCode}" <c:if test="${payChannel.channelCode==sdkChannelGameConfirm.channelCode}">selected="selected"</c:if> >
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
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkChannelGameConfirm.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
             <td align="center"><label class="Validform_label"> 计费代码配置的原本游戏: </label>
             </td>
             <td class="value">
              <select id="originalGameId"  name="originalGameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkChannelGameConfirm.originalGameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			
			<tr>
                <td align="center"><label class="Validform_label"> 计费提示框使用状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="chargetip" name="chargetip">
                    <option value="1"  <c:if test="${sdkChannelGameConfirm.chargetip==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${sdkChannelGameConfirm.chargetip==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label">计费成功提示框使用状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="chargesuceesstip" name="chargesuceesstip">
                    <option value="1"  <c:if test="${sdkChannelGameConfirm.chargesuceesstip==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${sdkChannelGameConfirm.chargesuceesstip==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
			
		</table>
	</t:formvalid>
</body>