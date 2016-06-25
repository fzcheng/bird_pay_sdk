<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="sdkGameGif.do?save">
  			<input id="giftId" name="giftId" type="hidden" value="${sdkGift.giftId}">
  			<input id="gameId" name="gameId" type="hidden" value="${gameId}">
			<table style="width: 650px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
						礼包名称:
						</label>
					</td>
					<td class="value">
					<input class="inputxt"   name="title" id="title"  value="${sdkGift.title}" datatype="s1-256" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
						活动截止时间:
						</label>
					</td>
					<td class="value">
					<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="endDate" id="endDate"  
					value="<fmt:formatDate value='${sdkGift.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
								<tr>
					<td align="right">
						<label class="Validform_label">
						礼包验证码:
						</label>
					</td>
					<td class="value">
					<textarea rows="10" cols="80" id="vcodes" name="vcodes" datatype="*"  value="${sdkGift.vcodes}">${sdkGift.vcodes}</textarea>
	      			(英文逗号分隔)
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>	
								<tr>
					<td align="right">
						<label class="Validform_label">
						礼包详情:
						</label>
					</td>
					<td class="value">
					<textarea rows="10" cols="80" id="detail" name="detail" datatype="*"  value="${sdkGift.detail}">${sdkGift.detail}</textarea>
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>	 
			</table>
		</t:formvalid>
 </body>