<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
   <script type="text/javascript">

  $(document).ready(function(){ 
	  $("input[name=type]").change(function (){
	  $("tr[class=showdw]").css("display","none");
	  $("tr[class=showurl]").css("display","none");
			var chkval=$(this).val();
			if(chkval==2){
				 $("tr[class=showdw]").css("display","table-row");
			}else if(chkval==1){
			    $("tr[class=showurl]").css("display","table-row");
				}
		   }
	   );
	  });
   
   function checkradio(){
     var  checkedtype=$("input[name=type]").val();
   	 alert(checkedtype);
   }
  
   
  </script>
  <t:formvalid formid="formobj" layout="div" dialog="true" action="sdkBulletin.do?save">
  			<input id="bulletinId" name="bulletinId" type="hidden" value="${sdkBulletin.bulletinId}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
						公告内容:
						</label>
					</td>
					<td class="value">
					<textarea rows="10" cols="50" name="detail" id="detail" datatype="*">${sdkBulletin.detail}</textarea>  
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
						选择公告类型
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="2" name="type" <c:if test="${sdkBulletin.type==2}" >checked</c:if>>下载游戏 &nbsp; 
					<input type="radio"  value="1" name="type" <c:if test="${sdkBulletin.type==1}" >checked</c:if>>网页链接 &nbsp;
					<input type="radio"  value="0" name="type" <c:if test="${sdkBulletin.type==0}" >checked</c:if>>无反应 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr class="showdw"  <c:if test="${sdkBulletin.type!=2}">style="display:none"</c:if> >
					<td align="right" >
						<label class="Validform_label">
						游戏包名:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="gamePkg" id="gamePkg"  value="${sdkBulletin.gamePkg}" datatype="s"  ignore="ignore" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr class="showurl"  <c:if test="${sdkBulletin.type!=1}">style="display:none"</c:if>>
					<td align="right" >
						<label class="Validform_label">
						Web页URL:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="gameUrl" id="gameUrl"  value="${sdkBulletin.gameUrl}" datatype="url" ignore="ignore" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>				 
			</table>
		</t:formvalid>
 </body>