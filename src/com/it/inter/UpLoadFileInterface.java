package com.it.inter;

import org.json.JSONObject;

import com.qiniu.android.http.ResponseInfo;
/**
 * 文件上传回调
 * @author Administrator
 *
 */
public interface UpLoadFileInterface{
	
	/**
	 * 上传的文件成功
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	void complete(String arg0, ResponseInfo arg1, JSONObject arg2);
	/**
	 * 上传文件的进度
	 * @param arg0
	 * @param arg1
	 */
	void progress(String arg0, double arg1);
	
	/**
	 * 取消上传
	 * @return true为取消，false为不取消
	 */
	boolean isCancelled();
}
