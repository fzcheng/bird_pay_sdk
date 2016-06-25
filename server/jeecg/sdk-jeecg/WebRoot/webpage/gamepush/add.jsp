<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pushMsg.do?save">
  <script type="text/javascript">

  $(document).ready(function(){ 
	  $("input[name=chk1]").change(function (){
			var chkval=$(this).val();
			if(chkval==2){
				$("#url").css("display","block");
				//$("#url").attr("disabled",false);
			}else{
				$("#url").focus();
				$("#url").val("");
				$("#url").blur();
				$("#url").css("display","none");
			//	$("#url").attr("disabled",true);
				}
			$("#contentType").val(chkval);
			
		   }
	   );
	  });
   
  
   
  </script>
			<input id="id" name="id" type="hidden" value="${gamePush.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
					<td align="right">
						<label class="Validform_label">
							游戏名:
						</label>
					</td>
					<td class="value">
					<select id="gameInfo.id"  name="gameInfo.id"  datatype="*">
       <c:forEach items="${gameInfos}" var="gameInfo">
        <option value="${gameInfo.id }" <c:if test="${gameInfo.id==gamePush.gameInfo.id}">selected="selected"</c:if>>
         ${gameInfo.name}
        </option>
       </c:forEach>
      </select>
      <span class="Validform_checktip">请选择游戏商</span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="title" id="title"  value="${gamePush.title}" datatype="s" >
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
					<TEXTAREA  rows="10" cols="100" name="message" id="message"  value="${gamePush.message}" datatype="*" ></TEXTAREA>
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否推荐:
						</label>
					</td>
					<td class="value">
					<input class="inputxt" name="contentType" id="contentType" value="1" type="hidden"/>
					<input class="inputxt" name="chk1" id="chk1"   value="1"  type="radio">不推荐
					<input class="inputxt" name="chk1" id="chk2" value="2" type="radio">推荐
					<input class="inputxt" ignore="ignore" name="url" id="url"  datatype="url" value="${gamePush.url}"  style="display: none" > 
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
	 
			</table>
		</t:formvalid>
 </body>