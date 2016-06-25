package com.cheyooh.service.framework.basic;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;

public class NoneService extends Service<Cmd> {
	protected Result execute(Cmd cmd) {		 
		return null;
	}
 
	protected Cmd getCmd(ServiceContext rc) {		 
		return rc.getCmd(new Cmd());
	}

	protected Result verify(Cmd cmd) {		 
		return StatusCode.NON_SERVICE().newResult("无效的服务名： "+cmd.getCmd());
	}
  
}
