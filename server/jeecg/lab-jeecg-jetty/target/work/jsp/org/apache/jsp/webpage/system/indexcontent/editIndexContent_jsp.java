package org.apache.jsp.webpage.system.indexcontent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class editIndexContent_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_base_type_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_t_base_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_t_base_type_nobody.release();
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
      out.write(" <!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("<!--  ");
      if (_jspx_meth_t_base_0(_jspx_page_context))
        return;
      out.write("--> \r\n");
      out.write("\t<script type=\"text/javascript\" src=\"plug-in/ckeditor/ckeditor.js\"></script>\r\n");
      out.write("\t<style TYPE=\"text/css\" > \r\n");
      out.write("    <!--\r\n");
      out.write("\t* { margin: 0; padding: 0; }\r\n");
      out.write("\tbody {font-family: Verdana, Arial; font-size: 12px; line-height: 18px; }\r\n");
      out.write("\ta { text-decoration: none; }\r\n");
      out.write("\t.container{margin: 20px auto; width: 900px; background: #fff;}\r\n");
      out.write("\th3 { margin-bottom: 15px; font-size: 22px; text-shadow: 2px 2px 2px #ccc; }\r\n");
      out.write("\t\r\n");
      out.write("\t#contactform {\r\n");
      out.write("\t\r\n");
      out.write("\twidth: 80%;\r\n");
      out.write("\tpadding: 20px;\r\n");
      out.write("\tbackground: #f0f0f0;\r\n");
      out.write("\toverflow:auto;\r\n");
      out.write("\t\r\n");
      out.write("\tborder: 1px solid #cccccc;\r\n");
      out.write("\t-moz-border-radius: 7px;\r\n");
      out.write("\t-webkit-border-radius: 7px;\r\n");
      out.write("\tborder-radius: 7px;\t\r\n");
      out.write("\t\r\n");
      out.write("\t-moz-box-shadow: 2px 2px 2px #cccccc;\r\n");
      out.write("\t-webkit-box-shadow: 2px 2px 2px #cccccc;\r\n");
      out.write("\tbox-shadow: 2px 2px 2px #cccccc;\r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.field{margin-bottom:7px;}\r\n");
      out.write("\t\r\n");
      out.write("\tlabel {\r\n");
      out.write("\tfont-family: Arial, Verdana; \r\n");
      out.write("\ttext-shadow: 2px 2px 2px #ccc;\r\n");
      out.write("\tdisplay: block; \r\n");
      out.write("\tfloat: left; \r\n");
      out.write("\tfont-weight: bold; \r\n");
      out.write("\tmargin-right:10px; \r\n");
      out.write("\ttext-align: right; \r\n");
      out.write("\twidth: 120px; \r\n");
      out.write("\tline-height: 25px; \r\n");
      out.write("\tfont-size: 12px; \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.input{\r\n");
      out.write("\tfont-family: Arial, Verdana; \r\n");
      out.write("\tfont-size: 12px; \r\n");
      out.write("\tpadding: 5px; \r\n");
      out.write("\tborder: 1px solid #b9bdc1; \r\n");
      out.write("\twidth: 300px; \r\n");
      out.write("\tcolor: #797979;\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.input:focus{\r\n");
      out.write("\tbackground-color:#E7E8E7;\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.textarea {\r\n");
      out.write("\theight:150px;\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.hint{\r\n");
      out.write("\tdisplay:none;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.field:hover .hint {  \r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tdisplay: block;  \r\n");
      out.write("\tmargin: -30px 0 0 455px;\r\n");
      out.write("\tcolor: #FFFFFF;\r\n");
      out.write("\tpadding: 7px 10px;\r\n");
      out.write("\tbackground: rgba(0, 0, 0, 0.6);\r\n");
      out.write("\t\r\n");
      out.write("\t-moz-border-radius: 7px;\r\n");
      out.write("\t-webkit-border-radius: 7px;\r\n");
      out.write("\tborder-radius: 7px;\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.button{\r\n");
      out.write("\tfloat: right;\r\n");
      out.write("\tmargin:10px 55px 10px 0;\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\tline-height: 1;\r\n");
      out.write("\tpadding: 6px 10px;\r\n");
      out.write("\tcursor:pointer;   \r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\tbackground-image:images/linkbutton_bg.png\r\n");
      out.write("\t\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-shadow: 0 -1px 1px #64799e;\r\n");
      out.write("\t\r\n");
      out.write("\t/* Background gradient */\r\n");
      out.write("\tbackground: #a5b8da;\r\n");
      out.write("\tbackground: -moz-linear-gradient(top, #a5b8da 0%, #7089b3 100%);\r\n");
      out.write("\tbackground: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#a5b8da), to(#7089b3));\r\n");
      out.write("\t\r\n");
      out.write("\t/* Border style */\r\n");
      out.write("  \tborder: 1px solid #5c6f91;  \r\n");
      out.write("\t-moz-border-radius: 10px;\r\n");
      out.write("\t-webkit-border-radius: 10px;\r\n");
      out.write("\tborder-radius: 10px;\r\n");
      out.write("  \r\n");
      out.write("\t/* Box shadow */\r\n");
      out.write("\t-moz-box-shadow: inset 0 1px 0 0 #aec3e5;\r\n");
      out.write("\t-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;\r\n");
      out.write("\tbox-shadow: inset 0 1px 0 0 #aec3e5;\r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t.button:hover {\r\n");
      out.write("\tbackground: #848FB2;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("    -->\r\n");
      out.write("   </style>\r\n");
      out.write(" </head>\r\n");
      out.write("<form id=\"contactform\" class=\"rounded\" action=\"indexContentController.do?saveFiles\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\r\n");
      out.write("  <input type=\"hidden\" name=\"default\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${default}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("<div class=\"field\">\r\n");
      out.write("\t<label for=\"name\">版本 :</label>\r\n");
      out.write("  \t<input type=\"text\" class=\"input\" name=\"version\" id=\"name\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${version}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"field\">\r\n");
      out.write("\t<label for=\"name\">logo:</label>\r\n");
      out.write("  \t<img src=\"indexContentController.do?getLogo&default=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${default }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"input\"/>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"field\">\r\n");
      out.write("\t<label for=\"email\">上传logo:</label>\r\n");
      out.write("  \t<input type=\"file\" class=\"input\" name=\"file\" id=\"file\" />\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"field\">\r\n");
      out.write("  \t<textarea class=\"input textarea\" name=\"content\" id=\"editor1\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${content}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</textarea>\r\n");
      out.write("</div>\r\n");
      out.write("<input type=\"submit\" name=\"Submit\"  class=\"button\" value=\"更新\" />\r\n");
      out.write("</form>\r\n");
      out.write("</form>\r\n");
      out.write(" \r\n");
      out.write(" <script type=\"text/javascript\">\r\n");
      out.write(" \tvar editor = document.getElementById(\"editor1\");\r\n");
      out.write(" \tCKEDITOR.editorConfig = function( config ) {\r\n");
      out.write(" \t\t// Define changes to default configuration here. For example:\r\n");
      out.write(" \t\tconfig.language = 'zh-cn';\r\n");
      out.write(" \t\tconfig.uiColor = '#ffffff';\r\n");
      out.write(" \t\tconfig.fullPage= true;\r\n");
      out.write(" \t\tconfig.allowedContent= true;\r\n");
      out.write(" \t\t\r\n");
      out.write(" \t};\r\n");
      out.write(" \tCKEDITOR.replace(editor, {removeDialogTabs:'image:advanced;flash:advanced;image:Link',filebrowserBrowseUrl : '',uiColor:'b6b6b6',filebrowserImageBrowseLinkUrl:'',filebrowserImageBrowseUrl:'', filebrowserUploadUrl:'indexContentController.do?saveContentImage'});\r\n");
      out.write(" \t//window.parent.CKEDITOR.tools.callFunction(1,'http://www.jb51.net/image.jpg', '上传成功');\r\n");
      out.write(" \tCKEDITOR.on( editor, function( ev ){ \r\n");
      out.write("        // Take the dialog name and its definition from the event \r\n");
      out.write("        // data. \r\n");
      out.write("        var dialogName = ev.data.name; \r\n");
      out.write("        var dialogDefinition = ev.data.definition;\r\n");
      out.write("\r\n");
      out.write("        // Check if the definition is from the dialog we're \r\n");
      out.write("        // interested on (the \"Link\" dialog). \r\n");
      out.write("        if ( dialogName == 'Link' ) \r\n");
      out.write("        { \r\n");
      out.write("            // Remove the \"advanced\" tab from the \"Link\" dialog. \r\n");
      out.write("            dialogDefinition.removeContents( 'advanced' ); \r\n");
      out.write("        }\r\n");
      out.write("});\r\n");
      out.write(" \t</script>\r\n");
      out.write(" </html>\r\n");
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
}
