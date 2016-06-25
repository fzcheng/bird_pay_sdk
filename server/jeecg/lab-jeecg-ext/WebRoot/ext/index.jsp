<%@page import="com.media360.jeecg.ext.*"%>
CurrentTime: <%
out.println(System.currentTimeMillis());
Location loc=new Location();
out.println(loc.hello("World!"));
%>