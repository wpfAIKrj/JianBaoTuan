package com.it.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.R;
import com.it.bean.HotsEntity;
import com.it.ui.adapter.IdentiyAdapter.MyViewHolder;
import com.it.view.home.ViewHots;

public class IdentiyAdapter extends RecyclerView.Adapter<MyViewHolder> {

	private List<HotsEntity> hots;

	static class MyViewHolder extends ViewHolder {
		ViewHots hotsView;

		public MyViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			hotsView = (ViewHots) itemView.findViewById(R.id.hot);
		}

	}

	public IdentiyAdapter(List<HotsEntity> list) {
		// TODO Auto-generated constructor stub
		this.hots = list;
	}

	public IdentiyAdapter() {
		// TODO Auto-generated constructor stub
		hots = new ArrayList<HotsEntity>();
	}

	public void setData(List<HotsEntity> list) {
		if (this.hots == null) {
			this.hots = list;
		} else {
			this.hots.clear();
			this.hots.addAll(list);
		}
	}

	public List<HotsEntity> getData() {
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
		HotsEntity entity = hots.get(position);
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