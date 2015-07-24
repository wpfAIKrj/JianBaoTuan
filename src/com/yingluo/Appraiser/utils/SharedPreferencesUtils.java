package com.yingluo.Appraiser.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	/**
	 * 临时文件
	 */
	public static final String SP_NAME="appraister";
	
	/**
	 * 登录用户
	 */
	public static final String USER_NAME="user_name";
	
	private  SharedPreferences preferences =null;
	
	public static SharedPreferencesUtils b=null;
	
	private SharedPreferencesUtils(Context context){
		preferences=context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}
	
	public static SharedPreferencesUtils getInstance(){

		return b;
	};
	
	public static void init(Context context){
		if(b==null){
			b=new SharedPreferencesUtils(context);
		}
	}
	
	 /** 
     * 清除所有数据 
     * @param context 
     */  
    public  void clear()  
    {  
        SharedPreferences.Editor editor = preferences.edit();  
        editor.clear();  
        SharedPreferencesCompat.apply(editor);  
    }  
	private void save(String s,String s1) {
         preferences.edit().putString(s, s1).commit();
        
	}

	private  void save(String s,long s1) {
		  preferences.edit().putLong(s, s1).commit();
	}
	
	private void save(String s,float s1){
		 preferences.edit().putFloat(s, s1).commit();
	}

	private  void save(String s,int s1) {
		  preferences.edit().putInt(s, s1).commit();
	}

	private void save(String s,boolean s1){
		preferences.edit().putBoolean(s, s1).commit();
	}
	
	/**
	 * 保存是否长期登录状态
	 */
	public void saveForIsLoginSave(String phoneNum,boolean isloginsave){
		save(phoneNum, isloginsave);
	}
	
	
	/**
	 * 获取是否长期登录状态
	 */
	public boolean getIsHaveLoginSave(String phoneNum){
		if(!preferences.contains(phoneNum)) {
			return false;
		}
		return preferences.getBoolean(phoneNum, false);
	}
	
	/**
	 * 保存登录用户的帐号
	 * @param mobli
	 */
	public void saveLoginUserName(String moblie){
		save(USER_NAME, moblie);
	}
	
	/**
	 * 保存登录用户的密码（和账号匹配）
	 * @param mobli
	 */
	public void saveLoginUserPassword(String moblie,String password){
		save(moblie, password);
	}
	
	/**
	 * 获取登录用户的密码
	 * @return 用户名
	 */
	public String getLoginUserPassword(String moblie){
		if(!preferences.contains(moblie)) {
			return null;
		}
		return preferences.getString(moblie, null);
	}
	
	/**
	 * 获取登录用户的名字
	 * @return 用户名
	 */
	public String getLoginUserName(){
		if(!preferences.contains(USER_NAME)) {
			return null;
		}
		return preferences.getString(USER_NAME, null);
	}
	
	
    /** 
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类 
     */  
    private static class SharedPreferencesCompat  
    {  
        private static final Method sApplyMethod = findApplyMethod();  
  
        /** 
         * 反射查找apply的方法 
         *  
         * @return 
         */  
        @SuppressWarnings({ "unchecked", "rawtypes" })  
        private static Method findApplyMethod()  
        {  
            try  
            {  
                Class clz = SharedPreferences.Editor.class;  
                return clz.getMethod("apply");  
            } catch (NoSuchMethodException e)  
            {  
            }  
  
            return null;  
        }  
  
        /** 
         * 如果找到则使用apply执行，否则使用commit 
         *  
         * @param editor 
         */  
        public static void apply(SharedPreferences.Editor editor)  
        {  
            try  
            {  
                if (sApplyMethod != null)  
                {  
                    sApplyMethod.invoke(editor);  
                    return;  
                }  
            } catch (IllegalArgumentException e)  
            {  
            } catch (IllegalAccessException e)  
            {  
            } catch (InvocationTargetException e)  
            {  
            }  
            editor.commit();  
        }  
    }  
 

	
}