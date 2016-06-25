package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class Resultlovegame extends ResultContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7525945873393339335L;
	private String propsName;
	private String propAlias;

	public Resultlovegame() {
		setTagName("mmpmlovegame");
	}

	public String getPropsName() {
		return propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	public String getPropAlias() {
		return propAlias;
	}

	public void setPropAlias(String propAlias) {
		this.propAlias = propAlias;
	}
}
