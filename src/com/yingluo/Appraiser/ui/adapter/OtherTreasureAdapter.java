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
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.view.home.ViewHots;

/**
 * @author ytmfdw 他的宝贝
 *
 */
public class OtherTreasureAdapter extends RecyclerView.Adapter<OtherTreasureAdapter.otherViewHolder> {

	private List<CollectionTreasure> hots;

	static class otherViewHolder extends ViewHolder {
		ViewHots hotsView;

		public otherViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			hotsView = (ViewHots) itemView.findViewById(R.id.other_treasure);
		}

	}

	public OtherTreasureAdapter(List<CollectionTreasure> list) {
		// TODO Auto-generated constructor stub
		this.hots = list;
	}

	public OtherTreasureAdapter() {
		// TODO Auto-generated constructor stub
		hots = new ArrayList<CollectionTreasure>();
	}

	public void setData(List<CollectionTreasure> list) {
		if (this.hots == null) {
			this.hots = list;
		} else {
			this.hots.clear();
			this.hots.addAll(list);
		}
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
	public void onBindViewHolder(otherViewHolder holder, int position) {
		// TODO Auto-generated method stub
		CollectionTreasure entity = hots.get(position);
		holder.hotsView.setItem(entity);

	}

	@Override
	public otherViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_other_treasure, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new otherViewHolder(view);
	}

}
