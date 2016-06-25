<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true"  layout="table"  beforeSubmit="checkSendData"  action="pushMsg.do?saveSend">
  <script type="text/javascript">
  function checkSendData(){
  		var urlvalue=$("#url").val();
  		if($("#chk2").val()==2 &&  $("#chk2").attr("checked")=="checked" && urlvalue=="" ){
  			alert("请输入url");
  			return false;
  		}
  		return true;
  }
  $(document).ready(function(){ 
	  $("input[name=chk1]").change(function (){
			var chkval=$(this).val();
			if(chkval==2){
				$("#url").css("display","block");
			}else{
				$("#url").focus();
				$("#url").val("");
				$("#url").blur();
				$("#url").css("display","none");
				}
			$("#contentType").val(chkval);
			
		   }
	   );
	  });
   
  
   
  </script>
			<input id="sdkGame.gameId" name="sdkGame.gameId" type="hidden" value="${gameId}"    >
			<input id="id" name="id" type="hidden"   ignore="ignore" >
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="title" id="title"  value="" datatype="s1-100"    >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
					<TEXTAREA  rows="10" cols="100" name="message" id="message"  value="" datatype="*"   ></TEXTAREA>
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							选择消息类型:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="contentType" id="contentType" value="1" type="hidden"/>
					<input name="chk1" id="chk1"   value="1"  type="radio" checked>纯文本
					<input  name="chk1" id="chk2" value="2" type="radio">Web页面
				   <input  style="display: none" class="inputxt"  ignore="ignore" name="url" id="url"  datatype="url"   > 
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
	 
			</table>
		</t:formvalid>
 </body>