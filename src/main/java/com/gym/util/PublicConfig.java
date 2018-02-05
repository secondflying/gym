package com.gym.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PublicConfig {

	private static Properties prop;
	static {
		try {
			InputStream in = PublicConfig.class.getResourceAsStream("/config.properties");
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getImagePath() {
		return prop.getProperty("imagePath");
	}

	public static String getImageUrl() {
		return prop.getProperty("imageUrl");
	}

	public static String getSmsaddress() {
		return prop.getProperty("smsaddress");
	}

	public static String getSmsaddress2() {
		return prop.getProperty("smsaddress2");
	}

	public static String getOSSEndpoint() {
		return prop.getProperty("oss-endpoint");
	}

	public static String getOSSAccessKeyId() {
		return prop.getProperty("oss-accessKeyId");
	}

	public static String getOSSAccessKeySecret() {
		return prop.getProperty("oss-accessKeySecret");
	}

	public static String getOSSBucket() {
		return prop.getProperty("oss-bucket");
	}

	public static String getSTSRoleArn() {
		return prop.getProperty("sts-roleArn");
	}
}