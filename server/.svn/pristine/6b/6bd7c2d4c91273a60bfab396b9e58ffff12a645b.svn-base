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
 function checkData(){
 	var ary = new Array();
            var orders = $("input[name=idx]");
            for (var i = 0; i < orders.length; i++) {
                ary.push(orders.eq(i).val());
            }
            var nary = ary.sort();
             for (var j = 0; j < ary.length; j++) {
                if (nary[j] == nary[j + 1] && nary[j]!="0") {
                    alert("重复内容：" + nary[j]);
                    return false;
                }

            }
            return true;
 }
 </script>
 
  <t:formvalid formid="formobj" layout="div" dialog="true" action="gameInfo.do?savePayment"  beforeSubmit="checkData" >
			<input id="gameId" name="gameId" type="hidden" value="${gameId }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<c:forEach items="${sdkGamePayments}" var="item">
				        <tr>
					<td align="right">
						<label class="Validform_label">
							${item.sdkPayment.payCnName}:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="longInput" name="id" id="id"  value="${item.id}"  type="hidden" >
					<input class="longInput" name="idx" id="idx"  value="${item.idx}" datatype="n1-60" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
			</c:forEach>
				 
			</table>
		</t:formvalid>
 </body>