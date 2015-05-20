package com.it.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.it.R;
import com.it.bean.AuthorsEntity;
import com.it.ui.adapter.MyLoveAdapter.HolderView;
import com.it.utils.BitmapsUtils;
import com.it.view.CircleImageView;

public class WellKnowPeopleAdapter extends BaseAdapter {

	Context mContext;
	List<AuthorsEntity> list;

	public WellKnowPeopleAdapter(Context context, List<AuthorsEntity> strs) {
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
	public AuthorsEntity getItem(int position) {
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
		BitmapsUtils bitmapsUtils=BitmapsUtils.getInstance();
		bitmapsUtils.display(holder.iv, list.get(position).authImage);
//		holder.iv.setImageResource(R.drawable.user_logo);

		return convertView;
	}

	class ViewHolder {
		CircleImageView iv;
	}

}
