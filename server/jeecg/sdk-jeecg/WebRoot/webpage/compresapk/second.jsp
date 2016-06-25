<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="makeApk.do?three">
  <input type="hidden" name="fileName" id="fileName" value="${fileName}"/>
  <input type="hidden" name="channelValues" id="channelValues" />

 <script type="text/javascript" >   
$("document").ready(function(){   
    $("input[name=chk]").click(function(){ 
		var countChk=$("input[name=chk]").length;
		var valuesChk="";
		 
        for(var j=0;j<countChk;j++){
		if($("input[name=chk]").eq(j).attr("checked")=="checked"){
			valuesChk+=$("input[name=chk]").eq(j).val()+",";
			}
			 
		}
		$("#channelValues").val(valuesChk);
    });
	$("#formobj").submit(function (){
	if($("#name").val()==""){
		alert("请输入游戏名称!");
		return false;
	}
		ajaxLoading();
	});
});

function ajaxLoading(){  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
 }   
</script>  
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							游戏名称:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="name" id="name"  value="" datatype="/^[0-9a-zA-Z][0-9a-zA-Z]+[0-9a-zA-Z]$/">
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道商:
						</label>
					</td>
					<td class="value">
						 
       <c:forEach items="${sdkChannels}" var="sdkChannel">
       <input type="checkbox" name="chk" id="chk_${sdkChannel.id}" value="${sdkChannel.id}" />${sdkChannel.channelName}
       </br>
       </c:forEach>
      </select>
      <span class="Validform_checktip">请选择渠道商</span>
					</td>
				</tr>
			
				 
			</table>
		</t:formvalid>
 </body>