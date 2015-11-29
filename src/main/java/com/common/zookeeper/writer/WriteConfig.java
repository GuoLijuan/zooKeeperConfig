package com.common.zookeeper.writer;

import org.apache.curator.framework.CuratorFramework;

public interface WriteConfig {

	void write(CuratorFramework client);
}
