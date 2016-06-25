<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="sdkGamePlan.do?save">
  			<input id="id" name="id" type="hidden" value="${sdkGamePlan.id}">
  			<input id="sdkPlan.id" name="sdkPlan.id" type="hidden" value="${planId}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
						游戏包名:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="gamePkg" id="gamePkg"  value="${sdkGamePlan.gamePkg}" datatype="s1-50" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>		 
			</table>
		</t:formvalid>
 </body>