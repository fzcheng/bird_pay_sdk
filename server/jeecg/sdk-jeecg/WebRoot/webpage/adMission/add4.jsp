<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>

<body scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="adMission.do?save">
    <input id="id" name="id" type="hidden" value="${adMission.id}">
    <input id="adverId" name="adverId" type="hidden" value="${adId}">
    <input id="showType" name="showType" type="hidden" value="${showType}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
				应用名称:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="appName" id="appName"  value="${app.appName}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				应用描述:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <textarea rows="10" cols="80" id="appDetail" name="appDetail" datatype="*"  value="${app.appDetail}">${app.appDetail}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				应用包下载地址:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="packageUrl" id="packageUrl"  value="${app.packageUrl}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				应用包大小:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="size" id="size"  value="${app.size}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				icon:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="icon" id="icon"  value="${app.icon}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				展示图片1:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="img1" id="img1"  value="${app.img1}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				展示图片2:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="img2" id="img2"  value="${app.img2}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				展示图片3:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="img3" id="img3"  value="${app.img3}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				展示图片4:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="img4" id="img4"  value="${app.img4}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				展示图片5:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="img5" id="img5"  value="${app.img5}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				版本名称:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="version" id="version"  value="${app.version}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				应用回调地址:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="callbackUrl" id="callbackUrl"  value="${app.callbackUrl}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				兑换比例:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="rate" id="rate"  value="${app.rate}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				兑换单位:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="rateUnit" id="rateUnit"  value="${app.rateUnit}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				版本更新code:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="versionCode" id="versionCode"  value="${app.versionCode}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				启动名:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="packagename" id="packagename"  value="${app.packagename}">
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				包名:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="packageName" id="packageName"  value="${app.packageName}">
			</td>
		</tr>
    </table>
  </t:formvalid>
</body>