package com.yingluo.Appraiser.ui.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

	public Drawable getDrawable(int position) {
		ViewHolder holder = maps.get(Integer.valueOf(position));
		return holder.iv.getDrawable();
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
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (holder.iv.getTag() == null) {
			BitmapsUtils bitmapsUtils = BitmapsUtils.getInstance();
			bitmapsUtils.display(holder.iv, list.get(position).authImage, BitmapsUtils.TYPE_YES);
			holder.iv.setTag(list.get(position).authImage);
		}
		maps.put(Integer.valueOf(position), holder);
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
