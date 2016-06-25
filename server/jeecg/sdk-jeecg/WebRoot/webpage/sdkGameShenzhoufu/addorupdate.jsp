<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>确认框设置添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkGameShenzhoufu.do?save">
		<input id="id" name="id" type="hidden" value="${sdkGameShenzhoufu.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
			<tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkGameShenzhoufu.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
				<td align="center"><label class="Validform_label"> 商户在神州付的唯一身份标识: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="merId" id="merId" value="${sdkGameShenzhoufu.merId}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 商户密钥: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="privateKey" id="privateKey" value="${sdkGameShenzhoufu.privateKey}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            
		</table>
	</t:formvalid>
</body>