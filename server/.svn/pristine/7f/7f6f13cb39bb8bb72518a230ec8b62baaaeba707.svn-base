<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="gamePlanSet.do?save">
			<input id="gameId" name="gameId" type="hidden" value="${gameId}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">

				 <tr>
				 	<td colspan="2" align="center"><label class="Validform_label">
							选择广告墙方案:
						</label></td>
				 </tr>
				 <tr>
				 	<td colspan="2" align="center" class="value">
                       <select id="selectPlan" name="selectPlan" style="width:90%">
				       <c:forEach items="${sdkPlans}" var="item">
				       <option value="${item.id}"  <c:if test="${item.id==selectPlanId}">selected</c:if> >${item.name}</option>
				       </c:forEach>
				       </select>
     	
					</td>
				 </tr>
			</table>
		</t:formvalid>
 </body>