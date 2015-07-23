package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.utils.DensityUtil;
import com.yingluo.Appraiser.view.viewholder.likeViewHolder;

public class MyLikeAdapter extends Adapter<ViewHolder> {

	private ArrayList<CollectionTreasure> list;
	private OnClickListener lis;

	private int imageWidth;
	public MyLikeAdapter(Context context,ArrayList<CollectionTreasure> list, OnClickListener lis) {
		this.list = list;
		this.lis = lis;
		imageWidth = (int) ((DensityUtil.getScreenWidth(context)-context.getResources().getDimension(R.dimen.x7)*4)/3);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		likeViewHolder view = (likeViewHolder) arg0;
		view.showData(list.get(arg1),imageWidth);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		return new likeViewHolder(LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_my_love, arg0, false),
				lis);
	}

}
