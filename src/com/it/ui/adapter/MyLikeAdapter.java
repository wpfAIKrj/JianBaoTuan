package com.it.ui.adapter;

import java.util.ArrayList;

import com.it.R;
import com.it.bean.ChoicesEntity;
import com.it.view.viewholder.likeViewHolder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class MyLikeAdapter extends Adapter<ViewHolder> {

	private ArrayList<ChoicesEntity> list;
	private OnClickListener lis;
	
	public MyLikeAdapter(ArrayList<ChoicesEntity> list,OnClickListener lis) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.lis=lis;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		likeViewHolder view=(likeViewHolder) arg0;
		view.showData(list.get(arg1));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
		return new likeViewHolder(LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_my_love, arg0, false), lis);
	}

}
