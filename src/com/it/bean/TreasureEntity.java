package com.it.bean;

import java.io.Serializable;
import java.util.Arrays;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

/**
 * @author ytmfdw 宝贝实体类
 * */
public class TreasureEntity implements Serializable {

	@SerializedName("id")
	public long id;
	@SerializedName("user_id")
	public long user_id;
	@SerializedName("treasure_description")
	public String treasure_description;
	@SerializedName("treasure_all_view_data")
	public String[] treasure_all_view_data;
	@SerializedName("treasure_special_view_data")
	public String[] treasure_special_view_data;
	@SerializedName("treasure_level")
	public String treasure_level;
	@SerializedName("treasure_classify")
	public String treasure_classify;
	@SerializedName("treasure_classify_id")
	public long treasure_classify_id;
	@SerializedName("status")
	public int status;
	@SerializedName("is_special")
	public int is_special;
	@SerializedName("is_hot")
	public int is_hot;
	@SerializedName("is_valid")
	public int is_valid;
	@SerializedName("insert_time")
	public String insert_time;
	@SerializedName("appraiser_id")
	public long appraiser_id;
	@SerializedName("view_times")
	public long view_times;
	@SerializedName("author_info")
	public JSONObject author_info;

	// public UserInfo user_info;

	public TreasureEntity() {
		super();
	}

	public TreasureEntity(long id, long user_id, String treasure_description,
			String[] treasure_all_view_data,
			String[] treasure_special_view_data, String treasure_level,
			String treasure_classify, long treasure_classify_id, int status,
			int is_special, int is_hot, int is_valid, String insert_time,
			long appraiser_id, long view_times, JSONObject user_info) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.treasure_description = treasure_description;
		this.treasure_all_view_data = treasure_all_view_data;
		this.treasure_special_view_data = treasure_special_view_data;
		this.treasure_level = treasure_level;
		this.treasure_classify = treasure_classify;
		this.treasure_classify_id = treasure_classify_id;
		this.status = status;
		this.is_special = is_special;
		this.is_hot = is_hot;
		this.is_valid = is_valid;
		this.insert_time = insert_time;
		this.appraiser_id = appraiser_id;
		this.view_times = view_times;
		this.author_info = user_info;
	}

	@Override
	public String toString() {
		return "TreasureEntity [id=" + id + ", user_id=" + user_id
				+ ", treasure_description=" + treasure_description
				+ ", treasure_all_view_data="
				+ Arrays.toString(treasure_all_view_data)
				+ ", treasure_special_view_data="
				+ Arrays.toString(treasure_special_view_data)
				+ ", treasure_level=" + treasure_level + ", treasure_classify="
				+ treasure_classify + ", treasure_classify_id="
				+ treasure_classify_id + ", status=" + status + ", is_special="
				+ is_special + ", is_hot=" + is_hot + ", is_valid=" + is_valid
				+ ", insert_time=" + insert_time + ", appraiser_id="
				+ appraiser_id + ", view_times=" + view_times + ", user_info="
				+ author_info + "]";
	}

}
