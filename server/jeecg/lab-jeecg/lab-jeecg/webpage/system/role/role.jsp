<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>角色信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>

 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="roleController.do?saveRole">
    <input name="id" type="hidden" value="${role.id}">
    <fieldset class="step">
     <div class="form">
      <label class="Validform_label">角色名称:</label>
      <input name="roleName" class="inputxt" value="${role.roleName }" datatype="s2-8">
      <span class="Validform_checktip">角色范围2~8位字符之间,且不为空</span>
     </div>
     <div class="form">
      <label class="Validform_label">
              角色编码:
      </label>
      <input name="roleCode" id="roleCode" ajaxurl="roleController.do?checkRole&code=${role.roleCode }" class="inputxt" value="${role.roleCode }" datatype="s2-15">
       <span class="Validform_checktip">角色编码2~15位字符之间,且不为空</span>
     </div>
     <div class="form">
      <label class="Validform_label">
              默认首页Tab:
      </label> 
	      <select id="menu" name="menu">
						<option value="" selected="selected">未选择</option>
						<c:forEach items="${folderFunctionList}" var="function_folder">
							<option value="${function_folder.id }">
								${function_folder.functionName}</option>
						</c:forEach>
		  </select>
		  <select id="defaultTab" name="defaultTab"  style="display:none">
			 <option value="" selected="selected">未选择</option>
		  </select>
     </div>
    </fieldset>
  </t:formvalid>
  <input type="hidden"  id ="path" value="<%=path %>"/>
 </body>
 
 <script type="text/javascript">
		$(document).ready(function () {

            $("#menu").change(function(){
            	var parentFunction = $("#menu option:selected").val();
            	var requestData = "parentFunctionId=" + parentFunction;
            	$.ajax({
            		   type: "POST",
            		   url: "indexContentController.do?getChildrenFunction&" + requestData,
     
            		   dataType: "json",
            		   success: function(msg){
            			
            			   var array = msg.obj;
            			   $("#defaultTab").empty();
            			   $("#defaultTab").append('<option value="" selected="selected">未选择</option>');
            			   for(var i = 0; i < array.length; i++){
            				   var id = array[i].id;
            				   var name = array[i].functionName;
            				   var option = "<option value='" + id + "'>"+name + "</option>"
            				   $("#defaultTab").append(option);
            			   }
            			   $("#defaultTab").show();
            		   },
            		   error: function(){
            			   alert("error");
            		   }
            		   
                });
            });
 
        });
 
 
 </script>
</html>
