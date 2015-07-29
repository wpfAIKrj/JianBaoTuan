package com.yingluo.Appraiser.bean;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.annotations.SerializedName;

/**
 * @author ytmfdw 宝贝实体类
 * */
public class TreasureEntity implements Serializable {

	@SerializedName("image")
	public String image;
	@SerializedName("title")
	public String title;
	@SerializedName("kinds")
	public List<Kind> kinds;
	@SerializedName("treasure_id")
	public long treasure_id;
	@SerializedName("user_id")
	public long user_id;
	// 状态
	public int status;

	// 删除是否被选择
	public boolean isSelect = false;
		
	public TreasureEntity(String image, String title, List<Kind> kinds,
			long treasure_id,long user_id) {
		super();
		this.image = image;
		this.title = title;
		this.kinds = kinds;
		this.treasure_id = treasure_id;
		this.user_id = user_id;
	}

	public TreasureEntity() {
		super();
	}

	@Override
	public String toString() {
		return "TreasureEntity [image=" + image + ", title=" + title
				+ ", kinds="  + ", treasure_id=" + treasure_id
				+ ", status=" + status + "]";
	}

	
	public static class Kind{
		@SerializedName("name")
		public String name;
		@SerializedName("id")
		public long id;
	}
}
