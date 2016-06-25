package dalgenerator;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class PhpEntityExampleGenerator extends PhpGenerator{	 
	private StringBuilder criterions = new StringBuilder();	
	private Map<String,Object> vars=new LinkedHashMap<String,Object>();
	
	public PhpEntityExampleGenerator() {
		super();
		 
	}

	public PhpEntityExampleGenerator(String db) {
		super(db);		
	}
	
	@Override
	public void createPhpFiles(CompilationUnit java,String targetRootDir) throws Exception {
		requires.add("dirname(__FILE__).'/../../../dal/common/dal.php'");
	 
		new VistorAdapter().visit(java, null);
		
		StringBuilder require_once=new StringBuilder();
		/*for(String r:requires){
			require_once.append("require_once ").append(r).append(";\r\n");
		}*/
		
		vars.put("requires",require_once);
		vars.put("db",db);
		vars.put("className",className);
		vars.put("criterions",criterions);
		
		String tpl=getTemplate("/dalgenerator/example.tpl");
		
		
		 
		
		for(String v:vars.keySet()){
			String key="#\\{"+v+"\\}";			
			
			tpl=tpl.replaceAll(key,vars.get(v).toString());
		}
		
		writeFile(targetRootDir+"/dal/"+db+"/entity/"+className+".php",tpl);		 
	}
	 
	
	public void visit(MethodDeclaration n, Object arg) {
		if("Criteria".equals(n.getType().toString()) && n.getName().toString().startsWith("and")){
			StringBuilder comment=getJavaDoc(n.getJavaDoc());
			StringBuilder func=new StringBuilder();
		 	
			if(n.getParameters()!=null)
			for (Parameter p : n.getParameters()) {
				if (func.length() > 0) {
					func.append(", ");					 
				}
				func.append("\\$").append(p.getId().getName());
				  
				String type=p.getType().toString();
				String php_type=getPhpType(type);
				if(php_type==null){
					requires.add("dirname(__FILE__).'/../../../dal/"+db+"/entity/"+type+".php'");
					
					comment.append("\r\n     * @param dal_"+db+"_entity_"+type+" \\$"+p.getId().getName());
				}else{
					comment.append("\r\n     * @param "+php_type+" \\$"+p.getId().getName());
				}
			}
			func.insert(0,"    public function "+n.getName()+"(");
			func.append("){ \r\n");
			
			BufferedReader reader=new BufferedReader(new StringReader(n.getBody().toString()));
			try{
				String line=reader.readLine(); //{
				line=reader.readLine().trim();
				
				List<Parameter> parameters=n.getParameters();
				if(parameters!=null && parameters.size()>0){	
					for (Parameter p : parameters) {
						String v=" "+p.getId().getName()+",";
						int x=line.indexOf(v);
						
						line=line.substring(0,x)+" \\$"+p.getId().getName()+","+line.substring(x+v.length());
					} 
					
					String jdbcDate="addCriterionForJDBCDate(";
					if(line.startsWith(jdbcDate)){
						line="addCriterion("+line.substring(jdbcDate.length());
					}
				}else{
					int x=line.indexOf("(");
					line="addCriterionWithoutValue"+line.substring(x);					 
				}
				func.append("      \\$this->").append(line).append("\r\n");
				func.append("      return \\$this; \r\n");
				
				reader.close();
			}catch(Exception e){}
			
			func.append("    }\r\n\r\n");
			
			comment.insert(0, "    /**");
			comment.append("\r\n    *");
			comment.append("\r\n    * @return dal_"+db+"_entity_"+className+"Criteria  ");
			comment.append("\r\n    */\r\n");
				
			criterions.append(comment);
			criterions.append(func);
		}
	}
	 
	 
	
	class VistorAdapter extends VoidVisitorAdapter{
		public void visit(MethodDeclaration n, Object arg) {
			PhpEntityExampleGenerator.this.visit(n, arg);
		}
	}
}
