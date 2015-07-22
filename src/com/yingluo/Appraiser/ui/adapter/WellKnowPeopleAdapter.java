package com.yingluo.Appraiser.ui.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.view.CircleImageView;

public class WellKnowPeopleAdapter extends BaseAdapter {

	Context mContext;
	List<CollectionTreasure> list;
	int mindex = 0;
	private Map<Integer,ViewHolder> maps;
	
	public WellKnowPeopleAdapter(Context context, List<CollectionTreasure> strs) {
		this.mContext = context;
		this.list = strs;
		maps = new HashMap<Integer, WellKnowPeopleAdapter.ViewHolder>();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public CollectionTreasure getItem(int position) {
		mindex = position;
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_well_know_adapter, parent, false);
			holder = new ViewHolder();
			holder.iv = (CircleImageView) convertView.findViewById(R.id.iv);
			convertView.setTag(holder);
			maps.put(Integer.valueOf(position), holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		} 
		Log.e("test image", "初始化"+holder.iv);
		if (holder.iv.getTag() == null) {
			Log.e("test image", "又加载了一遍？");
			BitmapsUtils bitmapsUtils = BitmapsUtils.getInstance();
			bitmapsUtils.display(holder.iv, list.get(position).authImage, BitmapsUtils.TYPE_YES);
			holder.iv.setTag(list.get(position).authImage);
		}
		
		if (mindex == position) {
			holder.iv.setAlpha(1);
		} else {
			holder.iv.setAlpha(0.6f);
		}
		return convertView;
	}

	class ViewHolder {
		CircleImageView iv;
	}

}
