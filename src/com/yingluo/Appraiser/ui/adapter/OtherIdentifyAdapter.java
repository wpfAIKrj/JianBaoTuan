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
import com.yingluo.Appraiser.http.ResponseMyIdentify.userIdentify;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.view.viewholder.OtherIdentityHolder;
import com.yingluo.Appraiser.view.viewholder.ViewTreasure;
import com.yingluo.Appraiser.view.viewholder.footerViewHolder;

/**
 * @author 王亚立 他的鉴定
 *
 */
public class OtherIdentifyAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<userIdentify> lists;
	private Context context;

	private ListviewLoadListener listview;
	
	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;
	
	private LayoutInflater mInflater;
	
	private int load_type = 2;

	private int type;
	
	public OtherIdentifyAdapter(Context context,ListviewLoadListener listview) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		lists = new ArrayList<userIdentify>();
		this.listview = listview;
	}

	public void setData(List<userIdentify> list) {
		if (this.lists == null) {
			this.lists = list;
		} else {
			this.lists.clear();
			this.lists.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<userIdentify> getData() {
		return lists;
	}

	@Override
	public int getItemViewType(int position) {
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}
	
	@Override
	public int getItemCount() {
		return lists.size()+1;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (holder instanceof footerViewHolder) {
			footerViewHolder foot = (footerViewHolder) holder;
			foot.showloadMore(load_type);
		}
		if (holder instanceof OtherIdentityHolder) {
			OtherIdentityHolder each = (OtherIdentityHolder) holder;
			userIdentify item = lists.get(position);
			each.setItem(item);
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
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		if (arg1 == TYPE_FOOTER) {
			return new footerViewHolder(mInflater.inflate(R.layout.xlistview_footer, viewGroup, false), listview);
		}
		if (arg1 == TYPE_ITEM) {
			View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_other_identify, viewGroup, false);
			// ViewHolder参数一定要是Item的Root节点.
			return new OtherIdentityHolder(context,view);
		}
		return null;
		
	}

}
