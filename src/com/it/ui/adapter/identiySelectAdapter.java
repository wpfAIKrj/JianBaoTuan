package com.it.ui.adapter;

import java.util.ArrayList;

import com.it.R;
import com.it.bean.UserInfo;
import com.it.view.viewholder.ChangIdentiyViewHolder;
import com.it.view.viewholder.IdentiyViewHolder;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
/**
 * 加载更多的数据库
 * @author Administrator
 *
 */
public class identiySelectAdapter extends Adapter {

	private ArrayList<UserInfo> list;
	private OnClickListener clickListener;
	private OnCheckedChangeListener checkListener;
	
	
	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;
	public identiySelectAdapter(ArrayList<UserInfo> list,OnClickListener click,OnCheckedChangeListener check) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.checkListener=check;
		this.clickListener=click;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size()+1;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}
	
	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof IdentiyViewHolder){
				IdentiyViewHolder iv=(IdentiyViewHolder) arg0;
				iv.showData(list.get(arg1));
		}
		if(arg0 instanceof ChangIdentiyViewHolder){
			if(arg1==(list.size()+1)){
				
			}
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg1==TYPE_FOOTER){
			View view=LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_identy_select, arg0, false);
			return new ChangIdentiyViewHolder(view, clickListener);
		}
		if(arg1==TYPE_ITEM){
			View view=LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_identy_people, arg0, false);
			return new IdentiyViewHolder(view, checkListener);
		}
		return null;
	}

}
