package com.cheyooh.tools.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.cheyooh.tools.cfg.EnvUtil;
import com.cheyooh.tools.log.Logger;

/**
 * XML处理工具类
 */
public class XmlTools {
	private static Logger logger = new Logger(XmlTools.class);
	public static Document loadXmlFile(String xmlfile) throws Exception {
		// 静态方法newInstance得到解析工厂实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 用解析工厂的newDocumentBuilder()方法得到解析对象
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(xmlfile));
		return updateDocument(document);
	}
	
	/**
	 * 把Document转换为string输出
	 * @param document
	 * @return
	 */
	public static String toStringFromDoc(Document document) {  
        String result = null;         
        if (document != null) {  
            StringWriter strWtr = new StringWriter();  
            StreamResult strResult = new StreamResult(strWtr);  
            TransformerFactory tfac = TransformerFactory.newInstance();  
            try {  
                javax.xml.transform.Transformer t = tfac.newTransformer();  
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
                t.setOutputProperty(OutputKeys.INDENT, "yes");  
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,  
                // text  
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");  
                t.transform(new DOMSource(document.getDocumentElement()), strResult);  
            } catch (Exception e) {  
                System.err.println("XML.toString(Document): " + e);  
            }  
            result = strResult.getWriter().toString();  
            try {  
                strWtr.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }   
        
        return result;  
    }  

	/**
	 * 变量输出整个XML文件
	 * 
	 * @param document
	 */
	public static void showDocument(Document document) {
		// 调用遍历节点方法，参数为根节点
		outNodeTranserse(document.getDocumentElement());// 遍历xml根节点root
		String root = document.getDocumentElement().getNodeName();// 根节点名称
		logger.info("</" + root + ">");// 遍历完之后，结束根节点名称
		// 当初一直得不到根节点root的结束标签
	}

	/**
	 * 对xml文件中的变量进行值替换后输出
	 * 
	 * @param document
	 * @return
	 */
	private static Document updateDocument(Document document) {
		Node root = document.getDocumentElement();
		document.replaceChild(root, updateNode(root));
		return document;
	}

	/**
	 * 更新node节点中的变量值
	 * 
	 * @param node
	 * @return
	 */
	private static Node updateNode(Node node) {
		NamedNodeMap nnm = node.getAttributes();
		if (nnm != null && nnm.getLength() > 0) {
			for (int i = 0; i < nnm.getLength(); i++) {
				Attr attr = (Attr) nnm.item(i);
				attr.setValue(getMatchValueInContent(attr.getValue(),
						"\\$\\{(.*?)\\}"));
			}
		}

		if (node.getNodeType() == Node.TEXT_NODE) { // 文本类型，输出文本
			String text = ((Text) node).getWholeText();
			text = getMatchValueInContent(text, "\\$\\{(.*?)\\}");
			node.setNodeValue(text);
		}

		NodeList allNodes = node.getChildNodes();// 获取所要遍历节点的子节点
		int size = allNodes.getLength();
		if (size > 0) {
			for (int j = 0; j < size; j++) {
				Node childNode = allNodes.item(j);
				updateNode(childNode);
			}
		}
		return node;
	}

	/**
	 * 变量node
	 * 
	 * @param node
	 */
	private static void outNodeTranserse(Node node) {
		StringBuffer sb_attr = new StringBuffer();
		NamedNodeMap nnm = node.getAttributes();
		if (nnm != null && nnm.getLength() > 0) {
			for (int i = 0; i < nnm.getLength(); i++) {
				Attr attr = (Attr) nnm.item(i);
				sb_attr.append(" ");
				sb_attr.append(attr.getName());
				sb_attr.append("=\"");
				String attr_value = attr.getValue();
				sb_attr.append(attr_value);
				sb_attr.append("\"");
			}
		}

		String rNodeName = node.getNodeName();// 当前遍历元素名称
		if (node.getNodeType() == Node.ELEMENT_NODE) { // 为节点类型，输出节点名称
			logger.info("<" + rNodeName + sb_attr.toString() + ">");
			sb_attr = new StringBuffer();
		}
		if (node.getNodeType() == Node.TEXT_NODE) { // 文本类型，输出文本
			String text = ((Text) node).getWholeText();
			logger.info(text);
		}

		NodeList allNodes = node.getChildNodes();// 获取所要遍历节点的子节点
		int size = allNodes.getLength();
		if (size > 0) {
			for (int j = 0; j < size; j++) {
				Node childNode = allNodes.item(j);
				outNodeTranserse(childNode);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					// 每遍历完一个标签，输出结束标签
					logger.info("</" + childNode.getNodeName() + ">");
				}
			}
		}
	}

	/**
	 * 对文档中符合条件的变量进行值替换
	 * 
	 * @param content
	 * @param mstring
	 * @return
	 */
	private static String getMatchValueInContent(String content, String mstring) {
		Pattern pattern = Pattern.compile(mstring);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String fg = matcher.group(1);
			// TODO 替换的值应该从加载的环境变量组合中得到
			content = content.replaceAll(fg, EnvUtil.getValue(fg));
		}
		// TODO 注意内容中本身包含特殊字符情况(暂不考虑处理)
		content = content.replaceAll("\\$\\{", "").replaceAll("\\}", "");
		return content;
	}
}
