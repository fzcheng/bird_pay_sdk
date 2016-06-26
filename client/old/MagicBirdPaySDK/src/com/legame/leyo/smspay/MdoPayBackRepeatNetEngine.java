package com.legame.leyo.smspay;

import java.util.HashMap;

public class MdoPayBackRepeatNetEngine extends MdoPayBackNetEngine{
	
	private static final String METHOD = "Payback_mmdo_repeat";

	public MdoPayBackRepeatNetEngine(String sid,
			HashMap<String, String> mapMdoPayBack) {
		super(sid, mapMdoPayBack);
		
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}

}
