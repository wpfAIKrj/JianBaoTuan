package com.yingluo.Appraiser.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 系统消息
 * @author xy418
 *
 */
public class SystemInfoEntity {

	@SerializedName("treasure_id")
	public long treasure_id;
	
	@SerializedName("content")
	public String content;
	
	@SerializedName("time")
	public String time;
}
