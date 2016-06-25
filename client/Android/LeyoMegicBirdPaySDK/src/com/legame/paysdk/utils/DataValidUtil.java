package com.legame.paysdk.utils;

public class DataValidUtil {

	
	public static boolean isValidMobilePhoneNo(String phoneNum) {
		// return
		// phoneNum.matches("^(130|131|132|133|134|135|136|137|138|139)\\d{8}$");
		if(phoneNum == null || phoneNum.trim().equals("")){
			return false;
		}
		return phoneNum.matches("^1(\\d){10}$");
	}
	
	public static boolean isValidEmail(String email){
		if(email == null || email.trim().equals("")){
			return false;
		}
		return email.matches("^([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
//		return email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
	}

}
