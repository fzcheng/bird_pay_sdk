package com.legame.paysdk.network.resultdata;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class VirtualCodeResultData extends BaseResultData{
	
	private String expectType;
	
	private boolean isVirtualCodePaySuccess;
	
	public VirtualCodeResultData(String expectType){
		this.expectType = expectType;
	}
	
	@Override
	public String getExpectPageType() {
		// TODO Auto-generated method stub
		return expectType;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
		int count = 0;
		while(count == 0){
			try {
				count = dateInput.available();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		byte[] buffer = new byte[count];
		try {
			dateInput.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		String resultStr = new String(buffer);
		isVirtualCodePaySuccess = resultStr.contains("success");
		return true;
	}
	
	public boolean getVirtualCodePayResult(){
		return isVirtualCodePaySuccess;
	}

	

}
