package com.it.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.it.bean.UserInfoDao;
import com.it.bean.MyTreasureDao;
import com.it.bean.TreasureMaterialClassifyDao;
import com.it.bean.ContentInfoDao;
import com.it.bean.MyCollectTreasureDao;
import com.it.bean.MyCollectContentDao;
import com.it.bean.MySystemNoticeDao;
import com.it.bean.MyFootprintsDao;
import com.it.bean.RTreasureCommentDao;
import com.it.bean.MyAppraisalTreasureDao;
import com.it.bean.SuggestionInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        UserInfoDao.createTable(db, ifNotExists);
        MyTreasureDao.createTable(db, ifNotExists);
        TreasureMaterialClassifyDao.createTable(db, ifNotExists);
        ContentInfoDao.createTable(db, ifNotExists);
        MyCollectTreasureDao.createTable(db, ifNotExists);
        MyCollectContentDao.createTable(db, ifNotExists);
        MySystemNoticeDao.createTable(db, ifNotExists);
        MyFootprintsDao.createTable(db, ifNotExists);
        RTreasureCommentDao.createTable(db, ifNotExists);
        MyAppraisalTreasureDao.createTable(db, ifNotExists);
        SuggestionInfoDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        UserInfoDao.dropTable(db, ifExists);
        MyTreasureDao.dropTable(db, ifExists);
        TreasureMaterialClassifyDao.dropTable(db, ifExists);
        ContentInfoDao.dropTable(db, ifExists);
        MyCollectTreasureDao.dropTable(db, ifExists);
        MyCollectContentDao.dropTable(db, ifExists);
        MySystemNoticeDao.dropTable(db, ifExists);
        MyFootprintsDao.dropTable(db, ifExists);
        RTreasureCommentDao.dropTable(db, ifExists);
        MyAppraisalTreasureDao.dropTable(db, ifExists);
        SuggestionInfoDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(UserInfoDao.class);
        registerDaoClass(MyTreasureDao.class);
        registerDaoClass(TreasureMaterialClassifyDao.class);
        registerDaoClass(ContentInfoDao.class);
        registerDaoClass(MyCollectTreasureDao.class);
        registerDaoClass(MyCollectContentDao.class);
        registerDaoClass(MySystemNoticeDao.class);
        registerDaoClass(MyFootprintsDao.class);
        registerDaoClass(RTreasureCommentDao.class);
        registerDaoClass(MyAppraisalTreasureDao.class);
        registerDaoClass(SuggestionInfoDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
