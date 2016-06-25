<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>运营商支付渠道添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="optpaychannel.do?save">
		<input id="id" name="id" type="hidden" value="${payChannel.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
            <tr>
				<td align="center"><label class="Validform_label"> 签约公司名称: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="signCorporation" id="signCorporation" value="${payChannel.signCorporation}"> </td>
			</tr>
			
            <tr>
				<td align="center"><label class="Validform_label"> 支付通道公司名称: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="corporation" id="corporation" value="${payChannel.corporation}"> </td>
			</tr>
            <tr>
                <td align="center"><label class="Validform_label"> 运营商: </label></td>
                <td class="value" style="width: 400px">
                <select id="operatorType" name="operatorType">
                    <option value="1"  <c:if test="${payChannel.operatorType==1}">selected</c:if>>移动</option>
                    <option value="2"  <c:if test="${payChannel.operatorType==2}">selected</c:if>>联通</option>
                    <option value="3"  <c:if test="${payChannel.operatorType==3}">selected</c:if>>电信</option>
                </select>
                </td>
            </tr>
            <tr>
                <td align="center"><label class="Validform_label"> 短信方式: </label></td>
                <td class="value" style="width: 400px">
                <select id="smsType" name="smsType">
                    <option value="1"  <c:if test="${payChannel.smsType==1}">selected</c:if>>短信指令</option>
                    <option value="2"  <c:if test="${payChannel.smsType==2}">selected</c:if>>网络获取</option>
                    <option value="3"  <c:if test="${payChannel.smsType==3}">selected</c:if>>SDK接入</option>
                </select>
                </td>
            </tr>
			<tr>
				<td align="center"><label class="Validform_label"> 支付渠道名: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="channelName" id="channelName" value="${payChannel.channelName}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="center"><label class="Validform_label"> 支付渠道号: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="channelCode" id="channelCode" value="${payChannel.channelCode}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
            <tr>
                <td align="center"><label class="Validform_label"> SDK最低版本: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="sdkMinVer" id="sdkMinVer" value="${payChannel.sdkMinVer}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 短信内容发送方式: </label></td>
                <td class="value" style="width: 400px">
                <select id="smsContentType" name="smsContentType">
                    <option value="1"  <c:if test="${payChannel.smsContentType==1}">selected</c:if>>字符串</option>
                    <option value="2"  <c:if test="${payChannel.smsContentType==2}">selected</c:if>>二进制</option>
                    <option value="3"  <c:if test="${payChannel.smsContentType==3}">selected</c:if>>多条短信</option>
                </select>
                </td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 每日限额: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="dayLimit" id="dayLimit" value="${payChannel.dayLimit}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 每月限额: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="monthLimit" id="monthLimit" value="${payChannel.monthLimit}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 单通道补点时间间隔: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="timeinterval" id="timeinterval" value="${payChannel.timeinterval}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
            <tr>
                <td align="center"><label class="Validform_label"> 请求计费时间间隔: </label></td>
                <td class="value" style="width: 400px"><input class="longInput" name="reqTimeinterval" id="reqTimeinterval" value="${payChannel.reqTimeinterval}" datatype="*"> <span class="Validform_checktip"></span></td>
            </tr>
            
		</table>
	</t:formvalid>
</body>