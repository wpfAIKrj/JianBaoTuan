package com.it.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.it.R;
import com.it.model.HomeItem0;
import com.it.view.HomeItemView;
import com.it.view.ViewHomeItem0;

public class HomeItem0Adapter extends BaseAdapter {

	List<HomeItem0> list;
	Context mContext;

	public HomeItem0Adapter(Context context, List<HomeItem0> list) {
		this.mContext = context;
		this.list = list;
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
			convertView = new ViewHomeItem0(mContext);
			holder = new ViewHolder();
			holder.hiv = (ViewHomeItem0) convertView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		HomeItem0 tem = list.get(position);
		// for test
		holder.hiv.setBigImage(mContext.getResources().getDrawable(
				R.drawable.test_x1));
		holder.hiv.setSmallImage(mContext.getResources().getDrawable(
				R.drawable.test_x2));
		// holder.hiv.setGradeImage(tem.grade);
		holder.hiv.setGradeImage(1);
		holder.hiv.setNum(tem.num + "");
		holder.hiv.setName(tem.name);
		return convertView;
	}

	class ViewHolder {
		ViewHomeItem0 hiv;
	}

}
