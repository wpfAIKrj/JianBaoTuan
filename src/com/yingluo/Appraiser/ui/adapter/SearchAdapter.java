package com.yingluo.Appraiser.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yingluo.Appraiser.R;

public class SearchAdapter extends BaseAdapter {

	Context mContext;
	List<String> list;

	public SearchAdapter(Context context, List<String> list) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_search, null);
			vh = new ViewHolder();
			vh.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.tv.setText(list.get(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv;
	}
}
