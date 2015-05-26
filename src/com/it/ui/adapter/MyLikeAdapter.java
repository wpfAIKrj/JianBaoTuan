package com.it.ui.adapter;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.it.R;
import com.it.bean.CollectionEntity;
import com.it.view.viewholder.likeViewHolder;

public class MyLikeAdapter extends Adapter<ViewHolder> {

	private ArrayList<CollectionEntity> list;
	private OnClickListener lis;

	public MyLikeAdapter(ArrayList<CollectionEntity> list, OnClickListener lis) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.lis = lis;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		likeViewHolder view = (likeViewHolder) arg0;
		view.showData(list.get(arg1));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub

		return new likeViewHolder(LayoutInflater.from(arg0.getContext())
				.inflate(R.layout.item_my_love, arg0, false), lis);
	}

}
