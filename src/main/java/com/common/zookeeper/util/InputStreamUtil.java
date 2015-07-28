package com.common.zookeeper.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtil {

	public static byte[] inputStream2Byte(InputStream in) throws IOException{
		ByteArrayOutputStream ots = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count = -1;
		while((count = in.read(data)) != -1){
			ots.write(data, 0, count);
		}
		return ots.toByteArray();
	}
	
	public static InputStream byte2InputStream(byte[] data){
		ByteArrayInputStream is = new ByteArrayInputStream(data);
		return is;
	}
}
