package dalgenerator;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.List;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class PhpEntityGenerator extends PhpGenerator{
	private StringBuilder classBody = new StringBuilder();
	
	public PhpEntityGenerator() {
		super();
		 
	}

	public PhpEntityGenerator(String db) {
		super(db);		
	}
	
	@Override
	public void createPhpFiles(CompilationUnit java,String targetRootDir) throws Exception {		
		String super_class=getSuperClass(java);		 
		 
		new VistorAdapter().visit(java, null);
 
		
		StringBuilder f = new StringBuilder();
		f.append("<?php \r\n");
		for (String r : requires) {
			f.append("require_once ").append(r).append("; \r\n");
		}
		f.append("\r\n");

		f.append("if(!class_exists('dal_"+db+"_"+entity+"_" + className +"')){\r\n");
		f.append("  class dal_"+db+"_"+entity+"_" + className +(super_class==null?"":(" extends "+super_class))+ "{	\r\n");
		f.append(classBody);
		f.append("  }");
		
		  
		
		f.append("\r\n}\r\n?>");
		
		writeFile(targetRootDir+"/dal/"+db+"/"+entity+"/"+className+".php",f.toString());
		 
	}
	
	private String getSuperClass(CompilationUnit java){
		String super_class=null;
		for(TypeDeclaration td:java.getTypes()){
			if(td instanceof ClassOrInterfaceDeclaration){
				ClassOrInterfaceDeclaration cd=(ClassOrInterfaceDeclaration)td;
				List<ClassOrInterfaceType> superTypes=cd.getExtends();
				if(superTypes!=null && superTypes.size()>0){
					ClassOrInterfaceType itype=superTypes.get(0);
					super_class="dal_"+db+"_entity_"+itype.getName();
					requires.add("dirname(__FILE__).'/../../../dal/"+db+"/entity/"+itype.getName()+".php'");
				}
			}
		}
		return super_class;
	}
	
	private void visit(FieldDeclaration n, Object arg) {
		StringBuilder field=new StringBuilder();
		StringBuilder comment=getJavaDoc(n.getJavaDoc());
		
		String varname=n.getVariables().get(0).getId().toString();
		  
		comment.append("     *\r\n");
		String ret_type=getPhpType(n.getType().toString());		
		if(ret_type==null){
			comment.append("     * @var "+n.getType().toString()).append("\r\n");
		}else{
			comment.append("     * @var "+ret_type).append("\r\n");
		}
		field.append("    /**"+comment+"     */\r\n");
		field.append("   public $" + varname+"; \r\n\r\n");
		
		classBody.append(field);

	}
	
	class VistorAdapter extends VoidVisitorAdapter{
		public void visit(FieldDeclaration n, Object arg) {
			PhpEntityGenerator.this.visit(n, arg);
		}
	}
}
