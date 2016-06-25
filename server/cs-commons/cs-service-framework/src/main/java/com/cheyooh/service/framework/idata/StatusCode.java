package com.cheyooh.service.framework.idata;

public class StatusCode { 
	public static Result SUCCESS() {
		return new Result(0,"OK");
	}	
	
	public static Result ERR_PARAMETER() {
		return new Result(-1,4,"参数错误");
	}
	
	public static Result ERR_INVLAID() {
		return new Result(-2,2,"非法操作");
	}

	public static Result ERR_SYSTEM() {
		return new Result(-3,"系统错误");
	}
	
	public static Result ERR_NEED_VERIFYCODE() {
		return new Result(-4,"验证码");
	}
	
	public static Result ERR_PROXY_BUSY() {
		return new Result(-5,"代理忙");
	}

	public static Result EXPIRED() {
		return new Result(-6,"会话已过期");
	}
	
	public static Result ERR_NOTFOUND() {
		return new Result(-100,"NotFound");
	} 
	
	public static Result ERR1_NOTFOUND() {
		return new Result(-101,"未找到运营商和sdk支持最低版本匹配的信息");
	}
	
	public static Result ERR2_NOTFOUND() {
		return new Result(-102,"未找到计费匹配的信息");
	}
	
	public static Result ERR3_NOTFOUND() {
		return new Result(-103,"未找到匹配计费省份的信息");
	}
	
	public static Result ERR4_NOTFOUND() {
		return new Result(-104,"未找到匹配计费时段的信息");
	}
	
	public static Result ERR5_NOTFOUND() {
		return new Result(-105,"找到匹配计费屏蔽号段的信息");
	}
	
	public static Result ERR6_NOTFOUND() {
		return new Result(-106,"找到匹配计费屏蔽省份id的信息");
	}

	public static Result NON_SERVICE() {
		return new Result(100,"未处理");
	}


	
	 
}
