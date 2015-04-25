package com.it.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.it.model.MySystemNotice;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MY_SYSTEM_NOTICE.
*/
public class MySystemNoticeDao extends AbstractDao<MySystemNotice, Long> {

    public static final String TABLENAME = "MY_SYSTEM_NOTICE";

    /**
     * Properties of entity MySystemNotice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property User_id = new Property(1, Integer.class, "user_id", false, "USER_ID");
        public final static Property Notice_data = new Property(2, String.class, "notice_data", false, "NOTICE_DATA");
        public final static Property Notice_type = new Property(3, Integer.class, "notice_type", false, "NOTICE_TYPE");
        public final static Property Is_read = new Property(4, Integer.class, "is_read", false, "IS_READ");
        public final static Property Is_valid = new Property(5, Integer.class, "is_valid", false, "IS_VALID");
        public final static Property Insert_time = new Property(6, java.util.Date.class, "insert_time", false, "INSERT_TIME");
    };


    public MySystemNoticeDao(DaoConfig config) {
        super(config);
    }
    
    public MySystemNoticeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MY_SYSTEM_NOTICE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' INTEGER," + // 1: user_id
                "'NOTICE_DATA' TEXT," + // 2: notice_data
                "'NOTICE_TYPE' INTEGER," + // 3: notice_type
                "'IS_READ' INTEGER," + // 4: is_read
                "'IS_VALID' INTEGER," + // 5: is_valid
                "'INSERT_TIME' INTEGER);"); // 6: insert_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MY_SYSTEM_NOTICE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MySystemNotice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(2, user_id);
        }
 
        String notice_data = entity.getNotice_data();
        if (notice_data != null) {
            stmt.bindString(3, notice_data);
        }
 
        Integer notice_type = entity.getNotice_type();
        if (notice_type != null) {
            stmt.bindLong(4, notice_type);
        }
 
        Integer is_read = entity.getIs_read();
        if (is_read != null) {
            stmt.bindLong(5, is_read);
        }
 
        Integer is_valid = entity.getIs_valid();
        if (is_valid != null) {
            stmt.bindLong(6, is_valid);
        }
 
        java.util.Date insert_time = entity.getInsert_time();
        if (insert_time != null) {
            stmt.bindLong(7, insert_time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MySystemNotice readEntity(Cursor cursor, int offset) {
        MySystemNotice entity = new MySystemNotice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // notice_data
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // notice_type
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // is_read
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // is_valid
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // insert_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MySystemNotice entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setNotice_data(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNotice_type(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setIs_read(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setIs_valid(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setInsert_time(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MySystemNotice entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MySystemNotice entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
