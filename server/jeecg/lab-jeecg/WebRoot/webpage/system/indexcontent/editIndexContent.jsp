<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <!DOCTYPE html>
<html>
 <head>
<!--  <t:base type="jquery,easyui,tools"></t:base>--> 
	<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
	<style TYPE="text/css" > 
    <!--
	* { margin: 0; padding: 0; }
	body {font-family: Verdana, Arial; font-size: 12px; line-height: 18px; }
	a { text-decoration: none; }
	.container{margin: 20px auto; width: 900px; background: #fff;}
	h3 { margin-bottom: 15px; font-size: 22px; text-shadow: 2px 2px 2px #ccc; }
	
	#contactform {
	
	width: 80%;
	padding: 20px;
	background: #f0f0f0;
	overflow:auto;
	
	border: 1px solid #cccccc;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;	
	
	-moz-box-shadow: 2px 2px 2px #cccccc;
	-webkit-box-shadow: 2px 2px 2px #cccccc;
	box-shadow: 2px 2px 2px #cccccc;
	
	}
	
	.field{margin-bottom:7px;}
	
	label {
	font-family: Arial, Verdana; 
	text-shadow: 2px 2px 2px #ccc;
	display: block; 
	float: left; 
	font-weight: bold; 
	margin-right:10px; 
	text-align: right; 
	width: 120px; 
	line-height: 25px; 
	font-size: 12px; 
	}
	
	.input{
	font-family: Arial, Verdana; 
	font-size: 12px; 
	padding: 5px; 
	border: 1px solid #b9bdc1; 
	width: 300px; 
	color: #797979;	
	}
	
	.input:focus{
	background-color:#E7E8E7;	
	}
	
	.textarea {
	height:150px;	
	}
	
	.hint{
	display:none;
	}
	
	.field:hover .hint {  
	position: absolute;
	display: block;  
	margin: -30px 0 0 455px;
	color: #FFFFFF;
	padding: 7px 10px;
	background: rgba(0, 0, 0, 0.6);
	
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;	
	}
	
	.button{
	float: right;
	margin:10px 55px 10px 0;
	font-weight: bold;
	line-height: 1;
	padding: 6px 10px;
	cursor:pointer;   
	color: #fff;
	background-image:images/linkbutton_bg.png
	
	text-align: center;
	text-shadow: 0 -1px 1px #64799e;
	
	/* Background gradient */
	background: #a5b8da;
	background: -moz-linear-gradient(top, #a5b8da 0%, #7089b3 100%);
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#a5b8da), to(#7089b3));
	
	/* Border style */
  	border: 1px solid #5c6f91;  
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
  
	/* Box shadow */
	-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
	-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
	box-shadow: inset 0 1px 0 0 #aec3e5;
	
	}
	
	.button:hover {
	background: #848FB2;
    cursor: pointer;
	}
    -->
   </style>
 </head>
<form id="contactform" class="rounded" action="indexContentController.do?saveFiles" method="post" enctype="multipart/form-data">

  <input type="hidden" name="default" value="${default}">
<div class="field">
	<label for="name">版本 :</label>
  	<input type="text" class="input" name="version" id="name"  value="${version}"/>
</div>
<div class="field">
	<label for="name">logo:</label>
  	<img src="indexContentController.do?getLogo&default=${default }" class="input"/>
</div>

<div class="field">
	<label for="email">上传logo:</label>
  	<input type="file" class="input" name="file" id="file" />
</div>


<div class="field">
  	<textarea class="input textarea" name="content" id="editor1">${content}</textarea>
</div>
<input type="submit" name="Submit"  class="button" value="更新" />
</form>
</form>
 
 <script type="text/javascript">
 	var editor = document.getElementById("editor1");
 	CKEDITOR.editorConfig = function( config ) {
 		// Define changes to default configuration here. For example:
 		config.language = 'zh-cn';
 		config.uiColor = '#ffffff';
 		config.fullPage= true;
 		config.allowedContent= true;
 		
 	};
 	CKEDITOR.replace(editor, {removeDialogTabs:'image:advanced;flash:advanced;image:Link',filebrowserBrowseUrl : '',uiColor:'b6b6b6',filebrowserImageBrowseLinkUrl:'',filebrowserImageBrowseUrl:'', filebrowserUploadUrl:'indexContentController.do?saveContentImage'});
 	//window.parent.CKEDITOR.tools.callFunction(1,'http://www.jb51.net/image.jpg', '上传成功');
 	CKEDITOR.on( editor, function( ev ){ 
        // Take the dialog name and its definition from the event 
        // data. 
        var dialogName = ev.data.name; 
        var dialogDefinition = ev.data.definition;

        // Check if the definition is from the dialog we're 
        // interested on (the "Link" dialog). 
        if ( dialogName == 'Link' ) 
        { 
            // Remove the "advanced" tab from the "Link" dialog. 
            dialogDefinition.removeContents( 'advanced' ); 
        }
});
 	</script>
 </html>
