package com.it.utils;

import java.util.List;

import com.it.model.DaoMaster;
import com.it.model.DaoMaster.DevOpenHelper;
import com.it.model.DaoSession;

import android.content.Context;

public class DataUtil {

	private static DataUtil mInstance=null;
	private  DaoMaster daoMaster=null;
	private  DaoSession daoSession=null;
	private DataUtil(){
		
	}
	
	/**
	 * 获取数据库对象
	 * @param context
	 * @return
	 */
	public DataUtil getInstance(Context context){
		if(mInstance==null){
			mInstance=new DataUtil();
			if(mInstance.daoMaster==null){
				DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "if", null);
				try {
					mInstance.daoMaster=new DaoMaster(helper.getWritableDatabase());
					if(mInstance.daoSession==null){
						mInstance.daoSession=mInstance.daoMaster.newSession();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return mInstance;
	}
	
	

}
