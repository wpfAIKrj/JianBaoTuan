package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter.MyViewHolder;
import com.yingluo.Appraiser.view.home.ViewHots;

public class IdentiyAdapter extends RecyclerView.Adapter<MyViewHolder> {

	private List<CollectionTreasure> hots;

	static class MyViewHolder extends ViewHolder {
		ViewHots hotsView;

		public MyViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			hotsView = (ViewHots) itemView.findViewById(R.id.hot);
		}

	}

	public IdentiyAdapter(List<CollectionTreasure> list) {
		// TODO Auto-generated constructor stub
		this.hots = list;
	}

	public IdentiyAdapter() {
		// TODO Auto-generated constructor stub
		hots = new ArrayList<CollectionTreasure>();
	}

	public void setData(List<CollectionTreasure> list) {
		this.hots=list;
		notifyDataSetChanged();
	}

	public List<CollectionTreasure> getData() {
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
		CollectionTreasure entity = hots.get(position);
		holder.hotsView.setItem(entity);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_identified, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new MyViewHolder(view);
	}

}
