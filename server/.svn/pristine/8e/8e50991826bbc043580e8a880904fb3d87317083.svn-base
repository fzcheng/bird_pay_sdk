<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
  <script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
  <link id="easyuiTheme" rel="stylesheet" href="plug-in/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="plug-in/easyui/themes/icon.css" type="text/css"></link>
  <link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
  <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>  
  <script type="text/javascript" src="plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
  <script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
  <script type="text/javascript" src="plug-in/tools/curdtools.js"></script>
  <script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript" src="plug-in/tools/Map.js"></script>
	
	<!-- Demo stuff --<script src="plug-in/anythingslider/js/jquery.min.js"></script>
	<!-- Anything Slider -->
	<link rel="stylesheet" href="plug-in/anythingslider/css/anythingslider.css">
	<script src="plug-in/anythingslider/js/jquery.anythingslider.js"></script>

  <style>
  	#imgstyle {width: 200px; height: 80px;}
	#slider { width: 200px; height: 80px; }
	#cancle {
	    background: none repeat scroll 0 0 #808080;
	    color: #FFFFFF;
	    left: 120px;
	    position: relative;
	    text-decoration: none;
	    top: -25px;
	    font-size:12px;
	}
	#cancle_file{
	    background: none repeat scroll 0 0 #808080;
	    color: #FFFFFF;
	    left: -100px;
	    position: relative;
	    text-decoration: none;
	    top: -15px;
	    font-size:12px;
	}
	
	#modify {
	    background: none repeat scroll 0 0 #808080;
	    color: #FFFFFF;
	    font-size: 12px;
	    left: 20px;
	    position: relative;
	    text-decoration: none;
	    top: -25px;
	}
	#modify_file{
		background: none repeat scroll 0 0 #808080;
	    color: #FFFFFF;
	    font-size: 12px;
	    left: -170px;
	    position: relative;
	    text-decoration: none;
	    top: -15px;
	}
	img {width:100%;height: 100%}
	</style>

	<!-- AnythingSlider initialization -->
 </head>
 <body style="overflow-x:"auto";overflow-y: hidden" >
  <form id="formobj" action="exCgFormBuildController.do?saveOrUpdate" name="formobj" method="post">
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" name="tableName" value="${tableName?if_exists?html}" />
			 <input type="hidden" name="newOrUpdate" value="${newOrUpdate}" />
			${idHidden}	
			
			 
			<table cellpadding="0" cellspacing="1" class="formtable">
			   <#if idLabel !="" >
			   <tr>
			     <td align="right" style="width:<#if (columns?size>10)>100<#else>150</#if>px;">
						<label class="Validform_label">
							${idLabel}:
						</label>
				 </td>
				 <td class="value" 
				   <#if (columns?size>10)>
				     colspan="3"
				   </#if> 
				 >
				    ${idInput}				    
				 </td>
			   </tr>
			   </#if>
			   
			   <#list columns as po>
			    <#if (columns?size>10)>
					<#if po_index%2==0>
					<tr>
					</#if>
				<#else>
					<tr>
				</#if>
					<td align="right" style="width:<#if (columns?size>10)>100<#else>150</#if>px;">
						<label class="Validform_label">
							${po.content}:
						</label>
					</td>
					<td class="value">
						<#if po.show_type=='text'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: ${po.field_length}px;" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>
					               datatype="${po.field_valid_type?if_exists?html}"
					               <#else>
					               <#if po.type == 'int'>
					               datatype="n" 
					               <#elseif po.type=='double'>
					               datatype="/^(-?\d+)(\.\d+)?$/" 
					               <#else>
					               datatype="*" 
					               </#if>
					               </#if>>
						<#elseif po.show_type=='textarea'>
							<textarea id="${po.field_name}" name="${po.field_name}" 
							       style="width: ${po.field_length}px" class="inputxt" 
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>${data['${tableName}']['${po.field_name}']?if_exists?html}</textarea>
						<#elseif po.show_type=='password'>
							<input id="${po.field_name}" name="${po.field_name}"  type="password"
							       style="width: ${po.field_length}px" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						
						<#elseif po.show_type=='radio'>
					        <@DictData name="${po.dict_field?if_exists?html}" tablename="${po.dict_table?if_exists?html}" var="dataList">
								<#list dataList as dictdata> 
								<input value="${dictdata.typecode?if_exists?html}" name="${po.field_name}" type="radio"
								<#if dictdata.typecode=="${data['${tableName}']['${po.field_name}']?if_exists?html}"> checked="true" </#if>>
									${dictdata.typename?if_exists?html}
								</#list> 
							</@DictData>
					               
						<#elseif po.show_type=='checkbox'>
							<#assign checkboxstr>${data['${tableName}']['${po.field_name}']?if_exists?html}</#assign>
							<#assign checkboxlist=checkboxstr?split(",")>
							<@DictData name="${po.dict_field?if_exists?html}" tablename="${po.dict_table?if_exists?html}" var="dataList">
								<#list dataList as dictdata> 
								<input value="${dictdata.typecode?if_exists?html}" name="${po.field_name}" type="checkbox"
								<#list checkboxlist as x >
								<#if dictdata.typecode=="${x?if_exists?html}"> checked="true" </#if></#list>>
									${dictdata.typename?if_exists?html}
								</#list> 
							</@DictData>
					               
						<#elseif po.show_type=='list'>
							<@DictData formTable="${tableName?if_exists?html}"  name="${po.dict_field?if_exists?html}" label="${po.dict_field_label?if_exists?html}" tablename="${po.dict_table?if_exists?html}" var="dataList">
								<select id="${po.field_name}" name="${po.field_name}"
								style="width: ${po.field_length+5}px;"  
								>
									<#list dataList as dictdata> 
									<option value="${dictdata.typecode?if_exists?html}" 
									<#if dictdata.typecode=="${data['${tableName}']['${po.field_name}']?if_exists?html}"> selected="selected" </#if>>
										${dictdata.typename?if_exists?html}
									</option> 
									</#list> 
								</select>
							</@DictData>
							
						<!--	<input  id = "${po.field_name}file_path" name="file_path" style="width:60px" type="text"/>
							<script type="text/javascript">
							$(document).ready(function(){ 
								$('#${po.field_name}').change(function(){ 
									if ($('#${po.field_name}').val() == "file"){
										alert("haha");
										$("#${po.field_name}file_path").show();
									}else{
										$("#${po.field_name}file_path").hide();
									}
$
								})  
							});
								-->
							</script>
							
						<#elseif po.show_type=='date'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: ${po.field_length+4}px;"  value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
							       class="Wdate" onClick="WdatePicker()" 
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						
						<#elseif po.show_type=='datetime'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: ${po.field_length+4}px"  value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
							       class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						<#elseif po.show_type=='files'>
							<script>
								var flag = 0;
								var sliderLength = 0;
							</script>
							<input type="hidden" id="modify${po.field_name}" name="${po.field_name}" value="${data['${tableName}']['${po.field_name}']?if_exists?html}" />
									<#if "${data['${tableName}']['${po.field_name}']?if_exists}" != "">
										<!-- Demo stuff --
										<!-- Anything Slider -->
	
										<style>#${po.field_name}slider { width: 200px; height: 80px; padding-left:0px; } "${po.field_name}slider" { width: 200px; height: 80px;padding-left:0px;  }</style>
										<script>
											// DOM Ready
											$(function(){
												$('.${po.field_name}slider').anythingSlider({
													buildStartStop  : false,
													showMultiple        : 1,
													
												});
											});
											
										</script>
								<div>
									<ul id="${po.field_name}slider" class="${po.field_name}slider">
										<#list "${data['${tableName}']['${po.field_name}']?if_exists}"?split(",") as fileB>
										<#if ("${fileB}" != "")>
											<li>
												<div style="width: 100%; height: 100%;">
												<img id="imgstyle" onclick="viewFile('${tableName?if_exists?html}', '${po.field_name?if_exists?html}', '${fileB?if_exists?html}', 'exCgFormBuildController.do?openViewFile&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=${fileB?if_exists?html}')"  alt="${fileB?if_exists?html}" src="exCgFormBuildController.do?fileRequest&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=${fileB?if_exists?html}">
												<a id="modify" href="javascript:void(0)" class="jeecgDetail" onclick="modifyFile('exUploadController.do?modifyFile','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','${fileB?if_exists?html}',this)">修改</a>
												<a id="cancle" href="javascript:void(0)" class="jeecgDetail" onclick="deleteFile('exUploadController.do?delFile','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','${fileB?if_exists?html}',this)">删除</a>
												</div>
											</li>
										</#if>
										</#list>
									</ul>
								</div>
								<#else>
								<style>#${po.field_name}slider { width: 200px; height: 80px;padding-left:0px; } "${po.field_name}slider" { width: 200px; height: 80px;padding-left:0px; }</style>
									<div>
									 <ul id="${po.field_name}slider" class="${po.field_name}slider" style="display:none">
									 	 
									 </ul>
									</div>
								</#if>
			
								    <div class="form jeecgDetail">
										<script type="text/javascript">
										var serverMsg="";
										var m = new Map();
										$(function(){$('#${po.field_name}').uploadify(
											{buttonText:'添加文件',
											auto:true,
											progressData:'speed',
											multi:true,
											height:25,
											
											overrideEvents:['onDialogClose'],
											fileTypeDesc:'文件格式:',
											queueID:'filediv_${po.field_name}',
											fileTypeExts:'*.*;',
											fileSizeLimit:'15MB',swf:'plug-in/uploadify/uploadify.swf',	
											uploader:'exUploadController.do?saveFiles',
				
											onUploadStart : function(file) { 
												var cgFormId=$("input[name='${idKey}']").val();
												
												$('#${po.field_name}').uploadify("settings", "formData", {'newOrUpdate':'${newOrUpdate}','cgFormId':cgFormId,'cgFormName':'${tableName?if_exists?html}','cgFormField':'${po.field_name}'});} ,
											onQueueComplete : function(queueData) {
												 //var win = frameElement.api.opener;
												 //win.reloadTable();
												 //win.tip(serverMsg);
												 //frameElement.api.close();
												 
												 },
											onAllComplete : function(){
											
											
											},
											onUploadSuccess : function(file, data, response) {var d=$.parseJSON(data);if(d.success){var win = frameElement.api.opener;serverMsg = d.msg;  
																
																var newFilePath = d.attributes.path;
																var newLi = '<li><div style=\"width: 100%; height: 100%;\"><img id=\"imgstyle\" alt=\"\" src=\"exCgFormBuildController.do?fileRequest&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path='+newFilePath+"\"/>";
																var modifyUpload ="<a id=\"modify\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"modifyFile('exUploadController.do?modifyUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">修改</a>"
																
																var cancleUpload = "<a id=\"cancle\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"deleteFile('exUploadController.do?cancleUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">删除</a>"
																
																newLi = newLi + modifyUpload + cancleUpload + "</div></li>";
																
																if(flag == 0){
																	sliderLength = $('.${po.field_name}slider').children().length;
																	if(sliderLength != 0){
																		sliderLength = sliderLength - 2;
																	}
																	flag = 1;
																}
																
																$(".${po.field_name}slider").show();
																
																$(".${po.field_name}slider").append(newLi);
																
																
																
																$(".${po.field_name}slider").anythingSlider({
																	
																	buildStartStop  : false,
																	showMultiple        : 1,
																
																	
																});
																
																sliderLength = sliderLength + 1;
																
																$(".${po.field_name}slider").anythingSlider(sliderLength);
																 
											
											
											
											}},onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},onSelectError : function(file, errorCode, errorMsg){switch(errorCode) {case -100:tip("上传的文件数量已经超出系统限制的"+$('#${po.field_name}').uploadify('settings','queueSizeLimit')+"个文件！");break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#${po.field_name}').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
											onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});
										
											</script><span id="file_uploadspan"><input type="file" name="${po.field_name}" id="${po.field_name}" /></span>
									</div>
									<div class="form" id="filediv_${po.field_name}" style="width:200px"> </div>
						<#elseif po.show_type=='file'>
						<input type="hidden" id="modify${po.field_name}" name="${po.field_name}" value="${data['${tableName}']['${po.field_name}']?if_exists?html}" />
								<ul style="list-style-type:none;padding-left:0px;">
										<li>
											<div style="width: 100%; height: 100%;">
												<img id="imgstyle" class="${po.field_name}newImg"  alt="" src="" style="display:none"/>		
											</div>
										</li>
								</ul>
									
								<#if "${data['${tableName}']['${po.field_name}']?if_exists}" != "">
								<ul style="list-style-type:none;padding-left:0px;">
										<li>
											<div style="width: 100%; height: 100%;">
											<img id="imgstyle" onclick="viewFile('${tableName?if_exists?html}', '${po.field_name?if_exists?html}', '${data['${tableName}']['${po.field_name}']?if_exists}', 'exCgFormBuildController.do?openViewFile&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=${data['${tableName}']['${po.field_name}']?if_exists}')"  alt="${data['${tableName}']['${po.field_name}']?if_exists}" src="exCgFormBuildController.do?fileRequest&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=${data['${tableName}']['${po.field_name}']?if_exists}">
											<a id="modify_file" href="javascript:void(0)" class="jeecgDetail" onclick="modifyFile('exUploadController.do?modifyFile','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','${data['${tableName}']['${po.field_name}']?if_exists}',this)">修改</a>
											<a id="cancle_file" href="javascript:void(0)" class="jeecgDetail" onclick="deleteFile('exUploadController.do?delFile','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','${data['${tableName}']['${po.field_name}']?if_exists}',this)">删除</a>
										</div>
										</li>
								</ul>
								
								<div class="form jeecgDetail" id="${po.field_name}uploadButton" style="display:none">
										<script type="text/javascript">
										var serverMsg="";
										var m = new Map();
										$(function(){$('#${po.field_name}').uploadify(
											{buttonText:'添加文件',
											auto:true,
											progressData:'speed',
											multi:true,
											removeTimeout:1,
											height:25,
											overrideEvents:['onDialogClose'],
											fileTypeDesc:'文件格式:',
											queueID:'filediv_${po.field_name}',
											fileTypeExts:'*.*;',
											fileSizeLimit:'15MB',swf:'plug-in/uploadify/uploadify.swf',	
											uploader:'exUploadController.do?saveFiles',
											onUploadStart : function(file) { 
												var cgFormId=$("input[name='${idKey}']").val();
												$('#${po.field_name}').uploadify("settings", "formData", {'newOrUpdate':'${newOrUpdate}','cgFormId':cgFormId,'cgFormName':'${tableName?if_exists?html}','cgFormField':'${po.field_name}'});} ,
											onQueueComplete : function(queueData) {
												 //var win = frameElement.api.opener;
												 //win.reloadTable();
												 //win.tip(serverMsg);
												 //frameElement.api.close();
												 $("#${po.field_name}uploadButton").hide(); 
												 },
											onAllComplete : function(){
												 // $("input[type='button']").attr("disabled",false);
												 
												
											
											},
											onUploadSuccess : function(file, data, response) { var d=$.parseJSON(data);if(d.success){var win = frameElement.api.opener;serverMsg = d.msg; 
											
																var srcPath = "exCgFormBuildController.do?fileRequest&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=" + d.attributes.path;
																$(".${po.field_name}newImg").attr("src", srcPath);
																$(".${po.field_name}newImg").show(); 
																var newFilePath = d.attributes.path;
																var modifyUpload ="<a id=\"modify_file\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"modifyFile('exUploadController.do?modifyUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">修改</a>";
																
																var cancleUpload = "<a id=\"cancle_file\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"deleteFile('exUploadController.do?cancleUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">删除</a>";
																$(".${po.field_name}newImg").after(modifyUpload, cancleUpload);
																
																
																
																
																}
																
																},
																
																onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},onSelectError : function(file, errorCode, errorMsg){switch(errorCode) {case -100:tip("上传的文件数量已经超出系统限制的"+$('#${po.field_name}').uploadify('settings','queueSizeLimit')+"个文件！");break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#${po.field_name}').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
											onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});
										
											</script><span id="file_uploadspan"><input type="file" name="${po.field_name}" id="${po.field_name}" /></span>
									</div>
									<div class="form" id="filediv_${po.field_name}" style="width:200px"> </div>
								
								<#else>
									
								    <div class="form jeecgDetail" id="${po.field_name}uploadButton">
										<script type="text/javascript">
										var serverMsg="";
										var m = new Map();
										$(function(){$('#${po.field_name}').uploadify(
											{buttonText:'添加文件',
											auto:true,
											progressData:'speed',
											multi:true,
											removeTimeout:1,
											height:25,
											overrideEvents:['onDialogClose'],
											fileTypeDesc:'文件格式:',
											queueID:'filediv_${po.field_name}',
											fileTypeExts:'*.*;',
											fileSizeLimit:'15MB',swf:'plug-in/uploadify/uploadify.swf',	
											uploader:'exUploadController.do?saveFiles',
											onUploadStart : function(file) { 
												var cgFormId=$("input[name='${idKey}']").val();
												$('#${po.field_name}').uploadify("settings", "formData", {'newOrUpdate':'${newOrUpdate}','cgFormId':cgFormId,'cgFormName':'${tableName?if_exists?html}','cgFormField':'${po.field_name}'});} ,
											onQueueComplete : function(queueData) {
												 //var win = frameElement.api.opener;
												 //win.reloadTable();
												 //win.tip(serverMsg);
												 //frameElement.api.close();
												 $("#${po.field_name}uploadButton").hide(); 
												 },
											onAllComplete : function(){
												 // $("input[type='button']").attr("disabled",false);
												 
												
											
											},
											onUploadSuccess : function(file, data, response) { var d=$.parseJSON(data);if(d.success){var win = frameElement.api.opener;serverMsg = d.msg; 
											
																var srcPath = "exCgFormBuildController.do?fileRequest&tableName=${tableName?if_exists?html}&cgfield=${po.field_name?if_exists?html}&path=" + d.attributes.path;
																$(".${po.field_name}newImg").attr("src", srcPath);$(".${po.field_name}newImg").show();
																var newFilePath = d.attributes.path;
																var modifyUpload ="<a id=\"modify_file\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"modifyFile('exUploadController.do?modifyUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">修改</a>";
																var cancleUpload = "<a id=\"cancle_file\" href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"deleteFile('exUploadController.do?cancleUpload','${tableName?if_exists?html}', '${po.field_name?if_exists?html}','" + newFilePath + "',this)\">删除</a>";
																$(".${po.field_name}newImg").after(modifyUpload, cancleUpload);
																
																 }},
																
																onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},onSelectError : function(file, errorCode, errorMsg){switch(errorCode) {case -100:tip("上传的文件数量已经超出系统限制的"+$('#${po.field_name}').uploadify('settings','queueSizeLimit')+"个文件！");break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#${po.field_name}').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
											onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});
										
											</script><span id="file_uploadspan"><input type="file" name="${po.field_name}" id="${po.field_name}" /></span>
									</div>
									<div class="form" id="filediv_${po.field_name}" style="width:200px"> </div>
								</#if>
						<#else>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: ${po.field_length}px;" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>

						</#if>
						<span class="Validform_checktip"></span>
					</td>
				<#if (columns?size>10)>
					<#if (po_index%2!=0)||(!po_has_next)>
					</tr>
					</#if>
				<#else>
					</tr>
				</#if>
			  </#list>
			</table>
			<link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css"/>
			<link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css"/>
			<script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min.js"></script>
			<script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype.js"></script>
			<script type="text/javascript" src="plug-in/Validform/js/datatype.js"></script>
			<SCRIPT type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js">
			</SCRIPT><script type="text/javascript">
			 	
				$(function(){
			
					$("#formobj").Validform({
						tiptype:4,
						btnSubmit:"#btn_sub",
						btnReset:"#btn_reset",
						ajaxPost:true,
						usePlugin:{
							passwordstrength:{
								minLen:6,
								maxLen:18,
								trigger:function(obj,error){
									if(error){
										obj.parent().next().find(".Validform_checktip").show();
										obj.find(".passwordStrength").hide();
									}else{
										$(".passwordStrength").show();
										obj.parent().next().find(".Validform_checktip").hide();
									}
								}
							}
						},
						callback:function(data){
							
							var win = frameElement.api.opener;
							if(data.success==true){
								uploadFile(data);
							}else{
								if(data.responseText==''||data.responseText==undefined){
									$("#formobj").html(data.msg);
								}else{
									$("#formobj").html(data.responseText);
								} 
								return false;
							}
							win.reloadTable();
						}
					});
				});
			</script>
	</form>
	<form id="testForm" style="visibility:hidden" method="POST" ENCTYPE="multipart/form-data" action="">
		<input id = "test" type = "file"  name = "file" onchange="updateFileSubmit()" />
	</form>
	
	<script type="text/javascript">
	   $(function(){
	    //查看模式情况下,删除和上传附件功能禁止使用
		if(location.href.indexOf("load=detail")!=-1){
			$(".jeecgDetail").hide();
		}
	   });
	   
	   function viewFile(tableName, cgfield, path , url){
	   
	   		var extend = path.substr(path.lastIndexOf(".")+1).toLowerCase();
	   		var imgs = new Array("png","gif","bmp","dib", "jfif","jpe", "jpeg" ,"jpg" , "ico","tif" , "tiff");
	   		var i = 0; 
	   		var flag = "false";
	   		for(;i < imgs.length; i++){
	   			if(imgs[i] == extend){
	   				flag = "true";
	   			}
	   		}	
	   		if(flag == "true"){
	   			$.dialog.setting.zIndex =2999;
	   			openwindow("预览", url, "hha", 500,400);
	   		}else{
	   			 window.open(url);
	   			 return false;
	   		
	   		}
	   
	   }
	  
	  function upload() {
	  	<#list columns as po>
	  		<#if po.show_type=='file'>
	  		$('#${po.field_name}').uploadify('upload', '*');		
	  		</#if>
	  	</#list>
	  }
	  function cancel() {
	  	
	  	<#list columns as po>
	  		<#if po.show_type=='file'>
	 	 $('#${po.field_name}').uploadify('cancel', '*');
	 	 	</#if>
	  	</#list>
	  	
	  }
	 
	  var updateUrl = "";
	  var updateObj = "";
	  var updateField = "";
	  var tableName = "";
	  function modifyFile(url,tableNames, field, path, obj){
	  	  
	      var file = document.getElementById("test");
	      file.click();
	      var cgField =field;
		  var id = document.getElementById("row_id").value;
		  updateUrl = url + "&tableName="+ tableNames +"&cgField="+cgField+"&path="+path+"&id="+id; 
	      updateObj = obj;
	      updateField = field;
	      tableName = tableNames;
	  }
	  
	  function ajaxUpdateFile(){
	  $("input[type='button']").attr("disabled",true);
	  		var option={
					type : 'POST',
					url : updateUrl,// 请求的action路径
					error : function() {// 请求失败处理函数
						alert("haha");
					},
					success : function(data) {
						var d = $.parseJSON(data);
						
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							var srcStr = "";
							var tmp = "exCgFormBuildController.do?openViewFile&path=" + d.attributes.path; 
							var onclickFun = 'viewFile("","","'+  d.attributes.path +'","'+ tmp + '")';
							
							if(d.attributes.isNewOrUpdate != null && d.attributes.isNewOrUpdate!=""){
								var srcPath = "exCgFormBuildController.do?fileRequest&tableName="+tableName + "&cgfield=" + updateField +"&path=" + d.attributes.path;
								$(updateObj).prev().attr("src", srcPath);
							}else{
							
								$(updateObj).prev().attr("src",d.attributes.path);
							}
							$(updateObj).prev().attr("alt","");
							$(updateObj).prev().attr("onclick", onclickFun);
							$('.'+updateField+'slider').anythingSlider({
												buildStartStop  : false,
												showMultiple        : 1,
											});
						}
						$("input[type='button']").attr("disabled",false);
					}
				};
				
	      	$("#testForm").ajaxSubmit(option);
		}
	      
	  function updateFileSubmit(obj){
	  	  $.dialog.setting.zIndex =1990;
	      $.dialog.confirm("确认修改该条记录?", function(){ ajaxUpdateFile(); }, function(){});
	     

	  }
	  
	  function uploadFile(data){
	  		if(!$("input[name='id']").val()){
	  			$("input[name='id']").val(data.obj.id);
	  		}
	  		if($(".uploadify-queue-item").length>0){
	  			upload();
	  		}else{
	  			var win = frameElement.api.opener;
				win.reloadTable();
				win.tip(data.msg);
				frameElement.api.close();
	  		}
	  	}
		function deleteFile(url,tableName, field, path, obj){
		
		   var cgField =field;
		   var id = document.getElementById("row_id").value;
		   url = url + "&tableName="+ tableName +"&cgField="+cgField+"&path="+path+"&id="+id; 
		   $.dialog.setting.zIndex =1999;
			$.dialog.confirm("确认删除该条记录?", function(){
			  	$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					url : url,// 请求的action路径
					error : function() {// 请求失败处理函数
					},
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							$(obj).prev().prev().remove();
							$(obj).closest("ul").next("div").show();
							$(obj).closest("li").remove();
							
							$('.'+field+'slider').anythingSlider({
												buildStartStop  : false,
												showMultiple        : 1,
											});
						}
					}
				});  
			}, function(){
		});
	}
	</script>
	<script type="text/javascript">${js_plug_in?if_exists}</script>		
 </body>
</html>