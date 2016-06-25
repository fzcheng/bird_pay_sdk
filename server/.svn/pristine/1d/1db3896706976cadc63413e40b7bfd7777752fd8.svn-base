package dbgenerator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class GeneratorGameSDKMappers {
	public static void main(String[] args) throws Exception {
		GeneratorGameSDKMappers test=new GeneratorGameSDKMappers();
		
		//test.generatorMappers("cs-generator.xml");
		test.generatorMappers("game-sdk-generator.xml");
		
		//test.generatorMappers("leyo-market-generator.xml");
	}
	
	public void generatorMappers(String resource) throws Exception {
		List<String> warnings = new ArrayList<String>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(this.getClass().getClassLoader().getResourceAsStream(resource));

		DefaultShellCallback shellCallback = new DefaultShellCallback(true);

		try {			
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
			
			
			myBatisGenerator.generate(null);
		} catch (InvalidConfigurationException e) {
			assertEquals(2, e.getErrors().size());
			throw e;
		}
		System.out.println(warnings);
	}

	
}
