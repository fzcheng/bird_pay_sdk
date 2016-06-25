package com.cheyooh.service.framework.basic;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;

public class JspService extends Service<Cmd> {
	private String jspServicePath;
	public String getJspServicePath() {
		return jspServicePath;
	}

	public void setJspServicePath(String jspServicePath) {
		this.jspServicePath = jspServicePath;
	}

	@Override
	protected Result verify(Cmd cmd) {
		return null;
	}

	@Override
	protected Result execute(Cmd cmd) {
		return new Result() ;
	}
	
	/**
	 * JSP页面实例调用校验
	 * @param cmd
	 * @return
	 */
	public Result	 jspPageDefaultVerify(Cmd cmd) {
		return super.doDefaultVerify(cmd);
	}
	
}
