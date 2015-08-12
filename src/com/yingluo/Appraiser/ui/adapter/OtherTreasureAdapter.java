package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.http.ResponseNewHome.HomeItem;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.ui.adapter.NewHomeListAdapter.ClickTabListener;
import com.yingluo.Appraiser.view.viewholder.OtherViewHolder;
import com.yingluo.Appraiser.view.viewholder.ViewTreasure;
import com.yingluo.Appraiser.view.viewholder.footerViewHolder;

/**
 * @author ytmfdw 他的宝贝
 *
 */
public class OtherTreasureAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<HomeItem> hots;
	private Context context;
	private ClickTabListener listener;

	private LayoutInflater mInflater;
	
	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;
	
	private ListviewLoadListener listview;
	
	private int load_type = 2;
	
	public OtherTreasureAdapter(Context context,ClickTabListener listener,ListviewLoadListener listview) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		hots = new ArrayList<HomeItem>();
		this.listener = listener;
		this.listview = listview;
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
	public int getItemViewType(int position) {
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}
	
	/**
	 * 更改footview显示效果
	 * 
	 * @param type 0点击加载更多，1为正在加载， 2为没有更多
	 */
	public void setFootType(int type) {
		load_type = type;
	}
	
	@Override
	public int getItemCount() {
		return hots.size()+1;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (holder instanceof footerViewHolder) {
			footerViewHolder foot = (footerViewHolder) holder;
			foot.showloadMore(load_type);
		}
		if (holder instanceof OtherViewHolder) {
			OtherViewHolder each = (OtherViewHolder) holder;
			HomeItem item = hots.get(position);
			each.setItem(item);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		if (arg1 == TYPE_FOOTER) {
			return new footerViewHolder(mInflater.inflate(R.layout.xlistview_footer, viewGroup, false), listview);
		}
		if (arg1 == TYPE_ITEM) {
			View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_other_treasure, viewGroup, false);
			// ViewHolder参数一定要是Item的Root节点.
			return new OtherViewHolder(context,view,listener);
		}
		return null;
	}

}
