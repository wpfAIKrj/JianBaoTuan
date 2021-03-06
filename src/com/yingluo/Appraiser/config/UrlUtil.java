package com.yingluo.Appraiser.config;

/**
 * 生成地址工具类
 * 
 * @author ytmfdw
 */
public class UrlUtil {

	public static final String BASE_URL = "http://123.57.251.101";

	/**
	 * 主页URL
	 */
	public static String getHomePageURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/home/indexAction");
		return url.toString();
	}

	/**
	 * 鉴定大厅
	 */
	public static String getIdentifyPageURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/home/hallAction");
		return url.toString();
	}

	/**
	 * 我的足迹
	 */
	public static String getMyFootPrintsURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/myFootPrintsListAction");
		return url.toString();
	}

	/**
	 * 删除 我的足迹
	 */
	public static String deleteMyFootPrintsURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/deleteFootPrintsAction");
		return url.toString();
	}

	/**
	 * 我的宝物
	 */
	public static String getTreasureByIdURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/treasureByStatusAction");
		return url.toString();
	}

	/**
	 * 他的宝物
	 */
	public static String getTreasureByOtherIdURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Home/otherTreasuresAction");
		return url.toString();
	}

	/**
	 * 他的收藏
	 */
	public static String getOtherCollectionURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Home/otherTreasureListAction");
		return url.toString();
	}

	/**
	 * 我的收藏
	 */
	public static String getMyCollectionURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/myCollectionsAction");
		return url.toString();
	}

	/**
	 * 我的鉴定
	 */
	public static String getMyIdentifyURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Users/treasureListAction");
		return url.toString();
	}

	/**
	 * 所有分类
	 */
	public static String getAllKinds() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/kindsAction");
		return url.toString();
	}

	/**
	 * 根据ID获取用户详情
	 */
	public static String getUserById() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Users/detailAction");
		return url.toString();
	}

	/**
	 * 根据宝物id来收藏
	 */
	public static String collectTreasureById() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/collectAction");
		return url.toString();
	}

	/**
	 * 根据宝物id来取消收藏
	 */
	public static String deleteCollectTreasureById() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/deleteCollectionsAction");
		return url.toString();
	}

	/**
	 * 获取宝贝详情
	 */
	public static String getTreasureAllInfoByIdURL() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/detailAction");
		return url.toString();
	}

	/**
	 * 获取宝贝评论列表
	 * 
	 * @return
	 */
	public static String getTreasureCommentListByIdURL() {
		// TODO Auto-generated method stub
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/commentsAction");
		return url.toString();
	}

	/**
	 * 发表宝贝评论
	 * 
	 * @return
	 */
	public static String sendTreasureCommentURL() {
		// TODO Auto-generated method stub
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Material/addCommentAction");
		return url.toString();
	}

	/**
	 * 我要鉴定
	 * 
	 * @return
	 */
	public static String sendTreasureIdentityURL() {
		// TODO 自动生成的方法存根
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Users/addTreasureAction");
		return url.toString();
	}

	/**
	 * 获取图片下载连接
	 * 
	 * @return
	 */
	public static String getImageTokenUrl() {
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL).append("/Home/token");
		return url.toString();
	}

}
