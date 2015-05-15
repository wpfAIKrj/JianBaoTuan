package com.it.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.it.bean.SuggestionInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SUGGESTION_INFO.
*/
public class SuggestionInfoDao extends AbstractDao<SuggestionInfo, Long> {

    public static final String TABLENAME = "SUGGESTION_INFO";

    /**
     * Properties of entity SuggestionInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id","SUGGESTION_INFO");
        public final static Property User_id = new Property(1, String.class, "user_id", false, "USER_ID","SUGGESTION_INFO");
        public final static Property Suggestion = new Property(2, String.class, "suggestion", false, "SUGGESTION","SUGGESTION_INFO");
        public final static Property Is_valid = new Property(3, Integer.class, "is_valid", false, "IS_VALID","SUGGESTION_INFO");
        public final static Property Insert_time = new Property(4, java.util.Date.class, "insert_time", false, "INSERT_TIME","SUGGESTION_INFO");
    };


    public SuggestionInfoDao(DaoConfig config) {
        super(config);
    }
    
    public SuggestionInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SUGGESTION_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' TEXT," + // 1: user_id
                "'SUGGESTION' TEXT," + // 2: suggestion
                "'IS_VALID' INTEGER," + // 3: is_valid
                "'INSERT_TIME' INTEGER);"); // 4: insert_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SUGGESTION_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SuggestionInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(2, user_id);
        }
 
        String suggestion = entity.getSuggestion();
        if (suggestion != null) {
            stmt.bindString(3, suggestion);
        }
 
        Integer is_valid = entity.getIs_valid();
        if (is_valid != null) {
            stmt.bindLong(4, is_valid);
        }
 
        java.util.Date insert_time = entity.getInsert_time();
        if (insert_time != null) {
            stmt.bindLong(5, insert_time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SuggestionInfo readEntity(Cursor cursor, int offset) {
        SuggestionInfo entity = new SuggestionInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // suggestion
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // is_valid
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // insert_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SuggestionInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSuggestion(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIs_valid(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setInsert_time(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SuggestionInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SuggestionInfo entity) {
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