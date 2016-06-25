package dalgenerator;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PhpMapperGenerator extends PhpGenerator {
	  
	private StringBuilder classBody = new StringBuilder();
	private Map<String,String> paginationClass=new LinkedHashMap<String,String>();
	
	
	public PhpMapperGenerator(){	
	}	
	
	public PhpMapperGenerator(String db){
		super(db);
	}
 
	public void createPhpFiles(CompilationUnit java,String targetRootDir) throws Exception {		 
		requires.add("dirname(__FILE__).'/../../../config/config.php'");
		requires.add("dirname(__FILE__).'/../../../jsonrpc/jsonRPCClient.php'");
		requires.add("dirname(__FILE__).'/../../../dal/common/dal.php'");
	 
		new VistorAdapter().visit(java, null);
 
		StringBuilder f = new StringBuilder();
		f.append("<?php \r\n");
		/*for (String r : requires) {
			f.append("require_once ").append(r).append("; \r\n");
		}*/
		f.append("\r\n");

		f.append("if(!class_exists('dal_"+db+"_dao_" + className + "')){\r\n");
		f.append("  class dal_"+db+"_dao_" + className + "{	\r\n");
		f.append(classBody);
		f.append("  }");
		
		
		for(String pclass:paginationClass.keySet()){
			f.append("\r\n\r\n");
			f.append("  class "+pclass+" extends dal_common_pagination{ \r\n");
			f.append("  	/** \r\n");
			f.append("  	 * 结果列表\r\n");
			f.append("  	 * @var array|"+paginationClass.get(pclass)+"\r\n");
			f.append("  	 */\r\n");
			f.append("  	public $list = array (); \r\n");
			f.append("  }\r\n");
		}
		
		f.append("\r\n}\r\n?>");
		
		writeFile(targetRootDir+"/dal/"+db+"/dao/"+className+".php",f.toString());
	}
 
	public void visit(MethodDeclaration n, Object arg) {
		String method_name=n.getName();
		boolean isInsertGenerateKey=false;
		if(n.getParameters()!=null && n.getParameters().size()==1 &&  n.getParameters().get(0).getId().getName().equals("record")){
			if(method_name.equals("insert") || method_name.equals("insertSelective")){
				method_name=method_name+"GenerateKey";
				isInsertGenerateKey=true;
				
			}else if(method_name.equals("insertGenerateKey") || method_name.equals("insertSelectiveGenerateKey")){
				return;
			}
		}		

		
		
		StringBuilder func_body = new StringBuilder();
		StringBuilder func = new StringBuilder();

		StringBuilder comment=getJavaDoc(n.getJavaDoc()); 
		 
		String fuc_name=n.getName();
		if(n.getType().toString().startsWith("Pagination")){
			fuc_name+="Page";			
		}
		
		func.append("    function " + fuc_name);
		func.append("(");
		
		if(n.getParameters()!=null)
		for (Parameter p : n.getParameters()) {
			if (func_body.length() > 0) {
				func_body.append(", ");
				func.append(", ");
			}
			
			func.append("$").append(p.getId().getName());
			func_body.append("$").append(p.getId().getName());
			
			 
			String type=p.getType().toString();
			String php_type=getPhpType(type);
			if(php_type==null){
				requires.add("dirname(__FILE__).'/../../../dal/"+db+"/entity/"+type+".php'");
				
				comment.append("     * @param dal_"+db+"_entity_"+type+" $"+p.getId().getName()).append("\r\n");
			}else{
				
				comment.append("     * @param "+php_type+" $"+p.getId().getName()).append("\r\n");
			}
		}
		
		
		comment.append("     *\r\n");
		String ret_type=getPhpType(n.getType().toString());		
		if(ret_type==null){
			String s=n.getType().toString();
			if(s.startsWith("List")){
				ret_type="array|"+"dal_"+db+"_entity_"+s.substring(5,s.length()-1);
			}else if(s.startsWith("Pagination")){
				String ptype=s.substring(11,s.length()-1);				 
				if(ptype.startsWith("Map<")){
					ptype="DDQMap";					
					
					ret_type="dal_"+db+"_pagination_"+ptype;
					paginationClass.put(ret_type,"array(string=>string)");
				}else{
					ret_type="dal_"+db+"_pagination_"+ptype;
					paginationClass.put(ret_type,"dal_"+db+"_entity_"+ptype);
				}
			}else{
				ret_type="dal_"+db+"_entity_"+s;
			}
			comment.append("     * @return "+ret_type).append("\r\n");
		}else{
			comment.append("     * @return "+ret_type).append("\r\n");
		}
		
		func.insert(0,"    /**"+comment+"     */\r\n");
		func.append("){\r\n");

		func_body.insert(0, "      $params=array(");
		func_body.append(");\r\n");
		func_body.append("      $client = new jsonRPCClient(JSON_RPC_SERVER); \r\n");
		
		
		
		func_body.append("      $r = $client->__call(\""+db+":"+className+"." + method_name + "\",$params); \r\n");
		if(isInsertGenerateKey){
			func_body.append("      foreach ($r->result AS $key => $value){ \r\n");
			func_body.append("        if($key != '_r_'){ \r\n");			
			func_body.append("          $record->$key=$value; \r\n");
			func_body.append("        }\r\n");
			func_body.append("      }\r\n");
			
			func_body.append("      return $r->result->_r_;");
		}else{
			func_body.append("      return $r->result;");
		}

		func.append(func_body);
		func.append("\r\n    }\r\n\r\n");

		classBody.append(func);
	}
	
	class VistorAdapter extends VoidVisitorAdapter{
		public void visit(MethodDeclaration n, Object arg) {
			PhpMapperGenerator.this.visit(n, arg);
		}
	}
}

