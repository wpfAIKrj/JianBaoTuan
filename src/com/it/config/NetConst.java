package com.it.config;

import org.apache.http.client.CookieStore;

public class NetConst {

	public static final String LOGIN_NAME = "mobile";
	public static final String LOGIN_PWD = "password";
	public static final String CONNECTSID="sid";
	
	
	public static final String CODE="code";
	
	public static final int CODE_SUCCESS=10000;
	
	public static final int CODE_ERROR1=10000;
	public static final int CODE_ERROR2=10002;//登陆失败
	public static final int CODE_ERROR3=10002;//用户已注册
	
	public static final String MESSAGE="message";
	
	
	public static final String DATA="data";
	
	
	/**
	 * 短信
	 */
	public static final String SMS_KEY="7375281b92eb";
	
	public static final String SMS_SECRET="a2cb90086e053cc677fac8ddcf8d10cb";
	
	
	/**
	 * 聊天
	 */
	public static final String IM_KEY="3argexb6r9p1e";
	public static final String IM_SECRET="aWFuJg2Rl0O";
	
	public static final String REGISTER_URL="/Users/registAction";
	public static final String LOGIN_URL="/Users/loginAction";
	public static CookieStore COOKIESTORE=null;//初始化为null
	
	public static String SESSIONID=null;//默认为空 注册登陆后赋值
	
	
	public static String DOWNLOADUPLOADSID=null;//默认为空 当登陆注册后冲服务器获取的下载上传凭证
	

}
