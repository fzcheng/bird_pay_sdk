package jeecg.ext.tools;


public class ExTools {

	public static void main(String[] args)throws Exception{
		System.out.println(decodeString("\u6DFB\u52A0\u6210\u529F")); 

	}
	
	public static String decodeString(String s)throws Exception{		 
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			sb.append(c);			
		}
		
		return sb.toString();
	}

	public static String escapeSqlValue(Object v){
		if(v==null){
			return null;
		}else{
			return escapeSqlValue(v.toString());
		}
	}
	public static String escapeSqlValue(String v){
		if(v==null){
			return null;
		}
		
		StringBuffer r=new StringBuffer();
		r.append("'");
		for(int i=0;i<v.length();i++){
			char c=v.charAt(i);
			switch(c){
				case '\'': r.append("''");   break;
				case '"' : r.append("\\\""); break;
				case '\\': r.append("\\\\"); break;
				default  : r.append(c);
			}
		}
		r.append("'");
		return r.toString();		 
	}
}
