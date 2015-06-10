package com.it.config;

import org.apache.http.client.CookieStore;

public class NetConst {

	public static final String LOGIN_NAME = "mobile";
	public static final String LOGIN_PWD = "password";
	public static final String CONNECTSID="sid";
	
	
	public static final String CODE="code";
	
	public static final int CODE_SUCCESS=100000;//所有操作成功
	
	public static final int CODE_ERROR1=10001;//参数错误
	public static final int CODE_ERROR2=10002;//更新失败
	public static final int CODE_ERROR3=10003;//添加失败
	public static final int CODE_ERROR4=10004;//暂时没有数据
	public static final int CODE_ERROR5=110000;//登录失败
	public static final int CODE_ERROR6=110001;//注册失败
	public static final int CODE_ERROR7=110002;//手机号码已经存在
	public static final int CODE_ERROR8=110003;//请先登录
	public static final int CODE_ERROR9=110004;//用户数据更新失败
	public static final int CODE_ERROR10=110005;//手机号码格式错误
	public static final int CODE_ERROR11=110006;//鉴定师不存在
	public static final int CODE_ERROR12=110007;//鉴定师不合法
	public static final int CODE_ERROR13=110008;//您已经鉴定此宝物
	
	public static final int CODE_ERROR14=210000;//宝贝添加失败
	public static final int CODE_ERROR15=210001;//请填写鉴定师
	public static final int CODE_ERROR16=210002;//没有找到宝贝
	public static final int CODE_ERROR17=210003;//该宝贝已经被收藏
	public static final int CODE_ERROR18=210003;//收藏宝贝失败
	public static final int CODE_ERROR19=310000;//知识信息没有找到
	public static final int CODE_ERROR20=310001;//知识信息已经无效
	public static final int CODE_ERROR21=310002;//该内容已经被收藏
	public static final int CODE_ERROR22=310003;//收藏内容失败
	
	
	
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
	
	public static final String REGISTER_URL="Users/registAction";//注册
	
	public static final String LOGIN_URL="Users/loginAction";//登录
	
	public static final String EXTRAUSR="Users/extraAction";//更新个人用户
	
	public static final String LIST_INFO = "Contents/listAction";//获取知识大厅列表
	public static final String RAMDOMAPPRAISER="Material/randomAppraiserAction";//获取鉴定师
	
	public static final String MINEACTION="home/mineAction";//获取我的页面入口数据
	
	public static final String COLLECTINFO="contents/collectAction";//收藏文章
	
	public static final String GETCOLLECTINFO="contents/myCollectionsAction";//获取收藏文章列表
	
	public static final String DELETEINFO="contents/deleteCollectionsAction";//删除我收藏文章列表中部分数据
	
	public static final String GETALLKINDTYPE="Material/kindsAction";//获取所有宝物的分类
	
	public static final String GETALLKINDS = "Contents/groupsAction";//获取所有分类数据
	
	public static final String GETDETAILINFO = "contents/detailAction";//获取文章详情
	
	public static final String ATTESTPERSONAL = "Licence/personAction";//个人认证申请
	
	public static final String ATTESTAGENCY = "Licence/companyAction";//机构认证
	
	public static final String UPDATAPWD = "Users/changePwdAction";//更新个人用户密码
	
	public static final String SENDFEED = "Users/suggestAction";//反馈意见
	
	public static final String SENDEXIT = "Users/logoutAction";//用户退出
	
	public static final String PUBLISH_TREASURES = "Material/addAction";//发布藏品
	public static CookieStore COOKIESTORE=null;//初始化为null
	
	public static String SESSIONID=null;//默认为空 注册登陆后赋值
	public static String UPTOKEN=null;//上传的token
	
	public static String IMTOKEN=null;//聊天的token
	
	
	
	public static final String LIKES="likes";
	
	
	public static final String FOOT_NUMBER="foot_number";
	public static final String TREASURE_RECORD_NUMBER="treasure_record_number";
	public static final String TREASURE_NUMBER="treasure_number";
	public static final String LISTSDATA="lists";
	public static final String EXTRA_NAME="nickname";
	
	public static final String SID="sid";
	
	
	public static final  String EXTRA_LOGO="portrait";

	public static final String EXTRA_QQ="qq";
	
	
	public static final String EXTRA_EMAIL="email";
	

	public static final String UPKEY="key";
	public static final String INFO_ID = "id";



	


	
	

	

}
