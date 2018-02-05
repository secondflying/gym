package com.gym.util;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BaiduMapUtils {
	static Logger logger = LoggerFactory.getLogger(BaiduMapUtils.class);

	@SuppressWarnings("deprecation")
	public static String coodToAddress(double x, double y) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		try {
			String url = "http://api.map.baidu.com/geocoder/v2/?ak=NW05Fj992DSyDaIkedUoD6p1&output=json&coordtype=wgs84ll&location="
					+ URLEncoder.encode(y + "," + x);
			HttpGet httpget = new HttpGet(url);

			HttpResponse response = httpclient.execute(httpget);
			String bs = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

			if (StringUtils.isNotBlank(bs)) {
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = parser.parse(bs).getAsJsonObject();
				if (jsonObject.get("status").getAsInt() == 0) {
					// JsonObject result =
					// jsonObject.get("result").getAsJsonObject().get("addressComponent")
					// .getAsJsonObject();
					// String province = result.get("province").getAsString();
					// String city = result.get("city").getAsString();
					// String pc = province + " " + city;
					// if (province.equals(city)) {
					// pc = province;
					// }
					// return pc;

					return jsonObject.get("result").getAsJsonObject().get("formatted_address").getAsString();
				}
			}
		} catch (Exception e) {
			logger.error("获取地址错误：", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	public static Integer coodToZoneCode(double x, double y) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		try {
			String url = "http://api.map.baidu.com/geocoder/v2/?ak=NW05Fj992DSyDaIkedUoD6p1&output=json&location="
					+ URLEncoder.encode(y + "," + x);
			HttpGet httpget = new HttpGet(url);

			HttpResponse response = httpclient.execute(httpget);
			String bs = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

			if (StringUtils.isNotBlank(bs)) {
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = parser.parse(bs).getAsJsonObject();
				if (jsonObject.get("status").getAsInt() == 0) {
					JsonObject result = jsonObject.get("result").getAsJsonObject().get("addressComponent")
							.getAsJsonObject();
					String adcode = result.get("adcode").getAsString();

					return Integer.parseInt(adcode);
				}
			}
		} catch (Exception e) {
			logger.error("获取地址错误：", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
}
