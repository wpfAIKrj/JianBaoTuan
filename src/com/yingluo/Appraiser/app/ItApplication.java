package com.yingluo.Appraiser.app;

import java.util.List;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.HomeEntity;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.im.RongCloudEvent;
import com.yingluo.Appraiser.im.RongImUtils;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DefaultExceptionHandler;
import com.yingluo.Appraiser.utils.FileUtils;
import com.yingluo.Appraiser.utils.SqlDataUtil;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class ItApplication extends Application {

	public static  UserInfo currnUser=null;

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
}
