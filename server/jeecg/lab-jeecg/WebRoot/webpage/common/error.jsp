<%@page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head><title>Exception!</title></head>
<body>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<H2>错误异常: <%= e.getClass().getSimpleName()%></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%>
<P>错误信息：</P>
<%
StringWriter sw=new StringWriter();
e.printStackTrace(new PrintWriter(sw)); 

StringBuilder more=new StringBuilder();
BufferedReader reader=new BufferedReader(new StringReader(sw.getBuffer().toString()));
String line=reader.readLine();
int i=0;
while(line!=null){
	if(i==0){
		out.println(line);
	}else{
		more.append(line);
	}
	
	i++;
	line=reader.readLine();
}
if(more.length()>0){
%>
  <div id="m" style="display:none;"> <%=more.toString()%></div>
<%
}
%>
</body>
</html>