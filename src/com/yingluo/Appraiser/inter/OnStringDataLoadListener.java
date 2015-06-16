package com.yingluo.Appraiser.inter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * String数据请求回调接口
 * @author yq
 *
 * @param String  返回混合数据的集合
 */
public interface OnStringDataLoadListener {
	/**
	 * 请求正常返回；执行在UI线程，可以直接刷新UI
	 * @param data 返回的数据
	 */
	public void onBaseDataLoaded(String data);
	
	/**
	 * 请求返回错误信息，执行在UI线程，可以直接刷新UI
	 * @param errorCode	错误码
	 * @param errorMsg	错误信息
	 */
	public void onBaseDataLoadErrorHappened(String errorCode, String errorMsg);
}
