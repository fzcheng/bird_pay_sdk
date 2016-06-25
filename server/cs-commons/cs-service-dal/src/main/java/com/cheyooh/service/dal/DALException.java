package com.cheyooh.service.dal;
/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class DALException extends RuntimeException {
 
	public DALException() {
		super();
		 
	}

	public DALException(String message, Throwable cause) {
		super(message, cause);
		 
	}

	public DALException(String message) {
		super(message);
		 
	}

	public DALException(Throwable cause) {
		super(cause);
		 
	}

	private static final long serialVersionUID = 7650482929513540889L;

}
