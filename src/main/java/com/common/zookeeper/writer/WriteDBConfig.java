package com.common.zookeeper.writer;

import java.io.IOException;
import java.io.InputStream;

import org.apache.curator.framework.CuratorFramework;

import com.common.zookeeper.config.ZooKeeperFactory;
import com.common.zookeeper.util.InputStreamUtil;

public class WriteDBConfig implements WriteConfig {

	private String path;
	private String srcFilePath;
	
	
	public void write(CuratorFramework client) {
		InputStream ins = WriteDBConfig.class.getClassLoader().getResourceAsStream(srcFilePath);
		try {
	        byte[] data = InputStreamUtil.inputStream2Byte(ins);
	        ZooKeeperFactory.setData(client, path, data);
	        
        } catch (IOException e) {
	        e.printStackTrace();
        } finally{
        	if(ins != null) {
        		try {
	                ins.close();
                } catch (IOException e) {
	                e.printStackTrace();
                }
        	}
        }
		
    }
	
	public WriteDBConfig(String path, String srcFilePath) {
		this.path = path;
		this.srcFilePath = srcFilePath;
    }
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSrcFilePath() {
		return srcFilePath;
	}

	public void setSrcFilePath(String srcFilePath) {
		this.srcFilePath = srcFilePath;
	}

	
}
