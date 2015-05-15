package com.it.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.it.bean.MyTreasure;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MY_TREASURE.
*/
public class MyTreasureDao extends AbstractDao<MyTreasure, Long> {

    public static final String TABLENAME = "MY_TREASURE";

    /**
     * Properties of entity MyTreasure.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id","MY_TREASURE");
        public final static Property User_id = new Property(1, String.class, "user_id", false, "USER_ID","MY_TREASURE");
        public final static Property Treasure_description = new Property(2, String.class, "treasure_description", false, "TREASURE_DESCRIPTION","MY_TREASURE");
        public final static Property Treasure_all_view_data = new Property(3, String.class, "treasure_all_view_data", false, "TREASURE_ALL_VIEW_DATA","MY_TREASURE");
        public final static Property Treasure_special_view_data = new Property(4, String.class, "treasure_special_view_data", false, "TREASURE_SPECIAL_VIEW_DATA","MY_TREASURE");
        public final static Property Treasure_level = new Property(5, Integer.class, "treasure_level", false, "TREASURE_LEVEL","MY_TREASURE");
        public final static Property Treasure_classify = new Property(6, String.class, "treasure_classify", false, "TREASURE_CLASSIFY","MY_TREASURE");
        public final static Property Is_appraisal = new Property(7, Integer.class, "is_appraisal", false, "IS_APPRAISAL","MY_TREASURE");
        public final static Property Is_special = new Property(8, Integer.class, "is_special", false, "IS_SPECIAL","MY_TREASURE");
        public final static Property Is_valid = new Property(9, Integer.class, "is_valid", false, "IS_VALID","MY_TREASURE");
        public final static Property Insert_time = new Property(10, java.util.Date.class, "insert_time", false, "INSERT_TIME","MY_TREASURE");
    };


    public MyTreasureDao(DaoConfig config) {
        super(config);
    }
    
    public MyTreasureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MY_TREASURE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' TEXT," + // 1: user_id
                "'TREASURE_DESCRIPTION' TEXT," + // 2: treasure_description
                "'TREASURE_ALL_VIEW_DATA' TEXT," + // 3: treasure_all_view_data
                "'TREASURE_SPECIAL_VIEW_DATA' TEXT," + // 4: treasure_special_view_data
                "'TREASURE_LEVEL' INTEGER," + // 5: treasure_level
                "'TREASURE_CLASSIFY' TEXT," + // 6: treasure_classify
                "'IS_APPRAISAL' INTEGER," + // 7: is_appraisal
                "'IS_SPECIAL' INTEGER," + // 8: is_special
                "'IS_VALID' INTEGER," + // 9: is_valid
                "'INSERT_TIME' INTEGER);"); // 10: insert_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MY_TREASURE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MyTreasure entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(2, user_id);
        }
 
        String treasure_description = entity.getTreasure_description();
        if (treasure_description != null) {
            stmt.bindString(3, treasure_description);
        }
 
        String treasure_all_view_data = entity.getTreasure_all_view_data();
        if (treasure_all_view_data != null) {
            stmt.bindString(4, treasure_all_view_data);
        }
 
        String treasure_special_view_data = entity.getTreasure_special_view_data();
        if (treasure_special_view_data != null) {
            stmt.bindString(5, treasure_special_view_data);
        }
 
        Integer treasure_level = entity.getTreasure_level();
        if (treasure_level != null) {
            stmt.bindLong(6, treasure_level);
        }
 
        String treasure_classify = entity.getTreasure_classify();
        if (treasure_classify != null) {
            stmt.bindString(7, treasure_classify);
        }
 
        Integer is_appraisal = entity.getIs_appraisal();
        if (is_appraisal != null) {
            stmt.bindLong(8, is_appraisal);
        }
 
        Integer is_special = entity.getIs_special();
        if (is_special != null) {
            stmt.bindLong(9, is_special);
        }
 
        Integer is_valid = entity.getIs_valid();
        if (is_valid != null) {
            stmt.bindLong(10, is_valid);
        }
 
        java.util.Date insert_time = entity.getInsert_time();
        if (insert_time != null) {
            stmt.bindLong(11, insert_time.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MyTreasure readEntity(Cursor cursor, int offset) {
        MyTreasure entity = new MyTreasure( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // treasure_description
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // treasure_all_view_data
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // treasure_special_view_data
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // treasure_level
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // treasure_classify
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // is_appraisal
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // is_special
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // is_valid
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)) // insert_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MyTreasure entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTreasure_description(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTreasure_all_view_data(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTreasure_special_view_data(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTreasure_level(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setTreasure_classify(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIs_appraisal(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setIs_special(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setIs_valid(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setInsert_time(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MyTreasure entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MyTreasure entity) {
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