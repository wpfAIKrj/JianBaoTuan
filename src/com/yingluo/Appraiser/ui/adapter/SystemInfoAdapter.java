package com.yingluo.Appraiser.ui.adapter;

import com.yingluo.Appraiser.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SystemInfoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	public SystemInfoAdapter(LayoutInflater inflater) {
		// TODO Auto-generated constructor stub
		mInflater=inflater;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return mInflater.inflate(R.layout.item_sysinfo, null);
	}

}
