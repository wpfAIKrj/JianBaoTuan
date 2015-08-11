package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.view.viewholder.OtherViewHolder;

/**
 * @author ytmfdw 他的宝贝
 *
 */
public class OtherTreasureAdapter extends RecyclerView.Adapter<OtherViewHolder> {

	private List<HomeItem> hots;
	private Context context;
	private ClickTabListener listener;

	public OtherTreasureAdapter(Context context,ClickTabListener listener) {
		this.context = context;
		hots = new ArrayList<HomeItem>();
	}

	public void setData(List<HomeItem> list) {
		if (this.hots == null) {
			this.hots = list;
		} else {
			this.hots.clear();
			this.hots.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<HomeItem> getData() {
		return hots;
	}

	@Override
	public int getItemCount() {
		return hots.size();
	}

	@Override
	public void onBindViewHolder(OtherViewHolder holder, int position) {
		HomeItem item = hots.get(position);
		holder.setItem(item);
	}

	@Override
	public OtherViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.item_other_treasure, viewGroup, false);
		// ViewHolder参数一定要是Item的Root节点.
		return new OtherViewHolder(context,view,listener);
	}

}
