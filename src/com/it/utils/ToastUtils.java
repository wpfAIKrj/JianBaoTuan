package com.it.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示信息
 * @author Administrator
 */
public class ToastUtils {
	// Activity的上下文
	public Context context;
	// 提示信息
	public String str;
	public ToastUtils(Context context, String str) {
		super();
		this.context = context;
		this.str = str;
		Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
		// toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}