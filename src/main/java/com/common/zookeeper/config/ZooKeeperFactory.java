package com.common.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperFactory {

	private static final String CONNECT_STRING = "127.0.0.1:2181";
	private static final String NAME_SPACE = "cfg";
	
	
	public static CuratorFramework get(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(CONNECT_STRING)
				.retryPolicy(retryPolicy)
//				.connectionTimeoutMs(50000)
				.namespace(NAME_SPACE)
				.build();
		client.start();
		return client;
	}
	
	public static void close(CuratorFramework client){
		CloseableUtils.closeQuietly(client);
	}

	public static void setData(CuratorFramework client, String path, byte[] data) {
        try {
        	Stat stat= client.checkExists().forPath(path);
	        if(stat == null) {
	        	client.create().withMode(CreateMode.PERSISTENT).forPath(path, data);
	        } else {
	        	client.setData().forPath(path, data);
	        }
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
}
