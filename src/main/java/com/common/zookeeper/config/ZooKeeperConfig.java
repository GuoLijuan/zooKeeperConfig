package com.common.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperConfig implements Config {

	public byte[] getConfig(String path) throws Exception {
		CuratorFramework client = ZooKeeperFactory.get();
		if(!existsPath(client, path)) {
			throw new RuntimeException("path is not exist.");
		}
		
		byte[] data = client.getData().forPath(path);
		CloseableUtils.closeQuietly(client);
		
		return data;
	}

	private boolean existsPath(CuratorFramework client, String path) throws Exception {
		Stat stat = client.checkExists().forPath(path);
	    return !(stat == null);
    }

}
