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
  	var chklength=$("input[name=payType]").length;
	 for(var i=0;i<chklength;i++){
	 if($("input[name=payType]").eq(i).val()==5 && $("input[name=payType]").eq(i).attr("checked")=="checked"){
	 	if($("#weipaykey").val()==""){
	 		alert("温馨提示：请到游戏列表中设置相应的微派支付项和支付金额，否则微派支付不能正常使用。");
	 		return false;
	 	}
	 	
	 }
//	 	alert($("input[name=payType]").eq(i).val()+"---"+$("input[name=payType]").eq(i).attr("checked")=="checked");
// 	 	if($("input[name=payType]").eq(i).val()==5 && $("input[name=payType]").eq(i).css("checked")="checked"){
// 	 		 alert("温馨提示：请到游戏列表中设置相应的微派支付项和支付金额，否则微派支付不能正常使用。");
// 	 	}
	 }
}
 </script>
  <t:formvalid formid="formobj" layout="div" dialog="true" action="gameInfo.do?save"  beforeSubmit="checkData">
   <script type="text/javascript">

  $(document).ready(function(){ 
	  $("input[name=payType]").change(function (){
			var chkval=$(this).val();			
			var weipai=$("#weipaykey").val();
			if(chkval==5 && weipai=="" && $(this).attr("checked")=="checked" ){
			 	
				 alert("请填写微派ID");
				// $("#weipaykey").attr("datatype","*");
				// $("#weipaykey"). focus();
				//$("#url").attr("disabled",false);
			} 

		   }
	   );
	  });
   
  
   
  </script>
			<input id="gameId" name="gameId" type="hidden" value="${gameDataPage.gameId }">
			<input id="cp_id" name="cp_id" type="hidden" value="${cp_id}">
			<input id="appKey" name="appKey" type="hidden" value="${appKey}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							游戏名称:
						</label>
					</td>
					<td class="value" style="width:400px">
					<input class="longInput" name="name" id="name"  value="${gameDataPage.name}" datatype="s1-60" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							游戏包名:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="packageName" id="packageName"  value="${gameDataPage.packageName}" datatype="s1-50" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
								<tr>
					<td align="right">
						<label class="Validform_label">
							客户端APPID:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="appKey" id="appKey"  value="${appKey}" datatype="s1-64"   disabled="disabled">
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Server KEY:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="ServerKEY" id="ServerKEY"  value="${ServerKEY}" datatype="s1-64"  disabled="disabled">
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Push_MasterSecret:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="pushMasterSecret" id="pushMasterSecret"  value="${pushMasterSecret}" datatype="s1-30" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Push_AppKey:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="pushAppkey" id="pushAppkey"  value="${pushAppkey}" datatype="s1-30" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							微派ID:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="weipaykey" id="weipaykey"  value="${gameDataPage.weipaykey}"   datatype="s1-60"  ignore="ignore">
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							回调URL:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="callbackRecharge" id="callbackRecharge"  value="${gameDataPage.callbackRecharge}"  datatype="url" >
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				
				 
				<tr>
					<td align="right">
						<label class="Validform_label">
							SDK版本:
						</label>
					</td>
					<td class="value">
					<input class="longInput" name="version" id="version"  value="${gameDataPage.version}"   datatype="s1-15" >
	      			<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
						是否设置显示
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="ifPush" <c:if test="${gameDataPage.ifPush==1}" >checked</c:if>>显示 &nbsp;
					<input type="radio"  value="0" name="ifPush" <c:if test="${gameDataPage.ifPush==0}" >checked</c:if>>隐藏 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				
				 <tr>
				 	<td colspan="2" align="center"><label class="Validform_label">
							请选择付费方式:
						</label></td>
				 </tr>
				 <tr>
				 	<td colspan="2" align="center" class="value">
                         <a>
				       <c:forEach items="${selectList}" var="item">
				        <input  type="checkbox" name="payType" value="${item.id}" <c:if test="${item.checked==true}">checked</c:if>>
				         ${item.name} &nbsp;
				        </input>
				       </c:forEach>
				       </a>
     	
					</td>
				 </tr>
			</table>
		</t:formvalid>
 </body>