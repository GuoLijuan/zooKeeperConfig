package com.common.zookeeper.writer;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.common.zookeeper.config.ZooKeeperFactory;

public class WriteData {
	
	public static void main(String[] args) {
	    CuratorFramework client = ZooKeeperFactory.get();
	    
	    List<WriteConfig> list = new ArrayList<WriteConfig>();
	    WriteConfig dbconfig = new WriteDBConfig("/jdbc-conf", "jdbc.properties");
	    list.add(dbconfig);
	    
	    WriteConfigCenter tool = new WriteConfigCenter(list, client);
	    tool.writeConfigs();
	    
	    CloseableUtils.closeQuietly(client);
    }
}
