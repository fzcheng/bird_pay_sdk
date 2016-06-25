<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="app.do?save">
    <input id="id" name="id" type="hidden" value="${app.id}">
    <input id="apid" name="apid" type="hidden" value="${apid}">
    <input id="appid" name="appid" type="hidden" value="${appid}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
        <td align="right"><label class="Validform_label"> 应用名称: </label></td>
        <td class="value"><input class="inputxt" name="appName" id="appName" value="${app.appName}" datatype="s">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
		<td align="right">
			<label class="Validform_label">
			App类型: 
			</label>
		</td>
		<td class="value">
		<input type="radio"  value="1" name="type" <c:if test="${app.type==1}" >checked</c:if>>IOS &nbsp;
		<input type="radio"  value="0" name="type" <c:if test="${app.type==0}" >checked</c:if>>Android &nbsp;
		<span class="Validform_checktip"></span>
			 
		</td>
	</tr>
	<tr>
        <td align="right"><label class="Validform_label"> 应用描述: </label></td>
        <td class="value"><input class="inputxt" name="appDetail" id="appDetail" value="${app.appDetail}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用包下载地址: </label></td>
        <td class="value"><input class="inputxt" name="packageUrl" id="packageUrl" value="${app.packageUrl}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用大小: </label></td>
        <td class="value"><input class="inputxt" name="size" id="size" value="${app.size}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用icon: </label></td>
        <td class="value"><input class="inputxt" name="icon" id="icon" value="${app.icon}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用简介图片1: </label></td>
        <td class="value"><input class="inputxt" name="img1" id="img1" value="${app.img1}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用简介图片2: </label></td>
        <td class="value"><input class="inputxt" name="img2" id="img2" value="${app.img2}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用简介图片3: </label></td>
        <td class="value"><input class="inputxt" name="img3" id="img3" value="${app.img3}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用简介图片4: </label></td>
        <td class="value"><input class="inputxt" name="img1" id="img4" value="${app.img4}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用简介图片5: </label></td>
        <td class="value"><input class="inputxt" name="img5" id="img5" value="${app.img5}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 版本名称: </label></td>
        <td class="value"><input class="inputxt" name="version" id="version" value="${app.version}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用回调地址: </label></td>
        <td class="value"><input class="inputxt" name="callbackUrl" id="callbackUrl" value="${app.callbackUrl}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用比率(%): </label></td>
        <td class="value"><input class="inputxt" name="rate" id="rate" value="${app.rate}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 应用比率单位: </label></td>
        <td class="value"><input class="inputxt" name="rateUnit" id="rateUnit" value="${app.rateUnit}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 版本更新code: </label></td>
        <td class="value"><input class="inputxt" name="versionCode" id="versionCode" value="${app.versionCode}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 启动名: </label></td>
        <td class="value"><input class="inputxt" name="packagename" id="packagename" value="${app.packagename}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 包名: </label></td>
        <td class="value"><input class="inputxt" name="packageName" id="packageName" value="${app.packageName}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> IOS端推送唯一标识: </label></td>
        <td class="value"><input class="inputxt" name="deviceToken" id="deviceToken" value="${app.deviceToken}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
    </table>
  </t:formvalid>
</body>