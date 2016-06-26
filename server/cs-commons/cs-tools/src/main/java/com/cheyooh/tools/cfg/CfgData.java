package com.cheyooh.tools.cfg;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;

public class CfgData extends CompositeConfiguration {

	public String getString(String string) {
		return super.getString(string);
	}

	public String getString(String string, String string2) {
		return super.getString(string, string2);
	}

	public int getInt(String string, int i) {
		return super.getInt(string, i);
	}

	public List<Object> getList(String string) {
		return super.getList(string);
	}

	public boolean getBoolean(String string, boolean b) {
		return super.getBoolean(string, b);
	}
	
}
