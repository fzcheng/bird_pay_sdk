<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<script language="javascript">
	function select(){
		alert("进来了！");
		var values=document.getElementById('showType').value;
		alert("选择了："+values);
		if(values==1){
			alert("进来了1！");
			document.getElementById('div2').style.display="none";
		}else if(values==2){
			alert("进来了2！");
			document.getElementById('div2').style.display="";//显示
			document.getElementById('div5').style.visibility="hidden";//隐藏
		}else if(values==3){
			alert("进来了3！");
			document.getElementById('div2').style.display="none";
		}else if(values==4){
			alert("进来了4！");
			document.getElementById('div2').style.display="none";
			document.getElementById('div5').style.visibility="hidden";//隐藏
		}else if(values==5){
			alert("进来了5！");
			document.getElementById('div2').style.display="none";//隐藏
			document.getElementById('div5').style.visibility="visible";//显示
		}
		alert("div2状态："+document.getElementById('div2').style.display);
		alert("div5状态："+document.getElementById('div2').style.visibility);
	}

</script>

<style type="text/css">
#div2{
display:none;
}
#div5{
visibility:hidden;
}
</style>

<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="adMission.do?save">
    <input id="id" name="id" type="hidden" value="${adMission.id}">
    <input id="adverId" name="adverId" type="hidden" value="${adMission.adId}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
       	<tr>
			<td align="right">
				<label class="Validform_label">
				任务开始时间:
				</label>
			</td>
			<td class="value">
			<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="beginTime" id="beginTime"  
			value="<fmt:formatDate value='${adMission.beginTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	    			<span class="Validform_checktip"></span>
			</td>
		</tr>
      	<tr>
			<td align="right">
				<label class="Validform_label">
				任务截止时间:
				</label>
			</td>
			<td class="value">
			<input class="Wdate"  style="width: 150px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  name="endTime" id="endTime"  
			value="<fmt:formatDate value='${adMission.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" >
	    			<span class="Validform_checktip"></span>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				显示类型:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <select id="showType" name="showType" onchange="select();">
                    <!-- <option value="1"  <c:if test="${adMission.showType==1}">selected</c:if>>开屏广告</option> -->
                    <option value="2"  <c:if test="${adMission.showType==2}">selected</c:if>>插屏广告</option>
                    <!-- <option value="3"  <c:if test="${adMission.showType==3}">selected</c:if>>banner广告</option> -->
                    <option value="4"  <c:if test="${adMission.showType==4}">selected</c:if>>积分墙广告</option>
                    <option value="5"  <c:if test="${adMission.showType==5}">selected</c:if>>插屏视频广告</option>
                </select>
			</td>
		</tr>
		<div id="div2">
			<tr>
				<td align="right">
					<label class="Validform_label">
					插屏广告图片:
					</label>
				</td>
				<td class="value" style="width: 400px">
	                <input class="inputxt"   name="show_img" id="show_img"  value="${adMission.show_img}" datatype="s1-256" >
				</td>
			</tr>
		</div>
		<div id="div5">
			<tr>
				<td align="right">
					<label class="Validform_label">
					插屏广告图片:
					</label>
				</td>
				<td class="value" style="width: 400px">
	                <input class="inputxt"   name="show_img" id="show_img"  value="${adMission.show_img}" datatype="s1-256" >
				</td>
			</tr>
		</div>
    </table>
  </t:formvalid>
</body>