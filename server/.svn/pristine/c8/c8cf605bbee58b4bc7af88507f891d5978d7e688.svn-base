package com.cheyooh.service.dal.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

import org.apache.ibatis.parsing.XPathParser;

/**
 * 根据jar文件路径，来解析对应的jar文件里的信息。
 * @author Com
 *
 */
public class JarFiles {

	/**
	 * 通过jar文件里的包名，返回该包下面的xml文件。
	 * @param jar
	 * @param packageName
	 * @return
	 * @throws Error
	 */
	public static Map<String,InputStream> getJarFiles(String jar, String packageName) throws Exception
	{
		jar = jar.replaceAll("\\\\", "/");
		packageName = packageName.replaceAll("\\\\", "/");
		if(packageName.startsWith("/"))
		{
			packageName = packageName.replaceFirst("/", "");
		}
	    Map<String,InputStream> iss = new TreeMap<String,InputStream>();
	    
	    JarFile jf = null;
	    FileInputStream fis = null;
	    JarInputStream jarFile = null;
	    try
	    {
	    	fis = new FileInputStream(jar);
	        jarFile = new JarInputStream(fis);
	        jf = new JarFile(jar);
	        JarEntry jarEntry = null;
	        do 
	        {       
	        	jarEntry = jarFile.getNextJarEntry();
                if (jarEntry != null) 
                {
                	//System.out.println(jarEntry.getName());
                	if(isInPackagefile(jar, packageName, jarEntry))
                	{
                		iss.put(jarEntry.getName() ,jf.getInputStream(jarEntry));
                		
                	}
                }
	        } while (jarEntry != null);
	    }
	    catch(IOException ioe)
	    {
	       	ioe.printStackTrace();
	    }
	    finally
	    {
	        if(fis != null)
	        {
	        	try {
					fis.close();
				} catch (IOException e) {
					
				}
	        }
	        if(jarFile != null)
	        {
	        	try {
	        		jarFile.close();
	        	} catch (IOException e) {
	        		
	        	}
	        }
	    }
	   return iss;
	}
	
	/**
	 * 判定jarEntry是否符合要求，1.路径是否正确，2是否是目录 3，是否是xml文件
	 * @param jar
	 * @param packageName
	 * @param jarEntry
	 * @return
	 * @throws Error
	 */
	private static boolean isInPackagefile(final String jar, final String packageName, JarEntry jarEntry) throws Exception
	{
		if(jarEntry.getName().startsWith(packageName) && !jarEntry.isDirectory() && jarEntry.getName().endsWith(".xml"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 关闭流
	 * @param is
	 */
	public static void closeInputStream(InputStream is)
	{
	    if(is != null) 
	    { 
	        try
	        {
	        	is.close(); 
	        }
	        catch(IOException ioe)
	        {
	        	
	        }
	    }
	}	
	
	/**
	 * 通过fileName获取jar包对应的文件
	 * @param jarFileName
	 * @param fileName
	 * @return
	 */
	public static InputStream getJarFile(String jarFileName,String fileName) 
	{
		jarFileName = jarFileName.replaceAll("\\\\", "/");
		fileName = fileName.replaceAll("\\\\", "/");
		
		if(fileName.startsWith("/"))
		{
			fileName = fileName.replaceFirst("/", "");
		}
		JarFile jf = null;
		try {
			jf = new JarFile(jarFileName);
			
			 ZipEntry entity = jf.getEntry(fileName);
			 InputStream is = jf.getInputStream(entity);
			 return is;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) 
	{
		String jarFileName = "E:\\Server_2.0\\cs-dal-mappers\\trunk\\target\\cs-dal-mappers-1.1.1-SNAPSHOT.jar";
		String fileName = "\\com\\cheyooh\\service\\dal\\db\\pub\\xmlmap\\PubCarMapper.xml";
		String dirName = "\\com\\cheyooh\\service\\dal\\db\\misc\\xmlmap";
		InputStream is = null;
		try {
			is = getJarFile(jarFileName, fileName);
			byte[] bb = new byte[is.available()];
			is.read(bb);
			System.out.println(new String(bb));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			closeInputStream(is);
		}
		
		Map<String,InputStream> iss = null;
		try {
			iss = getJarFiles(jarFileName, dirName);
			for(Entry<String, InputStream> entry : iss.entrySet())
			{
				String name = entry.getKey();
				is = entry.getValue();
				XPathParser xp = new XPathParser(is);
				byte[] bb = new byte[is.available()];
				is.read(bb);
				System.out.println(name);
				System.out.println(new String(bb));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(iss != null)
			{
				for(Entry<String, InputStream> entry : iss.entrySet())
				{
					is = entry.getValue();
					if(is != null)
					{
						closeInputStream(is);
					}
				}
			}
		}
		
	}

}
