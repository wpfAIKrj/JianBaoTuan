package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.ui.adapter.MyTreasureAdapter.MyViewHolder;
import com.yingluo.Appraiser.view.home.ViewTreasure;

/**
 * @author ytmfdw 我的宝贝
 *
 */
public class MyTreasureAdapter extends RecyclerView.Adapter<MyViewHolder> {

	private List<TreasureEntity> hots;

	static class MyViewHolder extends ViewHolder {
		ViewTreasure hotsView;

		public MyViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			hotsView = (ViewTreasure) itemView.findViewById(R.id.my_treasure);
		}

	}

	public MyTreasureAdapter(List<TreasureEntity> list) {
		// TODO Auto-generated constructor stub
		this.hots = list;
	}

	public MyTreasureAdapter() {
		// TODO Auto-generated constructor stub
		hots = new ArrayList<TreasureEntity>();
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
		// TODO Auto-generated method stub
		return hots.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		// TODO Auto-generated method stub
		TreasureEntity entity = hots.get(position);
		holder.hotsView.setItem(entity);

	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_my_treasure, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new MyViewHolder(view);
	}

}
