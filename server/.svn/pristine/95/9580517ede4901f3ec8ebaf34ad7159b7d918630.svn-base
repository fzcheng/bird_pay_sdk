package com.cheyooh.service.sdk.action.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheyooh.tools.log.Logger;

/**
 * @author ljg
 *  
 */
@WebServlet("/dispatchJsp/paySuccessUrlServlet")
public class paySuccessUrlServlet extends HttpServlet {
	private static Logger logger = new Logger(paySuccessUrlServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365470690056691818L;

	public paySuccessUrlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("the paySuccessUrlServlet is dispatch");
	}
}
