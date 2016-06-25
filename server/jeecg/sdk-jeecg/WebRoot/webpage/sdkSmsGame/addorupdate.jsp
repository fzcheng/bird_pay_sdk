<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>获取手机号短信发送设置表添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkSmsGame.do?save">
		<input id="id" name="id" type="hidden" value="${sdkSmsGame.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			
			 <tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkSmsGame.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			 
			<tr>
				<td align="center"><label class="Validform_label"> 是否发送
				</label></td>
				<td class="value">
					<input type="radio"  value="1" name="sendState" <c:if test="${sdkSmsGame.sendState==1}" >checked</c:if> >发送 &nbsp;
					<input type="radio"  value="0" name="sendState" <c:if test="${sdkSmsGame.sendState==0}" >checked</c:if>>不发送 &nbsp;
					<span class="Validform_checktip"></span>
					</td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 是否显示发送短信提示框
				</label></td>
				<td class="value">
					<input type="radio"  value="1" name="smstip" <c:if test="${sdkSmsGame.smstip==1}" >checked</c:if> >显示 &nbsp;
					<input type="radio"  value="0" name="smstip" <c:if test="${sdkSmsGame.smstip==0}" >checked</c:if>>不显示 &nbsp;
					<span class="Validform_checktip"></span>
					</td>
			</tr>			
		</table>
	</t:formvalid>
</body>