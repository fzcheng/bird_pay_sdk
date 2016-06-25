<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>上传文件</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div"  dialog="true" beforeSubmit="upload">
  <input type="hidden" name="fileName" id="fileName"/>
  
    <input id="id" name="id" type="hidden" value="${sdkGameBlacklist.id}">
  
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				 
				<tr>
	     <td align="right" height="60">
	      <span class="filedzt"> 文件(请上传png,jpeg,jpg,bmp格式)：</span>
	     </td>
	     <td class="value" style="width:400px;">

    
	       <fieldset class="step">		    
		     <div class="form" style="width:400px;">
		     <link rel="stylesheet" href="plug-in/uploadify/uploadify.css" type="text/css"></link>		     
		      
		     <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>  
		     <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.min.js"></script> 
		     <!-- 
		     <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify.js"></script>
		     -->
		     <script type="text/javascript" src="plug-in/tools/Map.js"></script>
		     <script type="text/javascript">
		     		var flag = false;
		     		var fileitem="";
		     		var fileKey="";
		     		var serverMsg="";
		     		var m = new Map();	     		
		     		$(function(){
		     			$('#file_upload').uploadify({
		     					buttonText:'上传文件',
		     					auto:false,
		     					progressData:'speed',
		     					multi:true,
		     					height:25,
		     					queueSizeLimit:1,
		     					overrideEvents:['onDialogClose'],
		     					fileTypeDesc:'文件格式:',
		     					queueID:'filediv',
		     					fileTypeExts:'*.png;*.jpeg;*.jpg;*.bmp',
		     					fileSizeLimit:'1000MB',
		     					swf:'plug-in/uploadify/uploadify.swf',	
		     					uploader:'sdkGameAdvertisementList.do?saveFile',
		     					onUploadStart : function(file) { 
		     							var id=$('#id').val();
		     							$('#file_upload').uploadify("settings", "formData", {'id':id,'adurl':$("input[name=advertisementUrl]").val(),'ifpush':$("input[name='ifPush']:checked").val()});
		     					} ,
		     					onQueueComplete : function(queueData) {
									javascript:window.close();	
		     					},		     							     					
		     					onUploadSuccess : function(file, data, response) {	     					    
		     					    
		     						var d=$.parseJSON(data);
		     						if(d.success){	
		     							if(d.attributes.picfile!=null){
		     								$("#fileName").val(d.attributes.picfile);	
		     							}
		     							     							 
		     							serverMsg = d.msg;
		     						}		     						
		     					},
		     					
		     					onFallback : function(){
		     						tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
		     					},
		     					onSelectError : function(file, errorCode, errorMsg){
		     						switch(errorCode) {
		     							case -100:tip("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");break;
		     							case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");break;
		     							case -120:tip("文件 ["+file.name+"] 大小异常！");break;
		     							case -130:tip("文件 ["+file.name+"] 类型不正确！");break;
		     						}
		     					},
		     					onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
		     					}
		     			});
		     		});
		     		
		     		function upload() {	
		     			$('#file_upload').uploadify('upload', '*');		
		     			return flag;
		     		}
		     		
		     		function cancel() {
		     			$('#file_upload').uploadify('cancel', '*');
		     		}
		     				     		
		     		</script>		    				
		     
		     		<span id="file_uploadspan"><input type="file" name="files" id="file_upload" /></span>
		    </div>
		    <div class="form" id="filediv" style="height:100px;width:400px;">		    
		    </div>
		   </fieldset>    
    
	     </td>
	    </tr> 
			</table>
			
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			<tr>
				<td align="center"><label class="Validform_label"> 广告地址: </label></td>
				<td class="value" style="width: 90%"><input class="longInput" name="advertisementUrl" id="advertisementUrl" value="${sdkGameAdvertisementList.advertisementUrl}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
					<td align="center">
						<label class="Validform_label">
						是否显示
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="ifPush" <c:if test="${sdkGameAdvertisementList.ifshow==1}" >checked</c:if> >显示 &nbsp;
					<input type="radio"  value="0" name="ifPush" <c:if test="${sdkGameAdvertisementList.ifshow==0}" >checked</c:if>>隐藏 &nbsp;
					<span class="Validform_checktip"></span>
					</td>
				</tr>
		</table>	
		
			
		</t:formvalid>
 </body>