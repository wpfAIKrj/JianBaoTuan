package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.view.viewholder.ViewTreasure;

/**
 * @author ytmfdw 我的宝贝
 *
 */
public class MyTreasureAdapter extends RecyclerView.Adapter<ViewTreasure> {

	private List<TreasureEntity> hots;
	private OnClickListener lis;

	public MyTreasureAdapter(List<TreasureEntity> list, OnClickListener lis) {
		this.hots = list;
		this.lis = lis;
	}

	public MyTreasureAdapter(OnClickListener lis) {
		hots = new ArrayList<TreasureEntity>();
		this.lis = lis;
	}

	public void setData(List<TreasureEntity> list) {
		if (this.hots == null) {
			this.hots = list;
		} else {
			this.hots.clear();
			this.hots.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<TreasureEntity> getData() {
		return hots;
	}

	@Override
	public int getItemCount() {
		return hots.size();
	}

	@Override
	public void onBindViewHolder(ViewTreasure holder, int position) {
		TreasureEntity entity = hots.get(position);
		holder.setItem(entity);

	}

	@Override
	public ViewTreasure onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_my_treasure, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new ViewTreasure(view, lis);
	}

}
