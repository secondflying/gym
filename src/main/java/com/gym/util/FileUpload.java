package com.gym.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

/**
 * 上传更新apk文件到oss服务器
 * 
 * */
public class FileUpload {
	
	static Logger logger = Logger.getLogger(FileUpload.class.getName());
	
	public static String saveAPKFile(MultipartFile file, String format) throws IOException {
		
		String fileName = UUID.randomUUID().toString();
		String ending = "apk";
		if (format != null && format.equalsIgnoreCase("apk")) {
			ending = "apk";
		}
		String tmpPath = System.getProperty("java.io.tmpdir");
		File dir = new File(tmpPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File inputFile = File.createTempFile(fileName, "." + ending, dir);

		FileOutputStream outputStream = new FileOutputStream(inputFile);
		IOUtils.copy(file.getInputStream(), outputStream);
		outputStream.close();
		
		String fullName = fileName + "." + ending;
		File apkFile = new File(PublicConfig.getImagePath() + "apk" + File.separator, fullName);
		inputFile.renameTo(apkFile);
		
		try {
			if (StringUtils.isNotBlank(PublicConfig.getOSSEndpoint())) {
				OSSClient ossClient = new OSSClient(PublicConfig.getOSSEndpoint(),
						PublicConfig.getOSSAccessKeyId(), PublicConfig.getOSSAccessKeySecret());
	
				ossClient.putObject(PublicConfig.getOSSBucket(), "apk/" + fullName, apkFile);
	
				ossClient.shutdown();
			}
		} catch (OSSException e) {
			logger.error("上传oss出错", e);
		} catch (ClientException e) {
			logger.error("上传oss出错", e);
		}
		return fullName;
	}
	
}
