package org.apache.jsp.webpage.system.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class user_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_base_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_base_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action.release();
    _jspx_tagPool_t_base_type_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody.release();
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
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("  <title>用户信息</title>\r\n");
      out.write("  ");
      if (_jspx_meth_t_base_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" </head>\r\n");
      out.write(" <body style=\"overflow-y: hidden\" scroll=\"no\">\r\n");
      out.write("  ");
      if (_jspx_meth_t_formvalid_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" </body>");
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
    _jspx_th_t_base_0.setType("jquery,easyui,tools");
    int _jspx_eval_t_base_0 = _jspx_th_t_base_0.doStartTag();
    if (_jspx_th_t_base_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
      return true;
    }
    _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
    return false;
  }

  private boolean _jspx_meth_t_formvalid_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:formvalid
    org.jeecgframework.tag.core.easyui.FormValidationTag _jspx_th_t_formvalid_0 = (org.jeecgframework.tag.core.easyui.FormValidationTag) _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action.get(org.jeecgframework.tag.core.easyui.FormValidationTag.class);
    _jspx_th_t_formvalid_0.setPageContext(_jspx_page_context);
    _jspx_th_t_formvalid_0.setParent(null);
    _jspx_th_t_formvalid_0.setFormid("formobj");
    _jspx_th_t_formvalid_0.setDialog(true);
    _jspx_th_t_formvalid_0.setUsePlugin("password");
    _jspx_th_t_formvalid_0.setLayout("table");
    _jspx_th_t_formvalid_0.setAction("userController.do?saveUser");
    int _jspx_eval_t_formvalid_0 = _jspx_th_t_formvalid_0.doStartTag();
    if (_jspx_eval_t_formvalid_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("   <input id=\"id\" name=\"id\" type=\"hidden\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\">\r\n");
        out.write("   <table style=\"width:600px;\" cellpadding=\"0\" cellspacing=\"1\" class=\"formtable\">\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\" width=\"15%\" nowrap>\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       用户名:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\" width=\"85%\">\r\n");
        out.write("      ");
        if (_jspx_meth_c_if_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_formvalid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("      ");
        if (_jspx_meth_c_if_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_formvalid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\" width=\"10%\" nowrap>\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       真实姓名:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\" width=\"10%\">\r\n");
        out.write("      <input id=\"realName\" class=\"inputxt\" name=\"realName\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.realName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" datatype=\"s2-10\">\r\n");
        out.write("      <span class=\"Validform_checktip\">填写个人真实姓名</span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    ");
        if (_jspx_meth_c_if_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_formvalid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\">\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       部门:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\">\r\n");
        out.write("      <select id=\"TSDepart.id\"  name=\"TSDepart.id\"  datatype=\"*\">\r\n");
        out.write("       ");
        if (_jspx_meth_c_forEach_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_formvalid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("      </select>\r\n");
        out.write("      <span class=\"Validform_checktip\">请选择部门</span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\">\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       角色:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\" nowrap>\r\n");
        out.write("      <input name=\"roleid\"  name=\"roleid\" type=\"hidden\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" id=\"roleid\">\r\n");
        out.write("      <input name=\"roleName\" class=\"inputxt\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roleName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" id=\"roleName\" readonly=\"readonly\" datatype=\"*\" />\r\n");
        out.write("      ");
        if (_jspx_meth_t_choose_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_t_formvalid_0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("      <span class=\"Validform_checktip\">角色可多选</span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\" nowrap>\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       手机号码:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\">\r\n");
        out.write("      <!-- update-begin--Author:chenxu  Date:20130318 for：手机号无法保存和修改  -->\r\n");
        out.write("      <input class=\"inputxt\" name=\"mobilePhone\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.mobilePhone}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" datatype=\"m\" errormsg=\"手机号码不正确!\" ignore=\"ignore\">\r\n");
        out.write("      <!-- update-end--Author:chenxu  Date:20130318 for：手机号无法保存和修改  -->\r\n");
        out.write("      <span class=\"Validform_checktip\"></span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\">\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       办公电话:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\">\r\n");
        out.write("      <input  class=\"inputxt\" name=\"officePhone\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.officePhone}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" datatype=\"n\" errormsg=\"办公室电话不正确!\" ignore=\"ignore\">\r\n");
        out.write("      <span class=\"Validform_checktip\"></span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("    <tr>\r\n");
        out.write("     <td align=\"right\">\r\n");
        out.write("      <label class=\"Validform_label\">\r\n");
        out.write("       常用邮箱:\r\n");
        out.write("      </label>\r\n");
        out.write("     </td>\r\n");
        out.write("     <td class=\"value\">\r\n");
        out.write("      <input class=\"inputxt\"  name=\"email\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" datatype=\"e\" errormsg=\"邮箱格式不正确!\" ignore=\"ignore\">\r\n");
        out.write("      <span class=\"Validform_checktip\"></span>\r\n");
        out.write("     </td>\r\n");
        out.write("    </tr>\r\n");
        out.write("   </table>\r\n");
        out.write("  ");
        int evalDoAfterBody = _jspx_th_t_formvalid_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_t_formvalid_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action.reuse(_jspx_th_t_formvalid_0);
      return true;
    }
    _jspx_tagPool_t_formvalid_usePlugin_layout_formid_dialog_action.reuse(_jspx_th_t_formvalid_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_formvalid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_formvalid_0);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id!=null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("     ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.userName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\r\n");
        out.write("     ");
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

  private boolean _jspx_meth_c_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_t_formvalid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_formvalid_0);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id==null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("       <input id=\"userName\" class=\"inputxt\" name=\"userName\" ajaxurl=\"userController.do?checkUser&code=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.userName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.userName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" datatype=\"s2-10\" >\r\n");
        out.write("      <span class=\"Validform_checktip\">用户名范围在2~10位字符</span>\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_if_2(javax.servlet.jsp.tagext.JspTag _jspx_th_t_formvalid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_formvalid_0);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.id==null }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("     <tr>\r\n");
        out.write("      <td align=\"right\">\r\n");
        out.write("       <label class=\"Validform_label\">\r\n");
        out.write("        密码:\r\n");
        out.write("       </label>\r\n");
        out.write("      </td>\r\n");
        out.write("      <td class=\"value\">\r\n");
        out.write("       <input type=\"password\" class=\"inputxt\" value=\"\" name=\"password\" plugin=\"passwordStrength\" datatype=\"*6-18\" errormsg=\"\" />\r\n");
        out.write("       <span class=\"passwordStrength\" style=\"display: none;\"><span>弱</span><span>中</span><span class=\"last\">强</span> </span>\r\n");
        out.write("       <span class=\"Validform_checktip\">密码至少6个字符,最多18个字符</span>\r\n");
        out.write("      </td>\r\n");
        out.write("     </tr>\r\n");
        out.write("     <tr>\r\n");
        out.write("      <td align=\"right\">\r\n");
        out.write("       <label class=\"Validform_label\">\r\n");
        out.write("        重复密码:\r\n");
        out.write("       </label>\r\n");
        out.write("      </td>\r\n");
        out.write("      <td class=\"value\">\r\n");
        out.write("       <input id=\"repassword\" class=\"inputxt\" type=\"password\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.password}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\" recheck=\"password\" datatype=\"*6-18\" errormsg=\"两次输入的密码不一致！\">\r\n");
        out.write("       <span class=\"Validform_checktip\">重复个人密码</span>\r\n");
        out.write("      </td>\r\n");
        out.write("     </tr>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_formvalid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_formvalid_0);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${departList}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("depart");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("        <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${depart.id }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write(' ');
          if (_jspx_meth_c_if_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write(">\r\n");
          out.write("         ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${depart.departname}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\r\n");
          out.write("        </option>\r\n");
          out.write("       ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_if_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_c_if_3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${depart.id==user.TSDepart.id}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
        int evalDoAfterBody = _jspx_th_c_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
    return false;
  }

  private boolean _jspx_meth_t_choose_0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_formvalid_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:choose
    org.jeecgframework.tag.core.easyui.ChooseTag _jspx_th_t_choose_0 = (org.jeecgframework.tag.core.easyui.ChooseTag) _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody.get(org.jeecgframework.tag.core.easyui.ChooseTag.class);
    _jspx_th_t_choose_0.setPageContext(_jspx_page_context);
    _jspx_th_t_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_formvalid_0);
    _jspx_th_t_choose_0.setHiddenName("roleid");
    _jspx_th_t_choose_0.setHiddenid("id");
    _jspx_th_t_choose_0.setUrl("userController.do?roles");
    _jspx_th_t_choose_0.setName("roleList");
    _jspx_th_t_choose_0.setIcon("icon-choose");
    _jspx_th_t_choose_0.setTitle("角色列表");
    _jspx_th_t_choose_0.setTextname("roleName");
    _jspx_th_t_choose_0.setIsclear(new Boolean(true));
    int _jspx_eval_t_choose_0 = _jspx_th_t_choose_0.doStartTag();
    if (_jspx_th_t_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody.reuse(_jspx_th_t_choose_0);
      return true;
    }
    _jspx_tagPool_t_choose_url_title_textname_name_isclear_icon_hiddenid_hiddenName_nobody.reuse(_jspx_th_t_choose_0);
    return false;
  }
}
