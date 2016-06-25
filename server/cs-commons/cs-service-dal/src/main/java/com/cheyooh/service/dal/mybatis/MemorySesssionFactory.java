package com.cheyooh.service.dal.mybatis;

public class MemorySesssionFactory extends SelfSessionFactory{
	
	public MemorySesssionFactory(XmlConfiguration configuration) {
		super(configuration);
		
		initMemoryDB();
	}
	
	private void initMemoryDB(){
		
	}

}
