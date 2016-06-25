package org.apache.jsp.webpage.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;

public final class error_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head><title>Exception!</title></head>\r\n");
      out.write("<body>\r\n");
 Exception e = (Exception)request.getAttribute("ex"); 
      out.write("\r\n");
      out.write("<H2>错误异常: ");
      out.print( e.getClass().getSimpleName());
      out.write("</H2>\r\n");
      out.write("<hr />\r\n");
      out.write("<P>错误描述：</P>\r\n");
      out.print( e.getMessage());
      out.write("\r\n");
      out.write("<P>错误信息：</P>\r\n");

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

      out.write("\r\n");
      out.write("  <div id=\"m\" style=\"display:none;\"> ");
      out.print(more.toString());
      out.write("</div>\r\n");

}

      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
