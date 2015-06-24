package com.yingluo.Appraiser.config;

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
	 * 发布鉴定的图片路径(全景)
	 */
	public static final String IMAGEPATH_PANORAMIC="imagepath1";
	/**
	 * 发布鉴定的图片路径(特写)
	 */
	public static final String IMAGEPATH_FEATURE="imagepath2";
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
	 * 跳转时传参字符串
	 */
	public static final  String ENTITY="entity";
	/**
	 * 跳转(宝物)时id
	 * */
	public static final String KIND_ID="kind_id";

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
	/**
	 * 查看收藏文章
	 */
	public static int TO_COLLECT_INFO=6;

	/**
	 * 机构注册
	 */
	public static int TO_ATTEST_AGENCY=7;

	/**
	 * 个人注册
	 */
	public static final int TO_ATTEST_PERSIONAL = 8;
	/**
	 * 跳到鉴定大厅
	 * */
	public static final  int TO_INDENTIFY=9;
	
	/**
	 * 知识大厅跳转到选择宝物分类页面
	 */
	public static int TO_SELECT_TYPE=10;
	/**
	 * 发布鉴定页面选择宝物分类
	 */
	public static int TO_PUBLISH_SELECT_TYPE=11;

	/**
	 * 跳转到修改密码页面
	 */
	public static final int TO_UPDATA_PWD = 12;
	/**
	 * 意见反馈页面
	 */
	public static final int TO_FEEDBACK = 13;
	/**
	 * 我要鉴定
	 */
	public static final int TO_MY_INDENTITY = 14;
	/**
	 * 宝贝搜索页面跳转到宝物鉴定列表
	 */
	public static final int TO_KIND_INDENTIFY = 15;
	/**
	 * 我的页面跳转到系统消息
	 */
	public static final int TO_SYSTEM_INFO = 16;

}
