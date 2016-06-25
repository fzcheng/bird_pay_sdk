<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认框设置添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkGameAlipay.do?save">
		<input id="id" name="id" type="hidden" value="${sdkGameAlipay.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 			
			<tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkGameAlipay.sdkGame.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
				<td align="center"><label class="Validform_label"> 支付宝合作商户ID: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="partner" id="partner" value="${sdkGameAlipay.partner}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 支付宝账户ID: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="seller" id="seller" value="${sdkGameAlipay.seller}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
            <tr>
				<td align="center"><label class="Validform_label"> 通知回调地址: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="notifyUrl" id="notifyUrl" value="${sdkGameAlipay.notifyUrl}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 支付宝商户（RSA）私钥: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="rsaPrivate" id="rsaPrivate" value="${sdkGameAlipay.rsaPrivate}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
			<tr>
				<td align="center"><label class="Validform_label"> 支付宝（RSA）公钥: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="rsaAlipayPublic" id="rsaAlipayPublic" value="${sdkGameAlipay.rsaAlipayPublic}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
		</table>
	</t:formvalid>
</body>