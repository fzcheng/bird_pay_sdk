package com.cheyooh.service.dal.dialect;


/**
 * @author zhouzg@cheyooh.com {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class MySQLDialect extends Dialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		//检查最后15个字符串中是否已经包含limit语句
		String lower=sql.toLowerCase().trim();
		if(lower.length()>15){
			String subsql=lower.substring(lower.length()-15);
			int p=subsql.indexOf("limit");
	    	if(p>0){	    		
	    		char left =subsql.charAt(p-1);
	    		char right=subsql.charAt(p+5);
	    		if(isSplitChar(left) && isSplitChar(right)){
	    			return sql;
	    		}	
	    	}
		}
		
		
        if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}   
  
}
