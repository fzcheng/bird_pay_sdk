package org.apache.jsp.webpage.wzStatZoneProxy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgCol_width_title_field_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_dgCol_title_hidden_field_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_dgCol_width_title_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_dgCol_title_hidden_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody.release();
    _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl.release();
    _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.release();
    _jspx_tagPool_t_dgCol_width_title_field_nobody.release();
    _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody.release();
    _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.release();
    _jspx_tagPool_t_dgCol_title_hidden_field_nobody.release();
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
      if (_jspx_meth_t_datagrid_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(document).ready(function(){\r\n");
      out.write("\t\t$(\"input[name='id_statDate_begin']\").attr(\"class\",\"easyui-datebox\");\r\n");
      out.write("\t\t$(\"input[name='id_statDate_end']\").attr(\"class\",\"easyui-datebox\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"select[name='id_province']\").change(function(){\r\n");
      out.write("\t\t\tvar provinceId = $(this).children('option:selected').val();  //弹出select的值    \r\n");
      out.write("\t\t\t $.ajax({\r\n");
      out.write("\t\t\t\t   type: \"POST\",\r\n");
      out.write("\t\t   \t\t   url: \"wzStatZoneProxyController.do?getCitys&provinceId=\" + provinceId,\r\n");
      out.write("\t\t   \t\t   dataType: \"json\",\r\n");
      out.write("\t\t   \t\t   success: function(json){\r\n");
      out.write("\t\t   \t\t\t//$.messager.show({title:'提示消息',msg: json.msg ,timeout:2000});\r\n");
      out.write("\t\t\t\t\t//alert(json.msg);\t\t   \t\t\t\r\n");
      out.write("\t\t   \t\t\t$(\"select[name='id_city']\").html(json.msg);\r\n");
      out.write("\t\t   \t\t   },\r\n");
      out.write("\t\t   \t\t   error: function(){\r\n");
      out.write("\t\t   \t\t\t  $.messager.show({title:'提示消息',msg:'系统错误',timeout:2000});\r\n");
      out.write("\t\t   \t\t   } \r\n");
      out.write("\t\t\t\t });\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction exportXls() {\r\n");
      out.write("\t\t window.location.href=\"wzStatZoneProxyController.do?exportXls\";\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
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

  private boolean _jspx_meth_t_datagrid_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:datagrid
    org.jeecgframework.tag.core.easyui.DataGridTag _jspx_th_t_datagrid_0 = (org.jeecgframework.tag.core.easyui.DataGridTag) _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl.get(org.jeecgframework.tag.core.easyui.DataGridTag.class);
    _jspx_th_t_datagrid_0.setPageContext(_jspx_page_context);
    _jspx_th_t_datagrid_0.setParent(null);
    _jspx_th_t_datagrid_0.setName("wzStatZoneProxyList");
    _jspx_th_t_datagrid_0.setTitle("接口城市数据统计");
    _jspx_th_t_datagrid_0.setActionUrl("wzStatZoneProxyController.do?datagrid");
    _jspx_th_t_datagrid_0.setIdField("ZoneId");
    _jspx_th_t_datagrid_0.setTreegrid(false);
    _jspx_th_t_datagrid_0.setPagination(true);
    _jspx_th_t_datagrid_0.setFit(true);
    _jspx_th_t_datagrid_0.setFitColumns(true);
    _jspx_th_t_datagrid_0.setQueryMode("group");
    int _jspx_eval_t_datagrid_0 = _jspx_th_t_datagrid_0.doStartTag();
    if (_jspx_eval_t_datagrid_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgCol_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write(" ");
        if (_jspx_meth_t_dgToolBar_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_datagrid_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_t_datagrid_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_t_datagrid_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl.reuse(_jspx_th_t_datagrid_0);
      return true;
    }
    _jspx_tagPool_t_datagrid_treegrid_title_queryMode_pagination_name_idField_fitColumns_fit_actionUrl.reuse(_jspx_th_t_datagrid_0);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_0 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_title_hidden_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_0.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_0.setTitle("id");
    _jspx_th_t_dgCol_0.setField("id");
    _jspx_th_t_dgCol_0.setHidden(false);
    int _jspx_eval_t_dgCol_0 = _jspx_th_t_dgCol_0.doStartTag();
    if (_jspx_th_t_dgCol_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_title_hidden_field_nobody.reuse(_jspx_th_t_dgCol_0);
      return true;
    }
    _jspx_tagPool_t_dgCol_title_hidden_field_nobody.reuse(_jspx_th_t_dgCol_0);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_1(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_1 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_1.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_1.setTitle("客户端");
    _jspx_th_t_dgCol_1.setField("id_accid");
    _jspx_th_t_dgCol_1.setHidden(false);
    _jspx_th_t_dgCol_1.setQuery(false);
    _jspx_th_t_dgCol_1.setReplace((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${accidStr}", java.lang.String.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_t_dgCol_1 = _jspx_th_t_dgCol_1.doStartTag();
    if (_jspx_th_t_dgCol_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.reuse(_jspx_th_t_dgCol_1);
      return true;
    }
    _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.reuse(_jspx_th_t_dgCol_1);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_2(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_2 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_2.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_2.setTitle("产品");
    _jspx_th_t_dgCol_2.setField("id_proxyBelongid");
    _jspx_th_t_dgCol_2.setHidden(true);
    _jspx_th_t_dgCol_2.setQuery(true);
    _jspx_th_t_dgCol_2.setReplace((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${proxyBelongidStr}", java.lang.String.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_t_dgCol_2 = _jspx_th_t_dgCol_2.doStartTag();
    if (_jspx_th_t_dgCol_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.reuse(_jspx_th_t_dgCol_2);
      return true;
    }
    _jspx_tagPool_t_dgCol_title_replace_query_hidden_field_nobody.reuse(_jspx_th_t_dgCol_2);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_3(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_3 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_3.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_3.setTitle("日期");
    _jspx_th_t_dgCol_3.setField("id_statDate");
    _jspx_th_t_dgCol_3.setWidth(new Integer(20));
    _jspx_th_t_dgCol_3.setFormatter("yyyy-MM-dd");
    _jspx_th_t_dgCol_3.setQuery(true);
    _jspx_th_t_dgCol_3.setQueryMode("group");
    int _jspx_eval_t_dgCol_3 = _jspx_th_t_dgCol_3.doStartTag();
    if (_jspx_th_t_dgCol_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody.reuse(_jspx_th_t_dgCol_3);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_queryMode_query_formatter_field_nobody.reuse(_jspx_th_t_dgCol_3);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_4(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_4 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_4.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_4.setTitle("省份");
    _jspx_th_t_dgCol_4.setField("id_province");
    _jspx_th_t_dgCol_4.setWidth(new Integer(20));
    _jspx_th_t_dgCol_4.setQuery(true);
    _jspx_th_t_dgCol_4.setReplace((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${provinceStr}", java.lang.String.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_t_dgCol_4 = _jspx_th_t_dgCol_4.doStartTag();
    if (_jspx_th_t_dgCol_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.reuse(_jspx_th_t_dgCol_4);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.reuse(_jspx_th_t_dgCol_4);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_5(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_5 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_5.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_5.setTitle("城市");
    _jspx_th_t_dgCol_5.setField("id_city");
    _jspx_th_t_dgCol_5.setWidth(new Integer(20));
    _jspx_th_t_dgCol_5.setQuery(true);
    _jspx_th_t_dgCol_5.setReplace("请先选择省份_-1");
    int _jspx_eval_t_dgCol_5 = _jspx_th_t_dgCol_5.doStartTag();
    if (_jspx_th_t_dgCol_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.reuse(_jspx_th_t_dgCol_5);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_replace_query_field_nobody.reuse(_jspx_th_t_dgCol_5);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_6(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_6 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_6.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_6.setTitle("总请求数");
    _jspx_th_t_dgCol_6.setField("totalQuery");
    _jspx_th_t_dgCol_6.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_6 = _jspx_th_t_dgCol_6.doStartTag();
    if (_jspx_th_t_dgCol_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_6);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_6);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_7(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_7 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_7.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_7.setTitle("过滤总数");
    _jspx_th_t_dgCol_7.setField("filterCount");
    _jspx_th_t_dgCol_7.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_7 = _jspx_th_t_dgCol_7.doStartTag();
    if (_jspx_th_t_dgCol_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_7);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_7);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_8(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_8 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_8.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_8.setTitle("实际处理数");
    _jspx_th_t_dgCol_8.setField("activeCount");
    _jspx_th_t_dgCol_8.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_8 = _jspx_th_t_dgCol_8.doStartTag();
    if (_jspx_th_t_dgCol_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_8);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_8);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_9(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_9 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_9.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_9.setTitle("有违章");
    _jspx_th_t_dgCol_9.setField("totalWzMore");
    _jspx_th_t_dgCol_9.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_9 = _jspx_th_t_dgCol_9.doStartTag();
    if (_jspx_th_t_dgCol_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_9);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_9);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_10(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_10 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_10.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_10.setTitle("无违章");
    _jspx_th_t_dgCol_10.setField("totalWzZero");
    _jspx_th_t_dgCol_10.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_10 = _jspx_th_t_dgCol_10.doStartTag();
    if (_jspx_th_t_dgCol_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_10);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_10);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_11(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_11 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_11.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_11.setTitle("参数填写错误");
    _jspx_th_t_dgCol_11.setField("totalWzErrInput");
    _jspx_th_t_dgCol_11.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_11 = _jspx_th_t_dgCol_11.doStartTag();
    if (_jspx_th_t_dgCol_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_11);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_11);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_12(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_12 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_12.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_12.setTitle("查询条件错误");
    _jspx_th_t_dgCol_12.setField("totalWzErrCondition");
    _jspx_th_t_dgCol_12.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_12 = _jspx_th_t_dgCol_12.doStartTag();
    if (_jspx_th_t_dgCol_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_12);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_12);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_13(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_13 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_13.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_13.setTitle("接口错误");
    _jspx_th_t_dgCol_13.setField("totalWzErrInterface");
    _jspx_th_t_dgCol_13.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_13 = _jspx_th_t_dgCol_13.doStartTag();
    if (_jspx_th_t_dgCol_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_13);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_13);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_14(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_14 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_14.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_14.setTitle("其他错误");
    _jspx_th_t_dgCol_14.setField("totalWzErrOther");
    _jspx_th_t_dgCol_14.setWidth(new Integer(20));
    int _jspx_eval_t_dgCol_14 = _jspx_th_t_dgCol_14.doStartTag();
    if (_jspx_th_t_dgCol_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_14);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_14);
    return false;
  }

  private boolean _jspx_meth_t_dgCol_15(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgCol
    org.jeecgframework.tag.core.easyui.DataGridColumnTag _jspx_th_t_dgCol_15 = (org.jeecgframework.tag.core.easyui.DataGridColumnTag) _jspx_tagPool_t_dgCol_width_title_field_nobody.get(org.jeecgframework.tag.core.easyui.DataGridColumnTag.class);
    _jspx_th_t_dgCol_15.setPageContext(_jspx_page_context);
    _jspx_th_t_dgCol_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgCol_15.setTitle("成功率");
    _jspx_th_t_dgCol_15.setField("sucessRate");
    _jspx_th_t_dgCol_15.setWidth(new Integer(10));
    int _jspx_eval_t_dgCol_15 = _jspx_th_t_dgCol_15.doStartTag();
    if (_jspx_th_t_dgCol_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_15);
      return true;
    }
    _jspx_tagPool_t_dgCol_width_title_field_nobody.reuse(_jspx_th_t_dgCol_15);
    return false;
  }

  private boolean _jspx_meth_t_dgToolBar_0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_datagrid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:dgToolBar
    org.jeecgframework.tag.core.easyui.DataGridToolBarTag _jspx_th_t_dgToolBar_0 = (org.jeecgframework.tag.core.easyui.DataGridToolBarTag) _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody.get(org.jeecgframework.tag.core.easyui.DataGridToolBarTag.class);
    _jspx_th_t_dgToolBar_0.setPageContext(_jspx_page_context);
    _jspx_th_t_dgToolBar_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_datagrid_0);
    _jspx_th_t_dgToolBar_0.setTitle("导出excel");
    _jspx_th_t_dgToolBar_0.setIcon("icon-add");
    _jspx_th_t_dgToolBar_0.setOnclick("exportXls();");
    int _jspx_eval_t_dgToolBar_0 = _jspx_th_t_dgToolBar_0.doStartTag();
    if (_jspx_th_t_dgToolBar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody.reuse(_jspx_th_t_dgToolBar_0);
      return true;
    }
    _jspx_tagPool_t_dgToolBar_title_onclick_icon_nobody.reuse(_jspx_th_t_dgToolBar_0);
    return false;
  }
}
