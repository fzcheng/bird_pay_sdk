<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>短信中心号段添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkCenterNumber.do?save">
		<input id="id" name="id" type="hidden" value="${sdkCenterNumber.id}">
		<input type="hidden" id="cityCode" value="${sdkCenterNumber.areaCode}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable"> 
		  	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> 
			 <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script> 
			 <script language="javascript" type="text/javascript">
			 $(document).ready(function () {
			 
			 // 页面加载完成会执行下面函数
			 getcity();
			 
			 function getcity(){
			 	if($("#cityCode").val() ==""||undefined || null){
			 	}
			 	else{
			 		$.ajax({
						url:'sdkCenterNumber.do?getCity',
						data:{"provinceNo":$("#provinceNo").find("option:selected").val()},
						type:'GET',
						dataType:'json',
						success:function(data){							
								//alert("the parameter is:"+$("#provinceNo").find("option:selected").val());														
								//获取地市后，拼装select
								// data应该是一个数组，每个数包括地市id和地市名称，所以首先要遍历
								//if($("#cityCode").val() ==""||undefined || null){
								//		$("#cityAreaCode").empty();
								//}
								$("#areaCode").empty();
								$.each(data, function(i, item) {
									if ($("#cityCode").val() == i) {
   										$("#areaCode").append("<option value='" + i + "' selected=\"selected\" >" + item+ "</option>");
									}else{
										$("#areaCode").append("<option value='" + i + "'>" + item+ "</option>");
									}
									
									
								});								
							},
						error:function(msg){
								alter(msg);
							}
				        }
					   );
			 	}
			 };
			 
			 function provinceNoOnchange() {
			 	$.ajax({
						url:'sdkPhoneSegment.do?getCity',
						data:{"provinceNo":$("#provinceNo").find("option:selected").val()},
						type:'GET',
						dataType:'json',
						success:function(data){							
								//alert("the parameter is:"+$("#provinceNo").find("option:selected").val());														
								//获取地市后，拼装select
								// data应该是一个数组，每个数包括地市id和地市名称，所以首先要遍历
								//if($("#cityCode").val() ==""||undefined || null){
								//		$("#cityAreaCode").empty();
								//}
								$("#areaCode").empty();
								//var selectedStr = "";
								$.each(data, function(i, item) {
									$("#areaCode").append("<option value='" + i + "'>" + item+ "</option>");									
									//if ($("#cityCode").val() == i) {
   									//	selectedStr  = "selected='selected'";
									//}
									//$("#cityAreaCode").append("<option value='" + i + "' "+selectedStr+" >" + item+ "</option>");
								});								
							},
						error:function(msg){
								alter(msg);
							}
				        }
					   );
			 };
			 
			 // 下拉菜单改变时会执行
			 $("#provinceNo").change(function (){
						provinceNoOnchange();
					});
				});
			 </script>
			 
			<tr>
             <td align="center"><label class="Validform_label"> 省份: </label>
             </td>
             <td class="value">
              <select id="provinceNo"  name="provinceNo">
              <option value="">-请选择-</option>
               <c:forEach items="${sdkProvinceAddrs}" var="sdkProvinceAddr">
                <option value="${sdkProvinceAddr.id}" <c:if test="${sdkCenterNumber.provinceNo==sdkProvinceAddr.id}">selected="selected"</c:if>>
                 ${sdkProvinceAddr.provincenm}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			
			<tr>
             <td align="center"><label class="Validform_label"> 城市: </label>
             </td>
             <td class="value">
              <select id="areaCode"  name="areaCode">
              	<option value="">-请选择-</option>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr> 
			
			<tr>
				<td align="center"><label class="Validform_label"> 短信中心号段: </label></td>
				<td class="value" style="width: 90%"><input class="longInput" name="centernumber" id="centernumber" value="${sdkCenterNumber.centernumber}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
		</table>
	</t:formvalid>
</body>