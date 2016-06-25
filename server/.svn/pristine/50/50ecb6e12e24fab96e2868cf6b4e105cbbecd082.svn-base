package com.cheyooh.tools.xml;

import java.io.IOException;

import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.output.DOMOutputter;

public class DomConvert {
	/**
	 * org.w3c.dom.Document 对象转换为 org.jdom.Document对象
	 * @param domDoc
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static org.jdom.Document dom2jdom(org.w3c.dom.Document domDoc)
			throws JDOMException, IOException {
		// Create new DOMBuilder, using default parser
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document jdomDoc = builder.build(domDoc);
		return jdomDoc;
	}
	
	/**
	 * org.jdom.Document对象 转换为org.w3c.dom.Document对象
	 * @param jdomDoc
	 * @return
	 * @throws Exception
	 */
	public static org.w3c.dom.Document jdom2dom(org.jdom.Document jdomDoc)
			throws Exception {
		DOMOutputter outputter = new DOMOutputter();
		return outputter.output(jdomDoc);
	}
	
}
