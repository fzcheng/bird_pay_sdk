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
@WebServlet("/dispatchJsp/payFaiUrlServlet")
public class payFaiUrlServlet extends HttpServlet {
	private static Logger logger = new Logger(payFaiUrlServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -3132001923222894506L;

	public payFaiUrlServlet() {
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
