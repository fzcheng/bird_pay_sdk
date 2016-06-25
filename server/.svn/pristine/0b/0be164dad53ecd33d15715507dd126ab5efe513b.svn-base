<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="adMissionSub.do?save">
    <input id="id" name="id" type="hidden" value="${adMissionSub.id}">
    <input id="missionId" name="missionId" type="hidden" value="${missionId}">
    <input id="missionSubId" name="missionSubId" type="hidden" value="${missionSubId}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
        <td align="right"><label class="Validform_label"> 序号: </label></td>
        <td class="value"><input class="inputxt" name="no" id="no" value="${adMissionSub.no}" datatype="s">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
		<td align="right">
			<label class="Validform_label">
			任务类型：: 
			</label>
		</td>
		<td class="value">
		<input type="radio"  value="1" name="type" <c:if test="${adMissionSub.type==1}" >checked</c:if>>登录 &nbsp;
		<input type="radio"  value="0" name="type" <c:if test="${adMissionSub.type==0}" >checked</c:if>>下载／点击／展示 &nbsp;
		<input type="radio"  value="2" name="type" <c:if test="${adMissionSub.type==2}" >checked</c:if>>等级达到xx &nbsp;
		<span class="Validform_checktip"></span>
			 
		</td>
	</tr>
	<tr>
        <td align="right"><label class="Validform_label"> 任务类型数值: </label></td>
        <td class="value"><input class="inputxt" name="typeValue" id="typeValue" value="${adMissionSub.typeValue}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 奖励鸟币: </label></td>
        <td class="value"><input class="inputxt" name="birdMoney" id="birdMoney" value="${adMissionSub.birdMoney}"> <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
        <td align="right"><label class="Validform_label"> 任务描述: </label></td>
        <td class="value"><input class="inputxt" name="missionDetail" id="missionDetail" value="${adMissionSub.missionDetail}" datatype="s"> <span class="Validform_checktip"></span></td>
      </tr>
    </table>
  </t:formvalid>
</body>