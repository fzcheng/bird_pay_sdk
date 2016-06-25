<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>运营商支付方式添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkOperatorPayType.do?save" >
		<input id="id" name="id" type="hidden" value="${sdkOperatorPayType.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
             <td align="center"><label class="Validform_label"> 支付方式: </label>
             </td>
             <td class="value">
              <select id="type"  name="type">
               <c:forEach items="${sdkPayments}" var="sdkPayment">
                <option value="${sdkPayment.payType}" <c:if test="${sdkPayment.payType==sdkOperatorPayType.type}">selected="selected"</c:if> >
                 ${sdkPayment.payCnName}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			<tr>
					<td align="center">
						<label class="Validform_label">
						手机运营商类型
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="operator" <c:if test="${sdkOperatorPayType.operator==1}" >checked</c:if> >中国移动 &nbsp;
					<input type="radio"  value="2" name="operator" <c:if test="${sdkOperatorPayType.operator==2}" >checked</c:if>>中国联通 &nbsp;
					<input type="radio"  value="3" name="operator" <c:if test="${sdkOperatorPayType.operator==3}" >checked</c:if> >中国电信 &nbsp;
					<span class="Validform_checktip"></span>
					</td>
			</tr>
		 
			<tr>
				<td align="center"><label class="Validform_label"> 最小支付金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="minPrice" id="minPrice" value="${sdkOperatorPayType.minPrice}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 最大支付金额: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="maxPrice" id="maxPrice" value="${sdkOperatorPayType.maxPrice}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 优先级(最高0): </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="idx" id="idx" value="${sdkOperatorPayType.idx}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 最低版本号: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="ver" id="ver" value="${sdkOperatorPayType.ver}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
      
		</table>
	</t:formvalid>
</body>