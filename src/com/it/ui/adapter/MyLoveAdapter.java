package com.it.ui.adapter;




import com.it.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLoveAdapter extends BaseAdapter{

	private Context mContext;
	
	public MyLoveAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext=context;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView = null;
		View view  = convertView;
		
		if(view == null ){
			holderView = new HolderView();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_my_love, parent, false);
			
			holderView.imageView =(ImageView) view.findViewById(R.id.imageView);
			
			view.setTag(holderView);
		}else{
			holderView = (HolderView) view.getTag();
		}
		
		holderView.imageView.setBackgroundColor(Color.BLACK);
		
		return view;
	}
	
	class HolderView{
		ImageView imageView;
	}
}
