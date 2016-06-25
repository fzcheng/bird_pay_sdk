<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
   <t:base type="jquery,easyui,tools"></t:base>
	<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
	 function goForm() {
		 $('#myCkeditorForm').form('submit', {
				url : "jeecgDemoController.do?saveCkeditor",
				dataType:"json",
				success : function(data) {
					var mydata = eval("("+data+")");
					alert(mydata.msg);
				}
		});
     }
	</script>
 </head>

<form action="jeecgDemoController.do?saveCkeditor" method="post" id="myCkeditorForm">
		<input type="hidden" value="${cKEditorEntity.id}" name="id"/>
		<textarea cols="80" id="editor1" name="contents" rows="15">
			${contents}
		</textarea>
		<script>

			// This call can be placed at any point after the
			// <textarea>, or inside a <head><script> in a
			// window.onload event handler.

			// Replace the <textarea id="editor"> with an CKEditor
			// instance, using default configurations.
			
			CKEDITOR.replace( 'editor1');


		</script>
		<p>
			<input type="button" value="更新" onclick="goForm();">
		</p>
	</form>
 </html>