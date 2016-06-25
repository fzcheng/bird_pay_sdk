<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>jar包对应游戏ID添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkUpgradeJarGame.do?save">
		<input id="id" name="id" type="hidden" value="${sdkUpgradeJarGame.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
			
			<tr>
             <td align="center"><label class="Validform_label"> 版本号: </label>
             </td>
             <td class="value">
              <select id="versionCode"  name="versionCode" class="easyui-combobox" style="text-align:left;width:200px;">
               <c:forEach items="${sdkUpgradeJars}" var="sdkUpgradeJar">
                <option value="${sdkUpgradeJar.versionCode}" <c:if test="${sdkUpgradeJar.versionCode==sdkUpgradeJarGame.versionCode}">selected="selected"</c:if> >
                 ${sdkUpgradeJar.versionCode}
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
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkUpgradeJarGame.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
            
            <tr>
                <td align="center"><label class="Validform_label"> 使用状态: </label></td>
                <td class="value" style="width: 400px">
                <select id="statusTag" name="statusTag">
                    <option value="1"  <c:if test="${sdkUpgradeJarGame.statusTag==1}">selected</c:if>>使用</option>
                    <option value="0"  <c:if test="${sdkUpgradeJarGame.statusTag==0}">selected</c:if>>停用</option>
                </select>
                </td>
            </tr>
            
		</table>
	</t:formvalid>
</body>