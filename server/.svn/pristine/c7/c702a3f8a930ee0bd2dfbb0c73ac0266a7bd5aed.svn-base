package org.apache.jsp.webpage.system.log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userBroswerLine_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value_nobody.release();
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
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(document).ready(function() {\r\n");
      out.write("\t\t\tvar chart;\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"logController.do?getBroswerBar&reportType=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${reportType}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\",\r\n");
      out.write("\t\t\t\tsuccess : function(jsondata) {\r\n");
      out.write("\t\t\t\t\tdata = eval(jsondata);\r\n");
      out.write("\t\t\t\t\tchart = new Highcharts.Chart({\r\n");
      out.write("\t\t\t\t\t\tchart : {\r\n");
      out.write("\t\t\t\t\t\t\trenderTo : 'containerline',\r\n");
      out.write("\t\t\t\t\t\t\tplotBackgroundColor : null,\r\n");
      out.write("\t\t\t\t\t\t\tplotBorderWidth : null,\r\n");
      out.write("\t\t\t\t\t\t\tplotShadow : false\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\ttitle : {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '用户浏览器比例分析'\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\txAxis : {\r\n");
      out.write("\t\t\t\t\t\t\tcategories : [ 'IE9', 'MSIE 7.0', 'MSIE 8.0', 'MSIE 7.0', 'Firefox', 'Chrome' ]\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\ttooltip : {\r\n");
      out.write("\t\t\t\t\t\t\tpointFormat : '{series.name}: <b>{point.percentage}%</b>',\r\n");
      out.write("\t\t\t\t\t\t\tpercentageDecimals : 1\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\texporting:{  \r\n");
      out.write("\t\t\t                filename:'折线图',  \r\n");
      out.write("\t\t\t                 url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctxPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/logController.do?export'  \r\n");
      out.write("\t\t\t            }, \r\n");
      out.write("\t\t\t\t\t\tplotOptions : {\r\n");
      out.write("\t\t\t\t\t\t\tpie : {\r\n");
      out.write("\t\t\t\t\t\t\t\tallowPointSelect : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tcursor : 'pointer',\r\n");
      out.write("\t\t\t\t\t\t\t\tshowInLegend : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tdataLabels : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tenabled : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcolor : '#000000',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tconnectorColor : '#000000',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tformatter : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\treturn '<b>' + this.point.name + '</b>: ' + this.percentage + ' %';\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\tseries : data\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"containerline\" style=\"width:80%; height:80%\"></div>\r\n");
      out.write("  \r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("ctxPath");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }
}
