	public java.util.Map<String,Integer> #{insertMethod}GenerateKey(#{recordType} record){
		java.lang.reflect.Field autoPrimaryField=null;
		java.lang.reflect.Field[] fs=record.getClass().getDeclaredFields();
		for(java.lang.reflect.Field f:fs){
			com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey pk=f.getAnnotation(com.cheyooh.service.dal.annotation.AutoIncrementPrimaryKey.class);
			if(pk!=null){
				autoPrimaryField=f;
				break;
			}
		}
		int r= mapper.#{insertMethod}(record);
		java.util.Map<String,Integer> m=new java.util.HashMap<String,Integer>();
		m.put("_r_", r);
		
		if(autoPrimaryField!=null){
			try{
				autoPrimaryField.setAccessible(true);
				Object o=autoPrimaryField.get(record);
				if(o instanceof Integer){
					m.put(autoPrimaryField.getName(), (Integer)o);	
				}										 
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		return m;	
	}
