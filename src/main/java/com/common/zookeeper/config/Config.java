package com.common.zookeeper.config;

public interface Config {
	byte[] getConfig(String path) throws Exception;
}
