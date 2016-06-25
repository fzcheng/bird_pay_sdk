<!DOCTYPE html>
<html>
 <head>
  <title>订单信息</title>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script><script type="text/javascript" src="plug-in/tools/dataformat.js"></script><link id="easyuiTheme" rel="stylesheet" href="plug-in/easyui/themes/default/easyui.css" type="text/css"></link><link rel="stylesheet" href="plug-in/easyui/themes/icon.css" type="text/css"></link><link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css"><script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script><script type="text/javascript" src="plug-in/easyui/locale/easyui-lang-zh_CN.js"></script><script type="text/javascript" src="plug-in/tools/syUtil.js"></script><script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script><script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script><script type="text/javascript" src="plug-in/tools/curdtools.js"></script><script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link><script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script><script type="text/javascript" src="plug-in/tools/Map.js"></script>
 </head>
 <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}
	$(function(){
		if($("#cgr_sql") != null){
			
			var  value = $("#cgr_sql").val();
			value = value.replace(/''/g, "'");
			$("#cgr_sql").val(value);
		}
	});
 </script>
 <body>
  <form id="formobj" action="exCgFormBuildController.do?saveOrUpdateMore" name="formobj" method="post"><input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" name="tableName" value="${tableName?if_exists?html}" >
			${idHidden}
			<input type="hidden" name="newOrUpdate" value="${newOrUpdate}" />
			<table  cellpadding="0" cellspacing="1" class="formtable">
				<#list columns as po>
				<#if po_index%2==0>
				<tr>
				</#if>
					<td align="right">
						<label class="Validform_label">
							${po.content}:
						</label>
					</td>
					<td class="value">
						<#if po.show_type=='text'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: 150px" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
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
					               </#if></#if>>
						
						<#elseif po.show_type=='password'>
							<input id="${po.field_name}" name="${po.field_name}"  type="password"
							       style="width: 150px" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
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
							<@DictData name="${po.dict_field?if_exists?html}" tablename="${po.dict_table?if_exists?html}" var="dataList">
								<select id="${po.field_name}" name="${po.field_name}" >
									<#list dataList as dictdata> 
									<option value="${dictdata.typecode?if_exists?html}" 
									<#if dictdata.typecode=="${data['${tableName}']['${po.field_name}']?if_exists?html}"> selected="selected" </#if>>
										${dictdata.typename?if_exists?html}
									</option> 
									</#list> 
								</select>
							</@DictData>
						<#elseif po.show_type=='textarea'>
							<textarea id="${po.field_name}" name="${po.field_name}" 
							       style="width: 150px" class="inputxt" 
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>${data['${tableName}']['${po.field_name}']?if_exists?html}</textarea>
					               	
						<#elseif po.show_type=='date'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: 150px"  value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
							       class="Wdate" onClick="WdatePicker()" 
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						
						<#elseif po.show_type=='datetime'>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: 150px"  value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
							       class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						
						<#elseif po.show_type=='file'>
							<table>
									<#list filesList as fileB>
										<#if fileB['field'] == po.field_name>
										<tr style="height:34px;">
										<td>${fileB['title']}</td>
										<td><a href="commonController.do?viewFile&fileid=${fileB['fileKey']}&subclassname=jeecg.cgform.entity.upload.CgUploadEntity" title="下载">下载</a></td>
										<td><a href="javascript:void(0);" onclick="openwindow('预览','commonController.do?openViewFile&fileid=${fileB['fileKey']}&subclassname=jeecg.cgform.entity.upload.CgUploadEntity','fList','800','700')">预览</a></td>
										<td><a href="javascript:void(0)" class="jeecgDetail" onclick="del('cgUploadController.do?delFile&id=${fileB['fileKey']}',this)">删除</a></td>
										</tr>
										</#if>
									</#list>
								</table>
							    <div class="form jeecgDetail">
									<script type="text/javascript">
									var serverMsg="";
									var m = new Map();
									$(function(){$('#${po.field_name}').uploadify(
										{buttonText:'添加文件',
										auto:false,
										progressData:'speed',
										multi:true,
										height:25,
										overrideEvents:['onDialogClose'],
										fileTypeDesc:'文件格式:',
										queueID:'filediv_${po.field_name}',
										fileTypeExts:'*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.pdf;',
										fileSizeLimit:'15MB',swf:'plug-in/uploadify/uploadify.swf',	
										uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
										onUploadStart : function(file) { 
											var cgFormId=$("input[name='id']").val();
											$('#${po.field_name}').uploadify("settings", "formData", {'cgFormId':cgFormId,'cgFormName':'${tableName?if_exists?html}','cgFormField':'${po.field_name}'});} ,
										onQueueComplete : function(queueData) {
											 var win = frameElement.api.opener;
											 win.reloadTable();
											 win.tip(serverMsg);
											 frameElement.api.close();},
										onUploadSuccess : function(file, data, response) {var d=$.parseJSON(data);if(d.success){var win = frameElement.api.opener;serverMsg = d.msg;}},onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},onSelectError : function(file, errorCode, errorMsg){switch(errorCode) {case -100:tip("上传的文件数量已经超出系统限制的"+$('#${po.field_name}').uploadify('settings','queueSizeLimit')+"个文件！");break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#${po.field_name}').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
										onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});
									
										</script><span id="file_uploadspan"><input type="file" name="${po.field_name}" id="${po.field_name}" /></span>
								</div>
								<div class="form" id="filediv_${po.field_name}"> </div>
						<#else>
							<input id="${po.field_name}" name="${po.field_name}" type="text"
							       style="width: 150px" class="inputxt" value="${data['${tableName}']['${po.field_name}']?if_exists?html}"
					               <#if po.is_null == 'Y'>ignore="ignore"</#if>
					               <#if po.field_valid_type?if_exists?html != ''>datatype="${po.field_valid_type?if_exists?html}"</#if>>
						</#if>
						<span class="Validform_checktip"></span>
					</td>
				<#if (po_index%2!=0)||(!po_has_next)>
				</tr>
				</#if>
			  </#list>
			</table>
<script type="text/javascript">
   $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
   });
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
	$.dialog.setting.zIndex =1990;
	function del(url,obj){
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
						$(obj).closest("tr").hide("slow");
					}
				}
			});  
		}, function(){
	});
}
</script>
			<div style="width: auto;height: 200px;">
				<div style="width:690px;height:1px;"></div>
				<div id="tt" tabPosition="top" border=flase style="margin:0px;padding:0px;overflow:hidden;width:auto;" class="easyui-tabs" fit="false">
				<#assign subTableStr>${head.subTableStr?if_exists?html}</#assign>
				<#assign subtablelist=subTableStr?split(",")>
				<#list subtablelist as sub >
				    <#if field['${sub}']?exists >
					    <div title="${field['${sub}'].head.content?if_exists?html}" style="margin:0px;padding:0px;overflow:hidden;">
							<script type="text/javascript"> 
								$('#addBtn_${sub}').linkbutton({   
								    iconCls: 'icon-add'  
								});  
								$('#delBtn_${sub}').linkbutton({   
								    iconCls: 'icon-remove'  
								}); 
								$('#addBtn_${sub}').bind('click', function(){   
							 		 var tr =  $("#add_${sub}_table_template tr").clone();
								 	 $("#add_${sub}_table").append(tr);
								 	 resetTrNum('add_${sub}_table');
							    });  
								$('#delBtn_${sub}').bind('click', function(){   
							       $("#add_${sub}_table").find("input:checked").parent().parent().remove();   
							        resetTrNum('add_${sub}_table');
							    });
							    $(document).ready(function(){
							    	$(".datagrid-toolbar").parent().css("width","auto");
							    });
							</script>
							<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
								<a id="addBtn_${sub}" href="#">添加</a> <a id="delBtn_${sub}" href="#">删除</a> 
							</div>
							<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
							<table border="0" cellpadding="2" cellspacing="0" id="${sub}_table">
							<tr bgcolor="#E6E6E6">
								<td align="center" bgcolor="#EEEEEE">序号</td>
							<#list field['${sub}'].fieldList as subTableField >
								<td align="left" bgcolor="#EEEEEE">${subTableField.content?if_exists?html}</td>
							</#list>
							</tr>
							<tbody id="add_${sub}_table">
								<#if data['${sub}']?exists&&(data['${sub}']?size>0) >
								<#list data['${sub}'] as subTableData >
									<tr>
									<td align="center">
										<input style="width:20px;" type="checkbox" name="ck"/>
										<input type="hidden" name="${sub}[${subTableData_index}].id" id="${sub}[${subTableData_index}].id" value="${subTableData['id']?if_exists?html}"/>
									</td>
									<#list field['${sub}'].fieldList as subTableField >
									<td align="left">
									<#if subTableField.show_type=='text'>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt" value="${subTableData['${subTableField.field_name}']?if_exists?html}"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>
								               datatype="${subTableField.field_valid_type?if_exists?html}"
								               <#else>
								               <#if subTableField.type == 'int'>
								               datatype="n" 
								               <#elseif subTableField.type=='double'>
								               datatype="/^(-?\d+)(\.\d+)?$/"
								                <#else>
					               				datatype="*"  
								               </#if></#if>>
									
									<#elseif subTableField.show_type=='password'>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}"  type="password"
										       style="width: 150px" class="inputxt" value="${subTableData['${subTableField.field_name}']?if_exists?html}"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='radio'>
								        <@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<#list dataList as dictdata> 
											<input value="${dictdata.typecode?if_exists?html}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="radio"
											<#if dictdata.typecode=="${subTableData['${subTableField.field_name}']?if_exists?html}"> checked="true" </#if>>
												${dictdata.typename?if_exists?html}
											</#list> 
										</@DictData>
								               
									<#elseif subTableField.show_type=='checkbox'>
										<#assign checkboxstr>${subTableData['${subTableField.field_name}']?if_exists?html}</#assign>
										<#assign checkboxlist=checkboxstr?split(",")>
										<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<#list dataList as dictdata> 
											<input value="${dictdata.typecode?if_exists?html}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="checkbox"
											<#list checkboxlist as x >
											<#if dictdata.typecode=="${x?if_exists?html}"> checked="true" </#if></#list>>
												${dictdata.typename?if_exists?html}
											</#list> 
										</@DictData>
								               
									<#elseif subTableField.show_type=='list'>
										<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<select id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" >
												<#list dataList as dictdata> 
												<option value="${dictdata.typecode?if_exists?html}" 
												<#if dictdata.typecode=="${subTableData['${subTableField.field_name}']?if_exists?html}"> selected="selected" </#if>>
													${dictdata.typename?if_exists?html}
												</option> 
												</#list> 
											</select>
										</@DictData>
										
									<#elseif subTableField.show_type=='date'>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="text"
										       style="width: 150px"  value="${subTableData['${subTableField.field_name}']?if_exists?html}"
										       class="Wdate" onClick="WdatePicker()" 
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='datetime'>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="text"
										       style="width: 150px"  value="${subTableData['${subTableField.field_name}']?if_exists?html}"
										       class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='file'>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt" value="${subTableData['${subTableField.field_name}']?if_exists?html}"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
								               
									<#else>
										<input id="${sub}[${subTableData_index}].${subTableField.field_name}" name="${sub}[${subTableData_index}].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt" value="${subTableData['${subTableField.field_name}']?if_exists?html}"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									</#if>
									</td>
									</#list>
									</tr>
								</#list>
								<#else>
									<tr>
									<td align="center">
									<input style="width:20px;" type="checkbox" name="ck"/>
									<input type="hidden" name="${sub}[0].id" id="${sub}[0].id" />
									</td>
									<#list field['${sub}'].fieldList as subTableField >
									<td align="left">
									<#if subTableField.show_type=='text'>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>
								               datatype="${subTableField.field_valid_type?if_exists?html}"
								               <#else>
								               <#if subTableField.type == 'int'>
								               datatype="n" 
								               <#elseif subTableField.type=='double'>
								               datatype="/^(-?\d+)(\.\d+)?$/" 
								                <#else>
					               				datatype="*" 
								               </#if></#if>>
									
									<#elseif subTableField.show_type=='password'>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}"  type="password"
										       style="width: 150px" class="inputxt" 
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='radio'>
								        <@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<#list dataList as dictdata> 
											<input value="${dictdata.typecode?if_exists?html}" name="${sub}[0].${subTableField.field_name}" type="radio">
												${dictdata.typename?if_exists?html}
											</#list> 
										</@DictData>
								               
									<#elseif subTableField.show_type=='checkbox'>
										<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<#list dataList as dictdata> 
											<input value="${dictdata.typecode?if_exists?html}" name="${sub}[0].${subTableField.field_name}" type="checkbox">
												${dictdata.typename?if_exists?html}
											</#list> 
										</@DictData>
								               
									<#elseif subTableField.show_type=='list'>
										<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
											<select id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" >
												<#list dataList as dictdata> 
												<option value="${dictdata.typecode?if_exists?html}" >
													${dictdata.typename?if_exists?html}
												</option> 
												</#list> 
											</select>
										</@DictData>
										
									<#elseif subTableField.show_type=='date'>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" type="text"
										       style="width: 150px"  
										       class="Wdate" onClick="WdatePicker()" 
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='datetime'>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" type="text"
										       style="width: 150px"  
										       class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									
									<#elseif subTableField.show_type=='file'>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt" 
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
								               
									<#else>
										<input id="${sub}[0].${subTableField.field_name}" name="${sub}[0].${subTableField.field_name}" type="text"
										       style="width: 150px" class="inputxt" 
								               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
								               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
									</#if>
									</td>
									</#list>
									</tr>
								</#if>
							</tbody>
							</table>
							</div>
						</div>
					</#if>
				</#list>
				</div>
			</div>
		<link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css"/><link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css"/><script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min.js"></script><script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype.js"></script><script type="text/javascript" src="plug-in/Validform/js/datatype.js"></script><SCRIPT type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></SCRIPT><script type="text/javascript">$(function(){$("#formobj").Validform({tiptype:1,btnSubmit:"#btn_sub",btnReset:"#btn_reset",ajaxPost:true,usePlugin:{passwordstrength:{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().find(".Validform_checktip").show();obj.find(".passwordStrength").hide();}else{$(".passwordStrength").show();obj.parent().next().find(".Validform_checktip").hide();}}}},callback:function(data){var win = frameElement.api.opener;if(data.success==true){uploadFile(data);}else{if(data.responseText==''||data.responseText==undefined)$("#formobj").html(data.msg);else $("#formobj").html(data.responseText); return false;}win.reloadTable();}});});</script></form>
		<!-- 添加 产品明细 模版 -->
		<table style="display:none">
		<#assign subTableStr>${head.subTableStr?if_exists?html}</#assign>
		<#assign subtablelist=subTableStr?split(",")>
		<#list subtablelist as sub >
		    <#if field['${sub}']?exists >
			<tbody id="add_${sub}_table_template">
				<tr>
					<td align="center">
					<input style="width:20px;" type="checkbox" name="ck"/>
					<input type="hidden" name="${sub}[#index#].id" id="${sub}[#index#].id" />
					</td>
					<#list field['${sub}'].fieldList as subTableField >
					<td align="left">
					<#if subTableField.show_type=='text'>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" type="text"
						       style="width: 150px" class="inputxt"
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>
				               datatype="${subTableField.field_valid_type?if_exists?html}"
				               <#else>
				               <#if subTableField.type == 'int'>
				               datatype="n" 
				               <#elseif subTableField.type=='double'>
				               datatype="/^(-?\d+)(\.\d+)?$/" 
				               <#else>
					           datatype="*" 
				               </#if></#if>>
					
					<#elseif subTableField.show_type=='password'>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}"  type="password"
						       style="width: 150px" class="inputxt" 
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
					
					<#elseif subTableField.show_type=='radio'>
				        <@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
							<#list dataList as dictdata> 
							<input value="${dictdata.typecode?if_exists?html}" name="${sub}[#index#].${subTableField.field_name}" type="radio">
								${dictdata.typename?if_exists?html}
							</#list> 
						</@DictData>
				               
					<#elseif subTableField.show_type=='checkbox'>
						<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
							<#list dataList as dictdata> 
							<input value="${dictdata.typecode?if_exists?html}" name="${sub}[#index#].${subTableField.field_name}" type="checkbox">
								${dictdata.typename?if_exists?html}
							</#list> 
						</@DictData>
				               
					<#elseif subTableField.show_type=='list'>
						<@DictData name="${subTableField.dict_field?if_exists?html}" tablename="${subTableField.dict_table?if_exists?html}" var="dataList">
							<select id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" >
								<#list dataList as dictdata> 
								<option value="${dictdata.typecode?if_exists?html}" >
									${dictdata.typename?if_exists?html}
								</option> 
								</#list> 
							</select>
						</@DictData>
						
					<#elseif subTableField.show_type=='date'>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" type="text"
						       style="width: 150px"  
						       class="Wdate" onClick="WdatePicker()" 
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
					
					<#elseif subTableField.show_type=='datetime'>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" type="text"
						       style="width: 150px"  
						       class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
					
					<#elseif subTableField.show_type=='file'>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" type="text"
						       style="width: 150px" class="inputxt" 
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
				               
					<#else>
						<input id="${sub}[#index#].${subTableField.field_name}" name="${sub}[#index#].${subTableField.field_name}" type="text"
						       style="width: 150px" class="inputxt" 
				               <#if subTableField.is_null == 'Y'>ignore="ignore"</#if>
				               <#if subTableField.field_valid_type?if_exists?html != ''>datatype="${subTableField.field_valid_type?if_exists?html}"</#if>>
					</#if>
					</td>
					</#list>
					</tr>
			 </tbody>
		</#if>
		</#list>
		</table>
	<script type="text/javascript">${js_plug_in?if_exists}</script>	
 </body>
 </html>