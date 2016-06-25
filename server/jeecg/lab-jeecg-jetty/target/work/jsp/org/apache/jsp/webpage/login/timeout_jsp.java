package org.apache.jsp.webpage.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class timeout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/context/mytags.jsp");
    _jspx_dependants.add("/WEB-INF/tld/easyui.tld");
  }

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

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("    <!-- Link JScript-->\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.min.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/jquery/jquery.cookie.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/login/js/jquery-jrumble.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/login/js/jquery.tipsy.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/login/js/iphone.check.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/login/js/login.js\"></script>\r\n");
      out.write("\t  <script type=\"text/javascript\">\r\n");
      out.write("\t  //判断如果当前页面不为主框架，则将主框架进行跳转\r\n");
      out.write("\t  \tvar tagert_URL = \"");
      out.print(request.getContextPath());
      out.write("/loginController.do?login\";\r\n");
      out.write("\t    if(self==top){\r\n");
      out.write("\t    \twindow.location.href = tagert_URL;\r\n");
      out.write("\t    }else{\r\n");
      out.write("\t    \ttop.location.href = tagert_URL;\r\n");
      out.write("\t    }\r\n");
      out.write("\t  </script>\r\n");
      out.write(" </body>\r\n");
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
