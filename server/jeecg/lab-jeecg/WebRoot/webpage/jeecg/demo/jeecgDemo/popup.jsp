<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
<head>
 <title>Popup示例</title>
</head>
<body style="overflow-y: hidden" scroll="no">
	<label>用户选择：</label> <span class="searchbox" style="width: 153px; height: 20px;">
	 <input id = "myText" readonly="readonly" type="text" class="searchbox-text" onclick="inputClick();" style="width: 135px; height: 20px; line-height: 20px;">
	<span><span class="searchbox-button" style="height: 20px;" onclick="inputClick();"></span></span>
	</span>
	
	<script type="text/javascript">
	function inputClick() {
		 $.dialog({
				content: "url:jeecgDemoController.do?selectUserList",
				lock : true,
				title:"用户选择",
				width:800,
				height: 400,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	var selected = iframe.getSelectRows();
			    	if (selected == '' || selected == null ){
				    	alert("请选择");
			    		return false;
				    }else {
					    var str = "";
				    	$.each( selected, function(i, n){
					    	if (i==0)
					    	str+= n.realName
					    	else
				    		str+= ","+n.realName
				    	});
				    	$("#myText").val("");
				    	//$('#myText').searchbox('setValue', str);
					    $("#myText").val(str);
				    	return true;
				    }
					
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});
	}
	</script>
</body>
</html>