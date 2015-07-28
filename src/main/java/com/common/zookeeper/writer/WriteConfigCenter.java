package com.common.zookeeper.writer;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;

public class WriteConfigCenter {

	private List<WriteConfig> list;
	private CuratorFramework client;
	
	public WriteConfigCenter(List<WriteConfig> list, CuratorFramework client) {
		this.list = list;
		this.client = client;
    }
	
	public void writeConfigs(){
		for(WriteConfig config : list){
			config.write(client);
		}
	}
}
