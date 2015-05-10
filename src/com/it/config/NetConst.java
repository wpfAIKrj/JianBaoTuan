package com.it.config;

import org.apache.http.client.CookieStore;

public class NetConst {

	public static final String LOGIN_NAME = "mobile";
	public static final String LOGIN_PWD = "password";
	public static final String CONNECTSID="sid";
	
	
	public static final String CODE="code";
	public static final String MESSAGE="message";
	public static final String DATA="data";
	
	public static final String SMS_KEY="7375281b92eb";
	
	public static final String SMS_SECRET="a2cb90086e053cc677fac8ddcf8d10cb";
	
	
	public static final String REGISTER_URL="/Users/registAction";
	public static CookieStore COOKIESTORE=null;//初始化为null
	
	public static String SESSIONID=null;//默认为空 注册登陆后赋值

}
