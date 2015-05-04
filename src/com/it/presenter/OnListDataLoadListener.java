package com.it.presenter;

import java.util.ArrayList;

/**
 * 列表信息请求回调接口
 * @author yq
 *
 * @param <T> 返回数据抽象类型
 */
public interface OnListDataLoadListener<T> {
	/**
	 * 正常返回
	 * @param data 返回的数据，执行在UI线程，可以直接刷新UI
	 */
	public void onListDataLoaded(ArrayList<T> data);
	/**
	 * 请求返回错误信息，执行在UI线程，可以直接刷新UI
	 * @param errorCode	错误码
	 * @param errorMsg	错误信息
	 */
	public void onListDataLoadErrorHappened(int errorCode, String errorMsg);
}
