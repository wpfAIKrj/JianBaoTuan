package com.yingluo.Appraiser.bean;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table TREASURE_TYPE.
 */
public class TreasureType implements Serializable{
	public static final int TYPE_FIRST = 1;//第一层
	public static final int TYPE_SECOND = 2;//第二层
	public static final int TYPE_THIRD = 3;//第三层
	
    public long id; 
    public long currnt_id; //宝物id
    public String name;//宝物名字
    public Integer type;//宝物级别
    public long parent_id;//父类id
    public Boolean isChild;

    public int position;
    
    public TreasureType() {
    }

    public TreasureType(Long id) {
        this.id = id;
    }

    public TreasureType(Long id, Long currnt_id, String name, Integer type, Long parent_id, Boolean isChild) {
        this.id = id;
        this.currnt_id = currnt_id;
        this.name = name;
        this.type = type;
        this.parent_id = parent_id;
        this.isChild = isChild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrnt_id() {
        return currnt_id;
    }

    public void setCurrnt_id(Long currnt_id) {
        this.currnt_id = currnt_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    @Override
	public String toString() {
		return "TreasureType [id=" + id + ", currnt_id=" + currnt_id
				+ ", name=" + name + ", type=" + type + ", parent_id="
				+ parent_id + ", isChild=" + isChild + "]";
	}

	public void setType(Integer type) {
        this.type = type;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean getIsChild() {
        return isChild;
    }

    public void setIsChild(Boolean isChild) {
        this.isChild = isChild;
    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
    
    

}
