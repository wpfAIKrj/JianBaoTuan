package com.yingluo.Appraiser.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	/**
	 * 临时文件
	 */
	public static final String SP_NAME="Flower_sp";
	
	/**
	 * 是否已经登录
	 */
	public static final String ISLOGIN="islogin";
	
	/**
	 * 登录用户邮箱
	 */
	public static final String USER_NAME="user_name";
	
	/**
	 * 登录用户密码
	 */
	public static final String USER_PASSWORD="user_password";
	
	public static final String SYNC_DAY="last_syncday";
	
	private  SharedPreferences preferences =null;
	
	public static SharedPreferencesUtils b=null;
	
	private SharedPreferencesUtils(Context context){
		preferences=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}
	
	
	public static SharedPreferencesUtils getInstance(Context context){
		if(b==null){
			b=new SharedPreferencesUtils(context);
		}
		return b;
	};
	
	public void save(String s,String s1) {
         preferences.edit().putString(s, s1).commit();
        
	}

	public  void save(String s,long s1) {
		  preferences.edit().putLong(s, s1).commit();
	}
	
	public void save(String s,float s1){
		 preferences.edit().putFloat(s, s1).commit();
	}

	public  void save(String s,int s1) {
		  preferences.edit().putInt(s, s1).commit();
	}

	/**
	 * 保存同步日期
	 * @param data（同板子上面的时间标签）
	 */
	public void saveSyncDaY(int data){
			save(SYNC_DAY, data);
	}
	
	/**
	 * 获取同步日期
	 * @return （-1为没有通不过）
	 */
	public int getSyncDay(){
		return preferences.getInt(SYNC_DAY, -1);
	}
	
	
	

	
}