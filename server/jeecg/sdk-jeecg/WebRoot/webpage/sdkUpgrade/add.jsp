<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>增加</title>
<t:base type="jquery,easyui,tools"></t:base>  
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <script type="text/javascript">
 function ajaxLoading(){  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
 }   
 function ajaxLoadEnd(){  
     $(".datagrid-mask").remove();  
     $(".datagrid-mask-msg").remove();              
}
 </script>
  <t:formvalid formid="formobj" layout="div" dialog="true"  beforeSubmit="upload" action="sdkUpgrade.do?save">
			<input type="hidden"  value="${sdkUpgrade.upgradeId}" id="upgradeId" name="upgradeId"/>
			<input type="hidden"  value="${sdkUpgrade.downUrl}" id="downUrl" name="downUrl"/>
			<input type="hidden"  value="${sdkUpgrade.versionCode}" id="versionCode" name="versionCode"/>
			<input type="hidden"  value="" id="errorMessage" name="errorMessage"/>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
	     <td align="right" height="60">
	     				<label class="Validform_label">
						文件(注意: apk格式)：
						</label>

	     </td>
	     <td class="value" style="width:400px;">

    
	       <fieldset >		    
		     <div class="form" style="width:400px;">
		     <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
		     <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
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
		     					multi:false,
		     					height:25,
		     					queueSizeLimit:30,
		     					overrideEvents:['onDialogClose'],
		     					fileTypeDesc:'文件格式:',
		     					queueID:'filediv',
		     					fileTypeExts:'*.*',
		     					fileSizeLimit:'1000MB',
		     					swf:'plug-in/uploadify/uploadify.swf',	
		     					uploader:'sdkUpgrade.do?saveFiles',
		     					onUploadStart : function(file) { 
		     							ajaxLoading();
		     							var id=$('#upgradeId').val();
		     							$('#file_upload').uploadify("settings", "formData", {'id':id});
		     					} ,
		     					onQueueComplete : function(queueData) { 
		     					
		     						if($("#errorMessage").val()!=""){
		     							ajaxLoadEnd();
		     							return false;
		     						}
		     						 $.post("sdkUpgrade.do?save", $("#formobj").serialize())
		     						    .success(function() { 
			     						    //alert("dsd");
			     						    ajaxLoadEnd();
			     						    var win = frameElement.api.opener;
		     								win.reloadTable();
		     								win.tip(serverMsg);
		     								frameElement.api.close()
		     						    })
		     						    .error(function() { 
		     						    	alert("error"); 
		     						    })
		     						    .complete(function() { 
		     						    	alert("complete"); 
		     						    }); 
		     					},
		     					onUploadSuccess : function(file, data, response) {
		     						var d=$.parseJSON(data);
		     						if(d.success){	
		     							if(d.attributes.apkfile!=null){
		     								//alert(d.attributes.apkfile);
		     								$("#downUrl").val(d.attributes.apkfile);	
		     							}
		     							if(d.attributes.versionCode!=null){
		     								//alert(d.attributes.versionCode);
		     								$("#versionCode").val(d.attributes.versionCode);	
		     							}     							 
		     							serverMsg = d.msg;
		     						}else{
		     							serverMsg = d.msg;
		     							$("#errorMessage").val(serverMsg);	
		     							var win = frameElement.api.opener;
		     							win.reloadTable();
		     							win.tip(serverMsg);
		     							frameElement.api.close()
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
		     			var uploadCount=$('#filediv').find("div[class=uploadify-queue-item]").length;
		     			if(uploadCount>0){
		     				$('#file_upload').uploadify('upload', '*');	
		     			}else{
		     				flag=true;
		     			}
		     			//$('#file_upload').uploadify('upload', '*');	
		     			return flag;
		     		}
		     		
		     		function cancel() {
		     			alert(flag);
		     			$('#file_upload').uploadify('cancel');
		     		}
		     		</script>
		     		<span id="file_uploadspan"><input type="file" name="files" id="file_upload" /></span>
		    </div>
		    <div class="form" id="filediv" style="height:100px;width:400px;">
		    </div>
		   </fieldset>    
    
	     </td>
	    </tr> 
				<tr>
					<td align="right">
						<label class="Validform_label">
						请选择升级类型:
						</label>
					</td>
					<td class="value">
					<input type="radio"  name="forceTag" value="0"  <c:if test="${sdkUpgrade.forceTag==0}">checked</c:if>>不升级 &nbsp;
					 <input type="radio"   name="forceTag" value="1" <c:if test="${sdkUpgrade.forceTag==1}">checked</c:if> >建议升级 &nbsp;
					 <input type="radio"   name="forceTag" value="2" <c:if test="${sdkUpgrade.forceTag==2}">checked</c:if> >强制升级 &nbsp;
	      			<span class="Validform_checktip"></span>
						 
					</td>
				</tr>		 
			</table>
		</t:formvalid>
 </body>