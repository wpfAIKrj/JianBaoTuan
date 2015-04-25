package com.it.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.it.model.TreasureMaterialClassify;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TREASURE_MATERIAL_CLASSIFY.
*/
public class TreasureMaterialClassifyDao extends AbstractDao<TreasureMaterialClassify, Long> {

    public static final String TABLENAME = "TREASURE_MATERIAL_CLASSIFY";

    /**
     * Properties of entity TreasureMaterialClassify.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Classify_name = new Property(1, String.class, "classify_name", false, "CLASSIFY_NAME");
        public final static Property Current_level = new Property(2, Integer.class, "current_level", false, "CURRENT_LEVEL");
        public final static Property Parent_id = new Property(3, Integer.class, "parent_id", false, "PARENT_ID");
        public final static Property Classify_path = new Property(4, String.class, "classify_path", false, "CLASSIFY_PATH");
        public final static Property Is_valid = new Property(5, Integer.class, "is_valid", false, "IS_VALID");
        public final static Property Insert_time = new Property(6, java.util.Date.class, "insert_time", false, "INSERT_TIME");
    };


    public TreasureMaterialClassifyDao(DaoConfig config) {
        super(config);
    }
    
    public TreasureMaterialClassifyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TREASURE_MATERIAL_CLASSIFY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'CLASSIFY_NAME' TEXT," + // 1: classify_name
                "'CURRENT_LEVEL' INTEGER," + // 2: current_level
                "'PARENT_ID' INTEGER," + // 3: parent_id
                "'CLASSIFY_PATH' TEXT," + // 4: classify_path
                "'IS_VALID' INTEGER," + // 5: is_valid
                "'INSERT_TIME' INTEGER);"); // 6: insert_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TREASURE_MATERIAL_CLASSIFY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TreasureMaterialClassify entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classify_name = entity.getClassify_name();
        if (classify_name != null) {
            stmt.bindString(2, classify_name);
        }
 
        Integer current_level = entity.getCurrent_level();
        if (current_level != null) {
            stmt.bindLong(3, current_level);
        }
 
        Integer parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindLong(4, parent_id);
        }
 
        String classify_path = entity.getClassify_path();
        if (classify_path != null) {
            stmt.bindString(5, classify_path);
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
    public TreasureMaterialClassify readEntity(Cursor cursor, int offset) {
        TreasureMaterialClassify entity = new TreasureMaterialClassify( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // classify_name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // current_level
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // parent_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // classify_path
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // is_valid
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // insert_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TreasureMaterialClassify entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClassify_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCurrent_level(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setParent_id(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setClassify_path(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIs_valid(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setInsert_time(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TreasureMaterialClassify entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TreasureMaterialClassify entity) {
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
