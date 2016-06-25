<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="makeApk.do?finish">
   <script type="text/javascript" > 
   $("document").ready(function(){  
	ajaxLoadEnd();
	
   })
    function ajaxLoadEnd(){  
     $(".datagrid-mask").remove();  
     $(".datagrid-mask-msg").remove();              
}
   </script>
  
   <script type="text/javascript">
function downloadFile() {
	window.location.href = "makeApk.do?zipApk&fileName="+$("#fileName").val(); 
}
 </script>
 <c:if test="${errorMessage==null}">
 <input type="hidden" name="fileName" id="fileName" value="${fileName}"/>
 <h1>打包完成，共完成 <label>${akpCount} </label>  个渠道的打包工作。开始下载？</h1>

</br>
</br>
<input type="button" value="下载" width="200px" onclick="return downloadFile();" />
 </c:if>
 <c:if test="${errorMessage!=null}">
  <h1>打包失败，${errorMessage}</h1>
 </c:if>
  




			 
		</t:formvalid>
 </body>