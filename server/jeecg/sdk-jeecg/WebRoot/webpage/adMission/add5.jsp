<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>增加</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>

<script type="text/javascript">

	function uploadTaxRegImgCallBack(url,name){
		alert("进来了！");
	    $("#taxRegImgView").attr('src',url);
	    $("#taxRegImg").val(url);
	}
	
  	function uploadFile(data){
  		alert("进来了！");
  		if($(".uploadify-queue-item").length>0){
  			alert("进来了if！");
  			upload();
  		}else{
  			alert("进来了else！");
  	  		if($("#id").val()!=""){
  	  			alert("进来了url！");
//  	  	      var id = $("#id").val();
//  	  	      var iconName = $("#iconName").val();
//  	  	 	  var iconType = $("#iconType").val();
//  	  	 	  var url = encodeURI("adMissionController.do?update&id="+id+"&iconName="+iconName+"&iconType="+iconType);
				  var url = encodeURI("adMissionController.do?update);
  	  	 	  submitForm(url);
  	  		}else{
			frameElement.api.opener.reloadTable();
			frameElement.api.close();
  	  		}
  		}
  	}
  	
  	function close(){
  		alert("进来了close！");
  		frameElement.api.close();
  	}
  	function submitForm(url){
  		alert("进来了submitForm！");
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
					frameElement.api.opener.tip(msg);
					frameElement.api.opener.reloadTable();
					frameElement.api.close();
				}
			}
		});
  	}

  	
</script>
  
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" callback="@Override uploadFile" action="adMission.do?update">
    <input id="id" name="id" type="hidden" value="${adMission.id}">
    <input id="adverId" name="adverId" type="hidden" value="${adId}">
    <input id="showType" name="showType" type="hidden" value="${showType}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
				插屏广告视频:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="showImg" id="showImg"  value="${adMission.showImg}" datatype="s1-256" >
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
				视频链接:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <input class="inputxt"   name="showImgSrc" id="showImgSrc"  value="${adMission.showImgSrc}" datatype="s1-256" >
			</td>
		</tr>
		<input id="taxRegImgUpload" type="button" value="上传" onclick="commonUpload(uploadTaxRegImgCallBack);"/>
		<div class="form" id="filediv">
   		</div>
		<div class="form">
	     <t:upload name="file_upload" uploader="adMissionController.do?saveOrUpdateIcon" extend="*.png;" id="file_upload" formData="id,iconName,iconType"></t:upload>
	    </div>
    </table>
  </t:formvalid>
</body>