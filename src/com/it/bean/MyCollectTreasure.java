package com.it.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table MY_COLLECT_TREASURE.
 */
public class MyCollectTreasure {

    private Long id;
    private Integer user_id;
    private Integer treasure_id;
    private Integer is_valid;
    private java.util.Date insert_time;

    public MyCollectTreasure() {
    }

    public MyCollectTreasure(Long id) {
        this.id = id;
    }

    public MyCollectTreasure(Long id, Integer user_id, Integer treasure_id, Integer is_valid, java.util.Date insert_time) {
        this.id = id;
        this.user_id = user_id;
        this.treasure_id = treasure_id;
        this.is_valid = is_valid;
        this.insert_time = insert_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTreasure_id() {
        return treasure_id;
    }

    public void setTreasure_id(Integer treasure_id) {
        this.treasure_id = treasure_id;
    }

    public Integer getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    public java.util.Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(java.util.Date insert_time) {
        this.insert_time = insert_time;
    }

}
