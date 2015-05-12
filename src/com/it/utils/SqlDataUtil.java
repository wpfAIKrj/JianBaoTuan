package com.it.utils;

import java.util.List;

import com.it.bean.UserInfo;
import com.it.config.Const;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.exception.DbException;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;
/**
 * 数据库操作
 * @author Administrator
 *
 */
public class SqlDataUtil {

	private static SqlDataUtil mInstance=null;
	private DbUtils db;
	
	private SqlDataUtil(Context context){
		db =DbUtils.create(context, Const.DATABASE_NAME, Const.DATABASE_VERSION, new DbUpgradeListener() {
			
			@Override
			public void onUpgrade(DbUtils arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				if(arg2!=arg1){
					try {
						db.dropDb();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		db.configAllowTransaction(true);
        db.configDebug(true);
	}
	
	/**
	 * 获取数据库对象
	 * @param context
	 * @return
	 */
	public static SqlDataUtil getInstance(){
		return mInstance;
	}
	
	/**
	 * 必须在数据库操作之前调用
	 * @param context
	 */
	public static void initSql(Context context){
		mInstance=new SqlDataUtil(context);
	}
	
	/**
	 * 保存用户数据
	 * @param user
	 */
	public void saveUserInfo(UserInfo user){
		try {
			db.saveOrUpdate(user);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前本地保存的所有用户数据
	 * @return 存在则是链表，不存在则为null
	 */
	public List<UserInfo> getUserList(){
		List<UserInfo> list =null;
		try {
			list= db.findAll(UserInfo.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
