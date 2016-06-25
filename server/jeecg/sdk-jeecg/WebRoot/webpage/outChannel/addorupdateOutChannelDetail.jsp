<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <style type="text/css">
 .longInput{
 	width:350px;
 }
 </style>
 <script type="text/javascript">
 </script>
  <t:formvalid formid="formobj" layout="div" dialog="true" action="outChannel.do?saveOutChannelDetail"  >
   <script type="text/javascript">
  </script>
			<input id="id" name="id" type="hidden" value="${outChannelDetail.id}">
			<input id="createTime" name="createTime" type="hidden" value="${outChannelDetail.createTime}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
				 <tr>
				 	<td align="right"><label class="Validform_label">
							请选择渠道:
						</label>
						</td>
				 	<td class="value" style="width:400px">
                         <select id="sdkOutChannel.id"  name="sdkOutChannel.id" datatype="*" style="width:350px">
       <c:forEach items="${sdkOutChannelList}" var="outChannel">
        <option value="${outChannel.id }" <c:if test="${outChannel.id==outChannelDetail.sdkOutChannel.id}">selected</c:if>>
         ${outChannel.name}
        </option>
       </c:forEach>
     	
					</td>
				 </tr>
				 <tr>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="easyui-datebox"  style="width:350px" name="inputTime" id="inputTime"  value="${outChannelDetail.inputTime}" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							注册数:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="longInput" datatype="*" name="regNumber" id="regNumber"  value="${outChannelDetail.regNumber}" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>