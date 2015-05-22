package com.it.utils;

import java.util.List;

import com.it.bean.DaoMaster;
import com.it.bean.DaoMaster.DevOpenHelper;
import com.it.bean.DaoSession;
import com.it.bean.UserInfo;
import com.it.bean.UserInfoDao;
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
	private  DaoMaster daoMaster=null;
	private  DaoSession daoSession=null;
	private  UserInfoDao userdao=null;
	
	
	private SqlDataUtil(Context context){
			if(daoMaster==null){
				DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "if", null);
				try {
					daoMaster=new DaoMaster(helper.getWritableDatabase());
					if(daoSession==null){
						daoSession=daoMaster.newSession();
					}
					if(userdao==null){
						userdao=daoSession.getUserInfoDao();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
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
			QueryBuilder<UserInfo> qb = userdao.queryBuilder();
			List<UserInfo> list = qb.list();
			for (UserInfo userInfo : list) {
				
				if(userInfo.getMobile().equals(user.getMobile())){
					user.setId(userInfo.getId());
				}
			}
			userdao.insertOrReplace(user);
		} catch (Exception e) {
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
			QueryBuilder<UserInfo> qb = userdao.queryBuilder();
			list=qb.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	


}
