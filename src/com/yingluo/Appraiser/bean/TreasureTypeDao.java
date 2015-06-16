package com.yingluo.Appraiser.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yingluo.Appraiser.bean.TreasureType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TREASURE_TYPE.
*/
public class TreasureTypeDao extends AbstractDao<TreasureType, Long> {

    public static final String TABLENAME = "TREASURE_TYPE";

    /**
     * Properties of entity TreasureType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id","TREASURE_TYPE");
        public final static Property Currnt_id = new Property(1, Long.class, "currnt_id", false, "CURRNT_ID","TREASURE_TYPE");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME","TREASURE_TYPE");
        public final static Property Type = new Property(3, Integer.class, "type", false, "TYPE","TREASURE_TYPE");
        public final static Property Parent_id = new Property(4, Long.class, "parent_id", false, "PARENT_ID","TREASURE_TYPE");
        public final static Property IsChild = new Property(5, Boolean.class, "isChild", false, "IS_CHILD","TREASURE_TYPE");
    };


    public TreasureTypeDao(DaoConfig config) {
        super(config);
    }
    
    public TreasureTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TREASURE_TYPE' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'CURRNT_ID' INTEGER," + // 1: currnt_id
                "'NAME' TEXT," + // 2: name
                "'TYPE' INTEGER," + // 3: type
                "'PARENT_ID' INTEGER," + // 4: parent_id
                "'IS_CHILD' INTEGER);"); // 5: isChild
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TREASURE_TYPE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TreasureType entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long currnt_id = entity.getCurrnt_id();
        if (currnt_id != null) {
            stmt.bindLong(2, currnt_id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }
 
        Long parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindLong(5, parent_id);
        }
 
        Boolean isChild = entity.getIsChild();
        if (isChild != null) {
            stmt.bindLong(6, isChild ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public TreasureType readEntity(Cursor cursor, int offset) {
        TreasureType entity = new TreasureType( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // currnt_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // parent_id
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0 // isChild
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TreasureType entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCurrnt_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setParent_id(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setIsChild(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TreasureType entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TreasureType entity) {
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
