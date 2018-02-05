package com.easemob.server.example.httpclient.vo;

import java.net.URL;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;

/**
 * HTTPClient EndPoints
 * 
 * @author Lynch 2014-09-15
 * 
 */
public interface EndPoints {

	public static final URL ROOT_URL = HTTPClientUtils.getURL("");

	public static final URL MANAGEMENT_URL = HTTPClientUtils.getURL("/management");

	public static final URL TOKEN_ORG_URL = HTTPClientUtils.getURL("/management/token");

	public static final String APP_URL = Constants.APPKEY.replace("#", "/");

	public static final URL APPLICATION_URL = HTTPClientUtils.getURL(APP_URL);

	public static final URL TOKEN_APP_URL = HTTPClientUtils.getURL(APP_URL + "/token");

	public static final URL USERS_URL = HTTPClientUtils.getURL(APP_URL + "/users");

	public static final URL MESSAGES_URL = HTTPClientUtils.getURL(APP_URL + "/messages");

	public static final URL CHATMESSAGES_URL = HTTPClientUtils.getURL(APP_URL + "/chatmessages");

	public static final URL CHATGROUPS_URL = HTTPClientUtils.getURL(APP_URL + "/chatgroups");

	public static final URL CHATFILES_URL = HTTPClientUtils.getURL(APP_URL + "/chatfiles");

}
