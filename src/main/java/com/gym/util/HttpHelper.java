package com.gym.util;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {

	public static String getRestRootUrl(HttpServletRequest httpRequest) {

		String serverName = httpRequest.getServerName();

		String scheme = httpRequest.getScheme();
		int port = httpRequest.getServerPort();
		String context = httpRequest.getContextPath();

		String requestURI = scheme + "://" + serverName;
		if (port != 80) {
			requestURI += ":" + port;
		}
		requestURI += context + "/";

		return requestURI;
	}
}
