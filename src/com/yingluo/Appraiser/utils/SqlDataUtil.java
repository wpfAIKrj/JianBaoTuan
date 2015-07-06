package com.yingluo.Appraiser.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import u.aly.cu;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.exception.DbException;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.bean.ContentInfoDao;
import com.yingluo.Appraiser.bean.DaoMaster;
import com.yingluo.Appraiser.bean.DaoMaster.DevOpenHelper;
import com.yingluo.Appraiser.bean.DaoSession;
import com.yingluo.Appraiser.bean.ImUserInfo;
import com.yingluo.Appraiser.bean.ImUserInfoDao;
import com.yingluo.Appraiser.bean.SystemInfoEntity;
import com.yingluo.Appraiser.bean.SystemInfoEntityDao;
import com.yingluo.Appraiser.bean.TreasureType;
import com.yingluo.Appraiser.bean.TreasureTypeDao;
import com.yingluo.Appraiser.bean.UserInfo;
import com.yingluo.Appraiser.bean.UserInfoDao;
import com.yingluo.Appraiser.config.Const;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
	private ImUserInfoDao imdao=null;//聊天用户信息本地
	private SQLiteDatabase db=null;//数据库对象
	private SystemInfoEntityDao systemdao=null;//系统消息
	
	
	private SqlDataUtil(Context context){
			if(daoMaster==null){
				DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "if", null);
				try {
					 db = helper.getWritableDatabase();
					daoMaster=new DaoMaster(db);
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
					if(imdao==null){
						imdao=daoSession.getImUserInfoDao();
					}
					if(systemdao==null){
						systemdao=daoSession.getSystemInfoEntityDao();
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
			userdao.insertOrReplace(user);
			ImUserInfo im=new ImUserInfo(user.getId(), user.getNickname(), user.getAvatar());
			saveIMUserinfo(im);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过手机获取指定的用户
	 * @param moblie
	 */
	public UserInfo getUserForPhone(String moblie){
		QueryBuilder<UserInfo> qb = userdao.queryBuilder();
		qb.where( UserInfoDao.Properties.Mobile.eq(moblie));
		UserInfo user = qb.unique();
		return user;
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
		try {
			qb.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*qb.where(TreasureTypeDao.Properties.Type.eq(TreasureType.TYPE_FIRST))
		.orderAsc(TreasureTypeDao.Properties.Currnt_id);*/
		list=(qb.list()!=null)?(ArrayList<TreasureType>) qb.list():new ArrayList<TreasureType>();
		return list;
	}

	/**
	 * 指定最低级，来获取你父级的标签
	 * @param type 最低级的宝物
	 * @return 包含起的宝贝列表
	 */
	public ArrayList<TreasureType> getTreasureTypeByChild(TreasureType type) {
		// TODO 自动生成的方法存根
		 ArrayList<TreasureType> list=new ArrayList<TreasureType>();
		 list.add(type);
		 
		 TreasureType start=type;
		 do{
			 if(start.parent_id!=0){
				 start=getSTreasureType((start.type-1),start.parent_id);
				 if(start!=null){
					 list.add(start);
				 }
				 
			 }else{
				 start=null;
			 }
			 
		 }while(start!=null);
		return list;
	}
	/**
	 * 获取指定的宝贝类别信息
	 * @param id id
	 * @param type 第几级
	 * @return 返回该宝物信息
	 */
	public TreasureType getSTreasureType(int type,long id) {
		// TODO Auto-generated method stub
		TreasureType data=null;
		QueryBuilder<TreasureType> qb = typeDao.queryBuilder();
		try {
			qb.where(TreasureTypeDao.Properties.Type.eq(type),TreasureTypeDao.Properties.Id.eq(id));
			data=qb.uniqueOrThrow();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 返回宝贝类型数据列表
	 * @param name 宝贝名字
	 * @return
	 */
	public List<TreasureType> getSelectTreasureType(String name){
		List<TreasureType> list=new ArrayList<TreasureType>();
		
		QueryBuilder<TreasureType> qb = typeDao.queryBuilder();
		qb.where(TreasureTypeDao.Properties.IsChild.eq(true),TreasureTypeDao.Properties.Name.like("%" + name + "%"));//, TreasureTypeDao.Properties.Name.eq());
		list=qb.list();
		if(list==null){
			list=new ArrayList<TreasureType>();
		}
		return list;
	}
	/**
	 * 清空用户信息表
	 */
	public void clearUserinfo() {
		// TODO 自动生成的方法存根
		userdao.deleteAll();
	}

	/**
	 * 返回一个聊天的用户数据
	 * @param arg0 用户id
	 * @return
	 */
	public io.rong.imlib.model.UserInfo getImUser(String arg0) {
		// TODO 自动生成的方法存根
		io.rong.imlib.model.UserInfo user=null;
		try {
			long id=Long.parseLong(arg0);
			QueryBuilder<ImUserInfo> qb = imdao.queryBuilder();
			qb.where(ImUserInfoDao.Properties.Id.eq(id));
			ImUserInfo old = qb.unique();
			if(old!=null){
				user=new io.rong.imlib.model.UserInfo(arg0,old.getName(),
						(old.getIcon()!=null)?Uri.parse(old.getIcon()):null);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 保存聊天用户基本信息在本地
	 * @param imuser
	 */
	public void saveIMUserinfo(ImUserInfo imuser){
		imdao.insertOrReplace(imuser);
	}

	
	/**
	 * 保存系统消息列表
	 */
	public void saveSystemInfoList(ArrayList<SystemInfoEntity> list){		
		if(list!=null&&!list.isEmpty()){
			systemdao.deleteAll();
			systemdao.insertInTx(list);
//			for (int i = 0; i < list.size(); i++) {
//				saveSystemInfo(list.get(i));				
//			}
		}
	}
	
	/**
	 * 保存系统消息列表
	 * @param entity
	 */
	public void saveSystemInfo(SystemInfoEntity entity){
		QueryBuilder<SystemInfoEntity> qb = systemdao.queryBuilder();
		qb.where(SystemInfoEntityDao.Properties.Mobile.eq(entity.getMobile()), 
				SystemInfoEntityDao.Properties.Treasure_id.eq(entity.treasure_id)
				,SystemInfoEntityDao.Properties.Time.eq(entity.time));
		SystemInfoEntity old = qb.unique();
		if(old!=null){
			entity.setId(old.getId());
		}
		systemdao.insertOrReplace(entity);
	}
	
	/**
	 * 获取某一用户的系统消息
	 * @param mobile
	 */
	public ArrayList<SystemInfoEntity> getSystemInfoList(String mobile){
		if(mobile==null||mobile.isEmpty()){
			return new ArrayList<SystemInfoEntity>();
		}
		List<SystemInfoEntity> list=null;
		QueryBuilder<SystemInfoEntity> qb = systemdao.queryBuilder();
		qb.where(SystemInfoEntityDao.Properties.Mobile.eq(mobile));
		list = qb.list();
		if(list==null){
			return new ArrayList<SystemInfoEntity>();
		}
		return (ArrayList<SystemInfoEntity>) list;
	}


}
