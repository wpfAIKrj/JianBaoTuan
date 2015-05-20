package com.it.config;

/**
 * 生成地址工具类
 * 
 * @author ytmfdw
 */
public class UrlUtil {

	public static final String BASE_URL = "http://123.57.251.101";

	/**
	 * 主页URL
	 * */
	public static String getHomePageURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/home/indexAction");
		return url.toString();
	}

}