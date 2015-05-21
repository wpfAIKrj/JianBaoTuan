package com.it.config;

/**
 * 基本参数
 * 
 * @author Gong
 *
 */
public class Const {
	public static final String GOTO_MY_PRECIOUS = "my_precious";
	public static final int PRECIOUS = 0x01;
	public static final int COLLECT = 0x02;
	public static final int IDENTIFY = 0x03;
	public static final String DATABASE_NAME = "it";
	public static int DATABASE_VERSION=1;
	
	/**
	 * 文章id
	 */
	public static final String ArticleId="article_id";

	
	/**
	 * 发布鉴定的图片路径
	 */
	public static final String IMAGEPATH="imagepath";

	/**
	 * 单张图片
	 */
	public static final String PICPATH="picpath";
	
	/**
	 * 主页面当前选择的序号
	 */
	public static final String MINDEX="index";
	/**
	 * 主页面保存的用户信息
	 */
	public static final String USER="user";
	
	/**
	 * 跳转我要鉴定
	 */
	public static final int TO_SEND_IDENTIY=0;
	/**
	 * 我要鉴定的第二步
	 */
	public static final int TO_IDENTY_NEXT=1;
	
	/**
	 * 跳转到注册页面
	 */
	public static final int TO_REGISTER=2;
	/**
	 * 跳转到找回密码页面
	 */
	public static final int TO_FOGOT=3;
	/**
	 * 登陆
	 */
	public static final int TO_LOGIN=4;
	/**
	 * 去查看文章详情
	 */
	public static final int TO_ARTICLE=5;
}
