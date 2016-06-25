package com.cheyooh.service.sdk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notify_tenpay")
public class TenpayNotifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594749653580531337L;
	
	public TenpayNotifyServlet(){
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher("/service?m=TenpayNotify&fmt=txt").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/service?m=TenpayNotify&fmt=txt").forward(request, response);
	}

}
