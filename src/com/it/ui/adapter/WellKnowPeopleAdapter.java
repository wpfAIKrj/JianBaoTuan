package com.it.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.it.R;
import com.it.ui.adapter.MyLoveAdapter.HolderView;
import com.it.view.CircleImageView;

public class WellKnowPeopleAdapter extends BaseAdapter {

	Context mContext;
	List<String> list;

	public WellKnowPeopleAdapter(Context context, List<String> strs) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.list = strs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_well_know_adapter, parent, false);
			holder = new ViewHolder();
			holder.iv = (CircleImageView) convertView.findViewById(R.id.iv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.iv.setImageResource(R.drawable.test_x2);

		return convertView;
	}

	class ViewHolder {
		CircleImageView iv;
	}

}
