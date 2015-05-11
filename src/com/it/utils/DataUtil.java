package com.it.utils;

import java.util.List;

import com.it.bean.DaoMaster;
import com.it.bean.DaoSession;
import com.it.bean.UserInfo;
import com.it.bean.UserInfoDao;
import com.it.bean.DaoMaster.DevOpenHelper;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;

public class DataUtil {

	private static DataUtil mInstance=null;
	private static DaoMaster daoMaster=null;
	private static DaoSession daoSession=null;
	private static UserInfoDao userdao=null;
	
	private DataUtil(){
		
	}
	
	/**
	 * 获取数据库对象
	 * @param context
	 * @return
	 */
	public static DataUtil getInstance(Context context){
		if(mInstance==null){
			mInstance=new DataUtil();
			if(mInstance.daoMaster==null){
				DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "if", null);
				try {
					mInstance.daoMaster=new DaoMaster(helper.getWritableDatabase());
					if(mInstance.daoSession==null){
						mInstance.daoSession=mInstance.daoMaster.newSession();
					}
					if(userdao==null){
						userdao=mInstance.daoSession.getUserInfoDao();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return mInstance;
	}
	
	/**
	 * 保存用户信息
	 * @param user
	 */
	public void saveUserInfo(UserInfo user){
		userdao.insertOrReplace(user);
	}
	

}
