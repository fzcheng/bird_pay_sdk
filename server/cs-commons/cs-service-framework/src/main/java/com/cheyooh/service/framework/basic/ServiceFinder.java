package com.cheyooh.service.framework.basic;

import com.cheyooh.service.framework.idata.Cmd;

public interface ServiceFinder {
	public Class<Service<Cmd>> findServiceClass(String name);
}
