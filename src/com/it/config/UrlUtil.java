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

	/**
	 * 鉴定大厅
	 * */
	public static String getIdentifyPageURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/home/hallAction");
		return url.toString();
	}

	/**
	 * 我的足迹
	 * */
	public static String getMyFootPrintsURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/myFootPrintsListAction");
		return url.toString();
	}

	/**
	 * 我的收藏
	 * */
	public static String getMyCollectionURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/myCollectionsAction");
		return url.toString();
	}

}
