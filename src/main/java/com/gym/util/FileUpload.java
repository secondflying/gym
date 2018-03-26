package com.gym.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

/**
 * 上传更新apk文件到oss服务器
 * 
 * */
public class FileUpload {
	
	static Logger logger = Logger.getLogger(FileUpload.class.getName());
	
	public static String saveAPKFile(InputStream file, String format) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = file.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		byte[] bs = buffer.toByteArray();

		if (bs.length > 0) {

			String ending = "apk";
			if (format != null && format.equalsIgnoreCase("apk")) {
				ending = "apk";
			}

			BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bs));

			String id = UUID.randomUUID().toString();
			String fileName = id + "." + ending;

			String tmpPath;
			tmpPath = System.getProperty("java.io.tmpdir");

			File originFile = new File(tmpPath + fileName);
			if (originFile.isDirectory()) {
				ImageIO.write(bi, ending, originFile);
			} else {
				originFile.mkdirs();
				ImageIO.write(bi, ending, originFile);
			}
			File apkFile = new File(PublicConfig.getImagePath() + "apk" + File.separator, fileName);
			try {
				if (StringUtils.isNotBlank(PublicConfig.getOSSEndpoint())) {
					OSSClient ossClient = new OSSClient(PublicConfig.getOSSEndpoint(),
							PublicConfig.getOSSAccessKeyId(), PublicConfig.getOSSAccessKeySecret());

					ossClient.putObject(PublicConfig.getOSSBucket(), "apk/" + fileName, apkFile);

					ossClient.shutdown();
				}
			} catch (OSSException e) {
				logger.error("上传oss出错", e);
			} catch (ClientException e) {
				logger.error("上传oss出错", e);
			}

			return fileName;
		}
		return null;
	}
	
}
