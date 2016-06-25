<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body onload="clickDownload()">
<%
	String tableName = (String) request.getAttribute("tableName");
	if(tableName==null) tableName="";
	String cgfield = (String) request.getAttribute("cgfield");
	if(cgfield=="") cgfield = "";
	String path = (String) request.getAttribute("path");
	Boolean isPicture = (Boolean)request.getAttribute("isPicture");
%>
<% if(isPicture){ %>
<img alt="" src="exCgFormBuildController.do?fileRequest&tableName=<%=tableName%>&cgfield=<%=cgfield%>&path=<%=path%>">
<%}else{%>
<a id="download" href="exCgFormBuildController.do?viewFile&tableName=<%=tableName%>&cgfield=<%=cgfield%>&path=<%=path%>"  alt=""></a>
<%} %>
</body>
<script type="text/javascript">
	function clickDownload(){
		var download = document.getElementById("download");
		download.click();
		
	}
</script>
</html>