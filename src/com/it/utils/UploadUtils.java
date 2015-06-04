package com.it.utils;

import java.io.File;
import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.text.format.DateFormat;

import com.it.config.NetConst;
import com.it.inter.UpLoadFileInterface;
import com.lidroid.xutils.util.LogUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.utils.UrlSafeBase64;

/**
 * 文件上传下载工具
 * 
 * @author Administrator
 *
 */
public class UploadUtils {



			/**
	 * 上传文件
	 * @param filePath 文件路径
	 * @param listener 回调接口
	 */
	public static void UploadPortrait(String filePath,final UpLoadFileInterface listener){
		UploadManager uploadManager=new UploadManager();
		uploadManager.put(filePath, null, NetConst.UPTOKEN, new UpCompletionHandler() {
			
			@Override
			public void complete(String arg0, ResponseInfo arg1, JSONObject arg2) {
				// TODO Auto-generated method stub
				LogUtils.d("key"+arg0);
				LogUtils.d("info"+arg1);
				LogUtils.d("json"+arg2);
				if(listener!=null){
					listener.complete(arg0, arg1, arg2);	
				}
			}
			
			
		}, new UploadOptions(null, "", true, new UpProgressHandler() {
			
			@Override
			public void progress(String arg0, double arg1) {
				// TODO Auto-generated method stub
				LogUtils.d(arg0+"上传进度："+arg1);
				if(listener!=null){
					listener.progress(arg0, arg1);
				}
			}
			
			
		}, new UpCancellationSignal() {
			
			@Override
			public boolean isCancelled() {
				// TODO Auto-generated method stub
				if(listener!=null){
					return listener.isCancelled();
				}else{
					return false;
				}
			}
		}));
		
	}
	
	
	
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";




}
