package com.cheyooh.rpc.dal.#{db};

import java.io.Closeable;
import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.dal.db.#{db}.dao.#{className};

#{imports}
  
public class #{className}Impl implements #{className},Closeable{
	private DAL dal=null;
	private #{className} mapper=null;
	
	public #{className}Impl(){
		dal=DALFactory.createDAL();
		mapper=dal.getMapper(#{className}.class);
	}
	
	public void close(){
		commit();
		
		if(dal!=null){			 
			dal.close();
		}
	}
	
	public void commit(){
		if(dal!=null){
			dal.commit();
		}
	}		
	
	#{methods} 
 
}