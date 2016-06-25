package com.cheyooh.service.sdk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
@WebServlet("/notify_wiipay")
public class NotifyServlet extends HttpServlet {
	private static final long serialVersionUID = -4805739240172064516L;
	   
	public NotifyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher("/service?m=WiipayNotify&fmt=txt").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/service?m=WiipayNotify&fmt=txt").forward(request, response);
	}
 
}
