package com.cheyooh.service.sdk.idata.gameserver;

public class JsonCuccYijianWomusicReqParam {
	private String operation;
	
	private JsonCuccYijianWomusicSyncOrder syncOrder;
	
	private String version;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public JsonCuccYijianWomusicSyncOrder getSyncOrder() {
		return syncOrder;
	}

	public void setSyncOrder(JsonCuccYijianWomusicSyncOrder syncOrder) {
		this.syncOrder = syncOrder;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "JsonCuccYijianWomusicReqParam [operation=" + operation
				+ ", syncOrder=" + syncOrder + ", version=" + version + "]";
	}
	
}
