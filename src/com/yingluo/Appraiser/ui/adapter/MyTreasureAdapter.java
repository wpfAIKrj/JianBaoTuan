package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.TreasureEntity;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.view.viewholder.ViewTreasure;
import com.yingluo.Appraiser.view.viewholder.footerViewHolder;

/**
 * @author 我的宝贝
 *
 */
public class MyTreasureAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<TreasureEntity> hots;
	private OnClickListener lis;
	private boolean isDel;// 删除
	private List<TreasureEntity> dels;

	private LayoutInflater mInflater;
	private Context context;
	private ListviewLoadListener listview;

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;

	private int load_type = 2;

	private int type;

	public MyTreasureAdapter(Context context, List<TreasureEntity> list, OnClickListener listner,
			ListviewLoadListener listview) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		dels = new ArrayList<TreasureEntity>();
		this.hots = list;
		this.lis = listner;
		this.listview = listview;
	}

	/**
	 * 设置是删除页面还是平常的页面
	 * 
	 * @param isDel
	 */
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	/**
	 * 返回是不是删除
	 * 
	 * @return
	 */
	public boolean isDel() {
		return isDel;
	}

	/**
	 * 设置页面的类型
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
		if (type == Const.PRECIOUS) {
			// 我的宝物
			isDel = false;
		} else if (type == Const.COLLECT) {
			// 收藏宝物
			isDel = false;
		} else if (type == Const.IDENTIFY) {
			// 我的鉴定
			isDel = false;
		}
	}

	public void setData(List<TreasureEntity> list) {
		hots = list;
		if(dels == null) {
			dels = new ArrayList<TreasureEntity>();
		} else {
			dels.clear();
		}
		notifyDataSetChanged();
	}

	public List<TreasureEntity> getData() {
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

	@Override
	public int getItemCount() {
		return hots.size() + 1;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (holder instanceof footerViewHolder) {
			footerViewHolder foot = (footerViewHolder) holder;
			foot.showloadMore(load_type);
		}
		if (holder instanceof ViewTreasure) {
			ViewTreasure each = (ViewTreasure) holder;
			TreasureEntity entity = hots.get(position);
			each.setItem(entity, isDel);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		if (arg1 == TYPE_FOOTER) {
			return new footerViewHolder(mInflater.inflate(R.layout.xlistview_footer, viewGroup, false), listview);
		}
		if (arg1 == TYPE_ITEM) {
			View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_my_treasure, viewGroup,
					false);
			return new ViewTreasure(view, lis, dels,hots);
		}
		return null;
	}

	public List<TreasureEntity> getDels() {
		return dels;
	}

	public void exitSelectMode() {
		dels.clear();
		for(TreasureEntity each:hots) {
			each.isSelect = false;
		}
		notifyDataSetChanged();
	}

	// 全选
	public void selectAll(boolean isSelect) {
		int len = hots.size();
		dels.clear();
		if (isSelect) {
			dels.addAll(hots);
		}

		for (int i = 0; i < len; i++) {
			hots.get(i).isSelect = isSelect;
		}
	}

	//删除成功
	public void delOk() {
		for (final TreasureEntity id : dels) {
			for(TreasureEntity ids : hots) {
				if(ids.treasure_id == id.treasure_id) {
					hots.remove(ids);
					break;
				}
			}
		}
		exitSelectMode();
	}
}
