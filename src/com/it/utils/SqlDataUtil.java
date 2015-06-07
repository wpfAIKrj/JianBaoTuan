package com.it.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.it.bean.ContentInfo;
import com.it.bean.ContentInfoDao;
import com.it.bean.DaoMaster;
import com.it.bean.DaoMaster.DevOpenHelper;
import com.it.bean.DaoSession;
import com.it.bean.TreasureType;
import com.it.bean.TreasureTypeDao;
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
	private  UserInfoDao userdao=null;//用户
	private ContentInfoDao infoDao=null;//文章详细
	private TreasureTypeDao typeDao=null;//宝物分类
	
	
	
	
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
					if(typeDao==null){
						typeDao=daoSession.getTreasureTypeDao();
					}
					if(infoDao==null){
						infoDao=daoSession.getContentInfoDao();
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

	/**
	 * 保存宝物分类信息
	 * @param data
	 */
	public void saveContentType(List<TreasureType> data) {
		// TODO Auto-generated method stub
		QueryBuilder<TreasureType> qb =null;
		try {
			for (int i = 0; i < data.size(); i++) {
				TreasureType newdata = data.get(i);
				 qb = typeDao.queryBuilder();	
				/* qb.where(TreasureTypeDao.Properties.Type.eq(newdata.getType()),
						 TreasureTypeDao.Properties.Currnt_id.eq(newdata.getCurrnt_id())
						 ,TreasureTypeDao.Properties.Parent_id.eq(newdata.getParent_id()));
				 TreasureType olddata = qb.unique();
				 if(olddata!=null){
					 newdata.setId(olddata.getId());
				 }*/
				 typeDao.insertOrReplace(newdata);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 保存宝物分类信息
	 * @param data
	 */
	public void saveContentType(TreasureType data){
		 try {
			typeDao.insertOrReplace(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有分类列表
	 * @return
	 */
	public List<TreasureType> getTreasureType() {
		// TODO Auto-generated method stub
		ArrayList<TreasureType> list=new ArrayList<TreasureType>();
		QueryBuilder<TreasureType> qb = typeDao.queryBuilder();
		qb.list();
		/*qb.where(TreasureTypeDao.Properties.Type.eq(TreasureType.TYPE_FIRST))
		.orderAsc(TreasureTypeDao.Properties.Currnt_id);*/
		list=(qb.list()!=null)?(ArrayList<TreasureType>) qb.list():new ArrayList<TreasureType>();
		return list;
	}

	/**
	 * 获取制定2级列表
	 * @param parent_id 1级列表id
	 * @return 2级列表
	 *//*
	public ArrayList<TreasureType> getSecondTreasureType(long parent_id) {
		// TODO Auto-generated method stub
		ArrayList<TreasureType> list=new ArrayList<TreasureType>();
		QueryBuilder<TreasureType> qb = typeDao.queryBuilder();
		qb.where(TreasureTypeDao.Properties.Type.eq(TreasureType.TYPE_SECOND),TreasureTypeDao.Properties.Parent_id.eq(parent_id))
		.orderAsc(TreasureTypeDao.Properties.Currnt_id);
		list=(qb.list()!=null)?(ArrayList<TreasureType>) qb.list():new ArrayList<TreasureType>();
		return list;
	}*/
	
	
	


}
