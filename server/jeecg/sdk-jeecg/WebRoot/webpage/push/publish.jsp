<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my"  uri="/my" %>
  		
<html>
	<head>
		<title>后台管理</title>
		<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar_js.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript">
 	   function window.onbeforeunload(){
				var JscrollPos;
				if (typeof window.pageYOffset != 'undefined') { 
				JscrollPos = window.pageYOffset;
				}
				else if (typeof document.compatMode != 'undefined' &&
				document.compatMode != 'BackCompat') { 
				JscrollPos = document.documentElement.scrollTop;
				}
				else if (typeof document.body != 'undefined') { 
				JscrollPos = document.body.scrollTop;
				}
				document.cookie="scrollTop="+JscrollPos;
	}
			
	function window.onload(){
				getProps();
				var arr;
				if(arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/))
				document.documentElement.scrollTop=parseInt(arr[1]);
				document.body.scrollTop=parseInt(arr[1]);
				
		var itemId = $("#panoply1 option:selected").val();
		var shopName = $("#panoply1 option:selected").text();
		$("#itemId").val(itemId);
		$("#shopName").val(shopName);
	}
function confirmRefresh() {

	if (confirm("确认刷新商品列表!\n刷新列表设置将生效!")) {
		window.location.href = '<%=request.getContextPath()%>/bacl_admin.do?comd=refreshShopList';
	}
}
		</script>
	</head>
	<body class="main_body">
	<c:if test="${message != null && message != ''}">
		<center><font color="#FF0000">${message}</font></center>
	</c:if>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 30">消息推送</strong>
		<br/>
		<form onSubmit="return confirmAddItem()" action="<%=request.getContextPath()%>/bp_admin.do?account=bp_admin&cmd=publish" method="post">
		<table width="50%" border="0px" cellpadding="0" cellspacing="0" style="margin-left: 60">
			<tr>
				<td align="center">推送对象:</td>
				<td>
					<input id="token" type="text" name=token />
				</td>
			</tr>
			<tr>
				<td align="center">标题:</td>
				<td>
					<input id="title" type="text" name="title" />
				</td>
			</tr>
			<tr>
				<td align="center">内容:</td>
				<td>
					<input id="content" type="text" name="content" />
				</td>
			</tr>
			</table>
			<br/>
			<input id="roleId_sub" name="roleId" type="hidden" />
			<input type="submit" style="margin-left: 300" value="确认" />
		</form> <br/>
		
	</body>
</html>
