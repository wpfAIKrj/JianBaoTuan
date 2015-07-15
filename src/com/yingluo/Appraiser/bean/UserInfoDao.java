package com.yingluo.Appraiser.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yingluo.Appraiser.bean.UserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table USER_INFO.
*/
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nickname = new Property(1, String.class, "nickname", false, "NICKNAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property Mobile = new Property(3, String.class, "mobile", false, "MOBILE");
        public final static Property Portrait = new Property(4, String.class, "portrait", false, "PORTRAIT");
        public final static Property User_type = new Property(5, Integer.class, "user_type", false, "USER_TYPE");
        public final static Property User_level = new Property(6, Integer.class, "user_level", false, "USER_LEVEL");
        public final static Property Personal_data = new Property(7, String.class, "personal_data", false, "PERSONAL_DATA");
        public final static Property Is_valid = new Property(8, Integer.class, "is_valid", false, "IS_VALID");
        public final static Property Is_famous_expert = new Property(9, Integer.class, "is_famous_expert", false, "IS_FAMOUS_EXPERT");
        public final static Property Is_system = new Property(10, Integer.class, "is_system", false, "IS_SYSTEM");
        public final static Property Is_bind = new Property(11, Integer.class, "is_bind", false, "IS_BIND");
        public final static Property Insert_time = new Property(12, Integer.class, "insert_time", false, "INSERT_TIME");
        public final static Property Session_id = new Property(13, String.class, "session_id", false, "SESSION_ID");
        public final static Property Avatar = new Property(14, String.class, "avatar", false, "AVATAR");
        public final static Property Image_token = new Property(15, String.class, "image_token", false, "IMAGE_TOKEN");
        public final static Property Message_token = new Property(16, String.class, "message_token", false, "MESSAGE_TOKEN");
        public final static Property Qq = new Property(17, String.class, "qq", false, "QQ");
        public final static Property Email = new Property(18, String.class, "email", false, "EMAIL");
        public final static Property Treasure_number = new Property(19, Integer.class, "treasure_number", false, "TREASURE_NUMBER");
        public final static Property Treasure_record_number = new Property(20, Integer.class, "treasure_record_number", false, "TREASURE_RECORD_NUMBER");
        public final static Property Foot_number = new Property(21, Integer.class, "foot_number", false, "FOOT_NUMBER");
        public final static Property Description = new Property(22, String.class, "description", false, "DESCRIPTION");
    };


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NICKNAME' TEXT," + // 1: nickname
                "'PASSWORD' TEXT," + // 2: password
                "'MOBILE' TEXT," + // 3: mobile
                "'PORTRAIT' TEXT," + // 4: portrait
                "'USER_TYPE' INTEGER," + // 5: user_type
                "'USER_LEVEL' INTEGER," + // 6: user_level
                "'PERSONAL_DATA' TEXT," + // 7: personal_data
                "'IS_VALID' INTEGER," + // 8: is_valid
                "'IS_FAMOUS_EXPERT' INTEGER," + // 9: is_famous_expert
                "'IS_SYSTEM' INTEGER," + // 10: is_system
                "'IS_BIND' INTEGER," + // 11: is_bind
                "'INSERT_TIME' INTEGER," + // 12: insert_time
                "'SESSION_ID' TEXT," + // 13: session_id
                "'AVATAR' TEXT," + // 14: avatar
                "'IMAGE_TOKEN' TEXT," + // 15: image_token
                "'MESSAGE_TOKEN' TEXT," + // 16: message_token
                "'QQ' TEXT," + // 17: qq
                "'EMAIL' TEXT," + // 18: email
                "'TREASURE_NUMBER' INTEGER," + // 19: treasure_number
                "'TREASURE_RECORD_NUMBER' INTEGER," + // 20: treasure_record_number
                "'FOOT_NUMBER' INTEGER," + // 21: foot_number
                "'DESCRIPTION' TEXT);"); // 22: description
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(2, nickname);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(4, mobile);
        }
 
        String portrait = entity.getPortrait();
        if (portrait != null) {
            stmt.bindString(5, portrait);
        }
 
        Integer user_type = entity.getUser_type();
        if (user_type != null) {
            stmt.bindLong(6, user_type);
        }
 
        Integer user_level = entity.getUser_level();
        if (user_level != null) {
            stmt.bindLong(7, user_level);
        }
 
        String personal_data = entity.getPersonal_data();
        if (personal_data != null) {
            stmt.bindString(8, personal_data);
        }
 
        Integer is_valid = entity.getIs_valid();
        if (is_valid != null) {
            stmt.bindLong(9, is_valid);
        }
 
        Integer is_famous_expert = entity.getIs_famous_expert();
        if (is_famous_expert != null) {
            stmt.bindLong(10, is_famous_expert);
        }
 
        Integer is_system = entity.getIs_system();
        if (is_system != null) {
            stmt.bindLong(11, is_system);
        }
 
        Integer is_bind = entity.getIs_bind();
        if (is_bind != null) {
            stmt.bindLong(12, is_bind);
        }
 
        Integer insert_time = entity.getInsert_time();
        if (insert_time != null) {
            stmt.bindLong(13, insert_time);
        }
 
        String session_id = entity.getSession_id();
        if (session_id != null) {
            stmt.bindString(14, session_id);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(15, avatar);
        }
 
        String image_token = entity.getImage_token();
        if (image_token != null) {
            stmt.bindString(16, image_token);
        }
 
        String message_token = entity.getMessage_token();
        if (message_token != null) {
            stmt.bindString(17, message_token);
        }
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(18, qq);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(19, email);
        }
 
        Integer treasure_number = entity.getTreasure_number();
        if (treasure_number != null) {
            stmt.bindLong(20, treasure_number);
        }
 
        Integer treasure_record_number = entity.getTreasure_record_number();
        if (treasure_record_number != null) {
            stmt.bindLong(21, treasure_record_number);
        }
 
        Integer foot_number = entity.getFoot_number();
        if (foot_number != null) {
            stmt.bindLong(22, foot_number);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(23, description);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nickname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mobile
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // portrait
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // user_type
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // user_level
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // personal_data
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // is_valid
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // is_famous_expert
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // is_system
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // is_bind
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // insert_time
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // session_id
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // avatar
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // image_token
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // message_token
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // qq
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // email
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // treasure_number
            cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // treasure_record_number
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // foot_number
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22) // description
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNickname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMobile(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPortrait(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUser_type(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setUser_level(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setPersonal_data(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIs_valid(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setIs_famous_expert(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setIs_system(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setIs_bind(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setInsert_time(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setSession_id(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setAvatar(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setImage_token(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setMessage_token(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setQq(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setEmail(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setTreasure_number(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setTreasure_record_number(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setFoot_number(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setDescription(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(UserInfo entity) {
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
