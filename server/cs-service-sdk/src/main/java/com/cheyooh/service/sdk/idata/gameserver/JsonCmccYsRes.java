package com.cheyooh.service.sdk.idata.gameserver;

public class JsonCmccYsRes {
	
	private  String CD;
	
	private  String MSG;
	
	private  String OID;

	public String getCD() {
		return CD;
	}

	public void setCD(String cD) {
		CD = cD;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	@Override
	public String toString() {
		return "JsonCmccYsRes [CD=" + CD + ", MSG=" + MSG + ", OID=" + OID
				+ "]";
	}
	
	
}
