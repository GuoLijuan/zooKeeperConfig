package com.common.zookeeper.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static final Logger logger = Logger.getLogger(ZooKeeperPropertyPlaceholderConfigurer.class);
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	        throws BeansException {
	    super.processProperties(beanFactoryToProcess, props);
	    
	    try {
	        fillCustomProperties(props);
	        
	        logger.info(props);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
	        e.printStackTrace();
        }
	}

	private void fillCustomProperties(Properties props) throws Exception {
	   byte[] data = getData(props);
	   fillProperties(props, data);
    }

	private void fillProperties(Properties props, byte[] data) throws IOException {
		String cfg = new String(data, "UTF-8");
		// TODO mutiline configuration
//		if(StringUtils.isNotBlank(cfg)){
//			String[] cfgArr = StringUtils.split(cfg, "=");
//			props.put(cfgArr[0], cfgArr[1]);
//		}
		
		props.load(new ByteArrayInputStream(data));
		
//		System.out.println(">>>>>>> " + cfg);
		
    }

	private byte[] getData(Properties props) throws Exception {
		String path = props.getProperty("zoo.paths");
		Config config = new ZooKeeperConfig();
	    return config.getConfig(path);
    }
}
