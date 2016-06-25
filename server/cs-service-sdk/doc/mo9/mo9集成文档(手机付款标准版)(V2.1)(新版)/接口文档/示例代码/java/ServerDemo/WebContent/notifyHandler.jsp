<%@page import="com.mokredit.payment.Md5Encrypt"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/***MO9的notify请求统一采用UTF-8编码，请在读取参数前指定字符集，以免出现乱码情况*/
    request.setCharacterEncoding("UTF-8");
	/**从HTTP请求中获取Notify参数*/
	Map<String,String> params = getParams(request);
	/**签名*/
	String sign = params.get("sign").toString();
	/**订单交易状态*/
	String status=params.get("trade_status").toString();
	/**invoice*/
	String invoice=params.get("invoice").toString();
	/**
	 *签名验证，请使用mo9.com提供给你的MD5密钥执行签名验证.
	 */
	if(sign.equalsIgnoreCase(Md5Encrypt.sign(params, "010c7c348d7449f4a1d78a32d28f6541")))
	{/***签名验证通过*/
		if("TRADE_SUCCESS".equals(status))
		{/**交易状态成功.*/
			if(!hasProcessed(invoice))
			{
				/***请在这里添加你的业务处理代码*/
				System.out.println("业务处理完成.invoice:"+invoice);
				/**返回mo9告知交易已经处理成功*/
				out.print("OK");
			}
			else
			{
				/**返回mo9告知交易已经处理成功，不需要再重复发送*/
				out.print("OK");
			}	
		}
		else
		{/**交易状态失败.当前版本mo9，不会notify给你任何失败交易.*/
			System.out.println("交易失败.invoice:"+invoice);
		}
	}
	else
	{/**签名验证不通过*/
		out.print("ILLEGAL SIGN");
	}
	
	out.flush();
%>
<%!
	/**
	 * 从HTTP请求中提取NOTIFY参数，
	 */
	private Map<String,String> getParams(ServletRequest req)
	{
		Map<String,String> payParams= new HashMap<String,String>();
		payParams.put("pay_to_email",req.getParameter("pay_to_email"));
		payParams.put("payer_id", req.getParameter("payer_id"));
		payParams.put("trade_no",req.getParameter("trade_no"));
		payParams.put("trade_status",req.getParameter("trade_status"));
		payParams.put("sign",req.getParameter("sign"));
		payParams.put("amount",req.getParameter("amount"));	
		payParams.put("currency", req.getParameter("currency"));
		payParams.put("req_amount",req.getParameter("req_amount"));
		payParams.put("req_currency",req.getParameter("req_currency"));
		payParams.put("item_name",req.getParameter("item_name"));
		payParams.put("lc",req.getParameter("lc"));
		payParams.put("invoice",req.getParameter("invoice"));
		/**extra_param为可选字段,如果你的支付请求中不包含该参数，则不需要提取.*/
		//payParams.put("extra_param",req.getParameter("extra_param"));
		/**app_id为可选字段,如果你的支付请求中不包含该参数，则不需要提取.*/
		payParams.put("app_id",req.getParameter("app_id"));
		return payParams;
	}

	/**
	 * 根据Invoice验证，当前notify请求是否已经被你的系统处理
	 */
	private boolean hasProcessed(String invoice)
	{
		return false;
	}	
%>