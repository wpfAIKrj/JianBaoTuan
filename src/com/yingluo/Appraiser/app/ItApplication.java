package com.yingluo.Appraiser.app;

import java.util.List;

import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.config.NetConst;
import com.yingluo.Appraiser.im.RongCloudEvent;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DefaultExceptionHandler;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.NetUtils;
import com.yingluo.Appraiser.utils.SharedPreferencesUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class ItApplication extends Application {

	private static  UserInfo currnUser=null;

	private HomeEntity homeEntity;

	private List<CollectionTreasure> hasIdentify;
	private List<CollectionTreasure> unIdentify;



	public HomeEntity getHomeEntity() {
		return homeEntity;
	}

	public void setHomeEntity(HomeEntity homeEntity) {
		this.homeEntity = homeEntity;
	}

	public List<CollectionTreasure> getHasIdentify() {
		return hasIdentify;
	}

	public void setHasIdentify(List<CollectionTreasure> hasIdentify) {
		this.hasIdentify = hasIdentify;
	}
	
	

	public List<CollectionTreasure> getUnIdentify() {
		return unIdentify;
	}

	public void setUnIdentify(List<CollectionTreasure> unIdentify) {
		this.unIdentify = unIdentify;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SqlDataUtil.initSql(this);
		FileUtils.init(this);
		FileUtils.getInstance().initFile();
		BitmapsUtils.init(this);
		RongImUtils.init(this);
        RongCloudEvent.init(this);
        SharedPreferencesUtils.init(this);
        NetUtils.init(this);
        //Crash 日志
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
	}

	  /**
     * 获得当前进程号
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    
    /**
     * 获取当前登录的用户
     */
    public static UserInfo getcurrnUser(){
    	if(currnUser!=null){
    		return currnUser;
    	}
    	String name=SharedPreferencesUtils.getInstance().getLoginUserName();
    	if(SharedPreferencesUtils.getInstance().getIsHaveLoginSave(name)){
			
			currnUser=SqlDataUtil.getInstance().getUserForPhone(name);
			if(currnUser!=null){
				NetConst.SESSIONID=ItApplication.currnUser.getSession_id();
				NetConst.UPTOKEN=ItApplication.currnUser.getImage_token();
				NetConst.IMTOKEN=ItApplication.currnUser.getMessage_token();
				RongImUtils.getInstance().connect(NetConst.IMTOKEN);
		    	return currnUser;
			}else{
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 更新数据
     * @param user
     */
    public static void setCurrnUser(UserInfo user){
    	currnUser=user;
    }
    /**
     * 推出登录状态
     */
    public static void cleanCurrnUser(){
    	RongImUtils.getInstance().disconnect();
		ItApplication.currnUser = null;
		String name=SharedPreferencesUtils.getInstance().getLoginUserName();
		SharedPreferencesUtils.getInstance().saveForIsLoginSave(name, false);
    }
}
