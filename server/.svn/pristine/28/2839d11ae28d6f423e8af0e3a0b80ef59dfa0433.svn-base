package dalgenerator;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class JavaMapperImplGenerator extends PhpGenerator{
	private Map<String,Object> vars=new LinkedHashMap<String,Object>();
	private StringBuilder methods=new StringBuilder();
	
	protected void createPhpFiles(CompilationUnit java, String targetRootDir) throws Exception {
		new VistorAdapter().visit(java, null);
		  
		if(java.getImports()!=null)
		for(ImportDeclaration id: java.getImports()){
			if(!"org.apache.ibatis.annotations.Param".equals(id.getName().toString())){
				requires.add(id.getName().toString());
			}
		}		 
		
		StringBuilder imports=new StringBuilder();
		for (String r : requires) {
			imports.append("import ").append(r).append("; \r\n");
		}		
		vars.put("imports",imports);
		vars.put("db",db);
		vars.put("className",className);
		vars.put("methods",methods);
		
		String tpl=getTemplate("/dalgenerator/impl.tpl");
		for(String v:vars.keySet()){
			String key="#\\{"+v+"\\}";			
			tpl=tpl.replaceAll(key,vars.get(v).toString());
		}
		
		writeFile(targetRootDir+"/"+db+"/"+className+"Impl.java",tpl);	
	}
 
	public void visit(MethodDeclaration n, Object arg) {
		StringBuilder sb=new StringBuilder();
	 	 
		sb.append("\r\n	public "+n.getType().toString()+" "+n.getName()+"(");
		int idx=0;
		if(n.getParameters()!=null)
		for(Parameter p:n.getParameters()){
			if(idx>0){
				sb.append(", ");
			}
			sb.append(p.getType().toString()).append(" ");
			sb.append(p.getId().getName());
			
			idx++;
		}		 
		sb.append("){\r\n");
		
		if("void".equals(n.getType().toString())){
			sb.append("		mapper.").append(n.getName()).append("(");
		}else{
			sb.append("		return mapper.").append(n.getName()).append("(");
		}
		
		idx=0;
		if(n.getParameters()!=null)
		for(Parameter p:n.getParameters()){
			if(idx>0){
				sb.append(", ");
			}
			sb.append(p.getId().getName());
			
			idx++;
		}
		sb.append(");");
				 
		sb.append("\r\n	}\r\n\r\n"); 

		methods.append(sb);
		
		
		String method_name=n.getName();
		boolean isInsertGenerateKey=false;
		if(n.getParameters()!=null && n.getParameters().size()==1 &&  n.getParameters().get(0).getId().getName().equals("record")){
			if(method_name.equals("insert") || method_name.equals("insertSelective")){				 
				isInsertGenerateKey=true;
				
			}else if(method_name.equals("insertGenerateKey") || method_name.equals("insertSelectiveGenerateKey")){
				return;
			}
		}	
		
		if(isInsertGenerateKey){
			String recordType=n.getParameters().get(0).getType().toString();
			Map<String,Object> vars=new HashMap<String,Object>();
			vars.put("insertMethod",method_name);
			vars.put("recordType",recordType);	
			
			String tpl=getTemplate("/dalgenerator/impl_insert.tpl");
			for(String v:vars.keySet()){
				String key="#\\{"+v+"\\}";			
				tpl=tpl.replaceAll(key,vars.get(v).toString());
			}
			
			methods.append("	");
			methods.append(tpl);
		}
	}
	
	class VistorAdapter extends VoidVisitorAdapter{
		public void visit(MethodDeclaration n, Object arg) {
			JavaMapperImplGenerator.this.visit(n, arg);
		}
	}

}
