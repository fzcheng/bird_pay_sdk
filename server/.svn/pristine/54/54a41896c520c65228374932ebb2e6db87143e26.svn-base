package org.apache.jsp.webpage.main;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.jeecgframework.core.util.ResourceUtil;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_base_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_t_base_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_t_base_type_nobody.release();
    _jspx_tagPool_c_if_test.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html >\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>");
      out.print(ResourceUtil.getConfigByName("webTitle",""));
      out.write("后台管理系统</title>\r\n");
      if (_jspx_meth_t_base_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"images/favicon.ico\">\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("a {\r\n");
      out.write("\tcolor: Black;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:hover {\r\n");
      out.write("\tcolor: black;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<SCRIPT type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// update-start--Author:duanqilu Date:20130601 for：添加在线用户显示\r\n");
      out.write("\t\t$('#layout_jeecg_onlineDatagrid').datagrid({\r\n");
      out.write("\t\t\turl : 'systemController.do?datagridOnline&field=id,ip,logindatetime,loginname,',\r\n");
      out.write("\t\t\ttitle : '',\r\n");
      out.write("\t\t\ticonCls : '',\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tfitColumns : true,\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\tpageSize : 10,\r\n");
      out.write("\t\t\tpageList : [ 10 ],\r\n");
      out.write("\t\t\tnowarp : false,\r\n");
      out.write("\t\t\tborder : false,\r\n");
      out.write("\t\t\tidField : 'id',\r\n");
      out.write("\t\t\tsortName : 'logindatetime',\r\n");
      out.write("\t\t\tsortOrder : 'desc',\r\n");
      out.write("\t\t\tfrozenColumns : [ [ {\r\n");
      out.write("\t\t\t\ttitle : '编号',\r\n");
      out.write("\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\thidden : true\r\n");
      out.write("\t\t\t} ] ],\r\n");
      out.write("\t\t\tcolumns : [ [ {\r\n");
      out.write("\t\t\t\ttitle : '登录名',\r\n");
      out.write("\t\t\t\tfield : 'loginname',\r\n");
      out.write("\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\tsortable : true,\r\n");
      out.write("\t\t\t\tformatter : function(value, rowData, rowIndex) {\r\n");
      out.write("\t\t\t\t\treturn formatString('<span title=\"{0}\">{1}</span>', value, value);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}, {\r\n");
      out.write("\t\t\t\ttitle : 'IP',\r\n");
      out.write("\t\t\t\tfield : 'ip',\r\n");
      out.write("\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\talign : 'center',\r\n");
      out.write("\t\t\t\tsortable : true,\r\n");
      out.write("\t\t\t\tformatter : function(value, rowData, rowIndex) {\r\n");
      out.write("\t\t\t\t\treturn formatString('<span title=\"{0}\">{1}</span>', value, value);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}, {\r\n");
      out.write("\t\t\t\ttitle : '登录时间',\r\n");
      out.write("\t\t\t\tfield : 'logindatetime',\r\n");
      out.write("\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\tsortable : true,\r\n");
      out.write("\t\t\t\tformatter : function(value, rowData, rowIndex) {\r\n");
      out.write("\t\t\t\t\treturn formatString('<span title=\"{0}\">{1}</span>', value, value);\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\thidden : true\r\n");
      out.write("\t\t\t} ] ],\r\n");
      out.write("\t\t\tonClickRow : function(rowIndex, rowData) {\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tonLoadSuccess : function(data) {\r\n");
      out.write("\t\t\t\t$('#layout_jeecg_onlinePanel').panel('setTitle', '( ' + data.total + ' )人在线');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}).datagrid('getPager').pagination({\r\n");
      out.write("\t\t\tshowPageList : false,\r\n");
      out.write("\t\t\tshowRefresh : false,\r\n");
      out.write("\t\t\tbeforePageText : '',\r\n");
      out.write("\t\t\tafterPageText : '/{pages}',\r\n");
      out.write("\t\t\tdisplayMsg : ''\r\n");
      out.write("\t\t});\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#layout_jeecg_onlinePanel').panel({\r\n");
      out.write("\t\t\ttools : [ {\r\n");
      out.write("\t\t\t\ticonCls : 'icon-reload',\r\n");
      out.write("\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t$('#layout_jeecg_onlineDatagrid').datagrid('load', {});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} ]\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\twindow.setInterval(function() {\r\n");
      out.write("\t\t\t$('#layout_jeecg_onlineDatagrid').datagrid('load', {});\r\n");
      out.write("\t\t}, 1000 * 60 * 10);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t$('#layout_east_calendar').calendar({\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tcurrent : new Date(),\r\n");
      out.write("\t\t\tborder : false,\r\n");
      out.write("\t\t\tonSelect : function(date) {\r\n");
      out.write("\t\t\t\t$(this).calendar('moveTo', new Date());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"overflow-y: hidden\" scroll=\"no\">\r\n");
      out.write("\t<!-- 顶部-->\r\n");
      out.write("\t<div region=\"north\" border=\"false\" title=\"版本: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${version }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" style=\"BACKGROUND:#FFFFFF;height: 85px; padding: 1px; overflow: hidden;\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"left\" style=\"vertical-align:text-bottom\"><img src=\"indexContentController.do?getLogo\" style=\"width:819px;height:59px\" /></td>\r\n");
      out.write("\t\t\t\t<td align=\"right\" nowrap>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td valign=\"top\" height=\"50\"><span style=\"color: #CC33FF\">当前用户:</span><span style=\"color: #666633\">(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${userName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(") <span style=\"color: #CC33FF\">职务</span>:<span style=\"color: #666633\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roleName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr><td>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"position: absolute; right: 0px; bottom: 0px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-menubutton\" menu=\"#layout_north_kzmbMenu\" iconCls=\"icon-help\">控制面板</a>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"layout_north_kzmbMenu\" style=\"width: 100px; display: none;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div onclick=\"openwindow('用户信息','userController.do?userinfo')\">个人信息</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"menu-sep\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div onclick=\"add('修改密码','userController.do?changepassword')\">修改密码</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- update-start--Author:周勇  Date:2013-07-29：新增修改首页风格功能  -->\r\n");
      out.write("\t\t\t\t\t\t        <div class=\"menu-sep\"></div>\r\n");
      out.write("\t\t\t\t\t\t        <div onclick=\"add('修改首页风格','userController.do?changestyle')\">首页风格 </div>\r\n");
      out.write("\t\t\t\t\t\t        <!-- update-start--Author:周勇  Date:2013-07-29：新增修改首页风格功能  -->\r\n");
      out.write("\t\t\t\t\t\t        \r\n");
      out.write("\t\t\t\t\t\t        <div class=\"menu-sep\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div onclick=\"exit('loginController.do?logout','确定退出该系统吗 ?',1);\">退出系统</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\t\t</table></td>\r\n");
      out.write("\t\t\t\t<td align=\"right\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 左侧-->\r\n");
      out.write("\t<div region=\"west\" split=\"true\" href=\"loginController.do?left\" title=\"导航菜单\" style=\"width: 150px; padding: 1px;\"></div>\r\n");
      out.write("\t<!-- 中间-->\r\n");
      out.write("\t<div id=\"mainPanle\" region=\"center\" style=\"overflow: hidden;\">\r\n");
      out.write("\t\t<div id=\"maintabs\" class=\"easyui-tabs\" fit=\"true\" border=\"false\">\r\n");
      out.write("\t\t\t <div class=\"easyui-tab\" title=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${tabTitle }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" href=\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${tab}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" style=\"padding:2px; overflow: hidden;\"></div>\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 右侧 -->\r\n");
      out.write("\t<div collapsed=\"true\" region=\"east\" iconCls=\"icon-reload\" title=\"辅助工具\" split=\"true\" style=\"width: 190px;\">\r\n");
      out.write("\t\t<div id=\"tabs\" class=\"easyui-tabs\" border=\"false\" style=\"height: 240px\">\r\n");
      out.write("\t\t\t<div title=\"日历\" style=\"padding: 0px; overflow: hidden; color: red;\">\r\n");
      out.write("\t\t\t\t<div id=\"layout_east_calendar\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- update-start--Author:duanqilu Date:20130601 for：添加在线用户显示 -->\r\n");
      out.write("\t\t<div id=\"layout_jeecg_onlinePanel\" data-options=\"fit:true,border:false\" title=\"用户在线列表\">\r\n");
      out.write("\t\t\t<table id=\"layout_jeecg_onlineDatagrid\"></table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- update---Author:duanqilu Date:20130601 for：添加在线用户显示 -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 底部 -->\r\n");
      out.write("\t<div region=\"south\" border=\"false\" style=\"height: 25px; overflow: hidden;\">\r\n");
      out.write("\t\t<div align=\"center\" style=\"color: #CC99FF; padding-top: 2px\">\r\n");
      out.write("\t\t\t推荐谷歌浏览器，获得更快响应速度\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"mm\" class=\"easyui-menu\" style=\"width:150px;\">\r\n");
      out.write("\t\t<div id=\"mm-tabclose\">关闭</div>\r\n");
      out.write("\t\t<div id=\"mm-tabcloseall\">全部关闭</div>\r\n");
      out.write("\t\t<div id=\"mm-tabcloseother\">除此之外全部关闭</div>\r\n");
      out.write("\t\t<div class=\"menu-sep\"></div>\r\n");
      out.write("\t\t<div id=\"mm-tabcloseright\">当前页右侧全部关闭</div>\r\n");
      out.write("\t\t<div id=\"mm-tabcloseleft\">当前页左侧全部关闭</div>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
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

  private boolean _jspx_meth_t_base_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:base
    jeecg.ext.online.ftl.ExHtmlLinkTag _jspx_th_t_base_0 = (jeecg.ext.online.ftl.ExHtmlLinkTag) _jspx_tagPool_t_base_type_nobody.get(jeecg.ext.online.ftl.ExHtmlLinkTag.class);
    _jspx_th_t_base_0.setPageContext(_jspx_page_context);
    _jspx_th_t_base_0.setParent(null);
    _jspx_th_t_base_0.setType("jquery,easyui,tools,DatePicker");
    int _jspx_eval_t_base_0 = _jspx_th_t_base_0.doStartTag();
    if (_jspx_th_t_base_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
      return true;
    }
    _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${map=='1'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<div class=\"easyui-tab\" title=\"地图\" style=\"padding: 1px; overflow: hidden;\">\r\n");
        out.write("\t\t\t\t\t<iframe name=\"myMap\" id=\"myMap\" scrolling=\"no\" frameborder=\"0\" src=\"mapController.do?map\" style=\"width: 100%; height: 99.5%;\"></iframe>\r\n");
        out.write("\t\t\t\t</div>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
