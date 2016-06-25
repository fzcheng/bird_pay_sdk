package dalgenerator;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.JavadocComment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhpGenerator {
	static Map<String, String> simpleType = new HashMap<String, String>();
	static {
		simpleType.put("RowBounds", "dal_common_rowbounds");
		simpleType.put("Byte", "integer");
		simpleType.put("Integer", "integer");
		simpleType.put("Float", "float");
		simpleType.put("Short", "integer");
		simpleType.put("Double", "double");
		simpleType.put("String", "string");
		simpleType.put("Boolean", "boolean");
		simpleType.put("Date", "string");
		simpleType.put("Long", "string");

		simpleType.put("short", "integer");
		simpleType.put("byte", "integer");
		simpleType.put("int", "integer");
		simpleType.put("long", "string");
		simpleType.put("float", "float");
		simpleType.put("double", "double");
		simpleType.put("boolean", "boolean");
		
		simpleType.put("List<String>", "array|string");
		simpleType.put("List<Short>", "array|integer");
		simpleType.put("List<Float>", "array|float");		
		simpleType.put("List<Integer>", "array|integer");		
		simpleType.put("List<Long>", "array|string");
		simpleType.put("List<Byte>", "array|integer");
		simpleType.put("List<Double>", "array|double");
		simpleType.put("List<Boolean>", "array|boolean");
		simpleType.put("List<Date>", "array|string");		
		
		simpleType.put("Map<String,Object>", "array|string=>string");
		simpleType.put("List<Map<String,Object>>","array|array(string=>string)");		 
	}
	protected String replaceBlank(String str) {		
		if (str!=null) {
			String dest = "";
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
			return dest;
		}else{
			return null;
		}
		
	}

	
	public String getPhpType(String type){
		type=replaceBlank(type);
		return simpleType.get(type);
	}
	
	protected String db;
	protected String className;
	protected String entity="entity";
	
	protected Set<String> requires = new LinkedHashSet<String>();
 
	public PhpGenerator() {

	}

	public PhpGenerator(String db) {
		this.db = db;
	}

	protected String getPhpClassName(CompilationUnit cu) {
		return cu.getTypes().get(0).getName();

	}

	protected StringBuilder getJavaDoc(JavadocComment doc) {
		StringBuilder comment = new StringBuilder();
		if (doc != null && doc.getContent() != null) {
			BufferedReader sr = new BufferedReader(new StringReader(doc.getContent()));
			try {
				String line = sr.readLine();
				while (line != null) {
					line = line.trim();
					if (line.startsWith("* @")) {
						break;
					} else if(line.length()>0){
						comment.append("     ");
						for(int i=0;i<line.length();i++){
							char c=line.charAt(i);
							if(c=='$'){
								if(i>0 && line.charAt(i-1)=='\\'){
									comment.append(c);
								}else{
									comment.append("\\$");
								}
							}else{
								comment.append(c);
							}
						}
						comment.append("\r\n");
					}
					line = sr.readLine();
				}
				sr.close();
			} catch (Exception e) {
			}
		}		
		return comment;
	}

	protected void checkAndSetDBClass(CompilationUnit cu) {
		if (db == null) {
			String pkg = cu.getPackage().getName().toString();
			if (pkg.endsWith(".dao") || pkg.endsWith(".entity") || pkg.endsWith(".qform")) {
				int x = pkg.lastIndexOf(".");
				pkg = pkg.substring(0, x);

				x = pkg.lastIndexOf(".");
				if (x > 0) {
					String schema = pkg.substring(x + 1).toLowerCase();
					if (!schema.equals("dao") && !schema.equals("entity") && !schema.equals("qform")) {
						db = schema;
					}
				}

			}
		}

		className = getPhpClassName(cu);
 	 
		if (db == null) {
			throw new RuntimeException("property: db is null.");
		}
	}

	public void createPhpFiles(String javaFile,String targetRootDir) throws Exception {
		CompilationUnit cu = JavaParser.parse(new File(javaFile));
		checkAndSetDBClass(cu);

		createPhpFiles(cu,targetRootDir);
	}

	protected abstract void createPhpFiles(CompilationUnit java,String targetRootDir) throws Exception;

	protected void writeFile(String file, String content)throws IOException{
		write(file, content);
	}

	private static Map<String,String> templates=new HashMap<String,String>();
	
	public static void write(String file, String content)throws IOException{
		int x=file.lastIndexOf("/");
		String path=file.substring(0,x);
		File dir=new File(path);
		if(dir.exists()==false){
			System.out.println("Create dirs: "+dir.getAbsolutePath());
			dir.mkdirs();
		}
		System.out.println("Write file:　" + file );
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(content.getBytes("utf-8"));		
		fos.close();		
	}
	public static String getTemplate(String resource){		
		String tpl=templates.get(resource);
		if(tpl==null){
			try{
				InputStream in=null;
				StringBuilder f=new StringBuilder();
				if(resource.startsWith("file://")){
					in=new FileInputStream(resource.substring(7));
				}else{
					in=PhpEntityExampleGenerator.class.getClass().getResourceAsStream(resource);
				}
				BufferedReader reader=new BufferedReader(new InputStreamReader(in,"utf-8"));
				String line=reader.readLine();
				while(line!=null){				
					f.append(line).append("\r\n");
					
					line=reader.readLine();
				}
				reader.close();
				
				tpl=f.toString().trim();
				
				templates.put(resource,tpl);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return tpl;
	}
 

}
