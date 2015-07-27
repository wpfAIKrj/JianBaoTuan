package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.ContentInfo;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.inter.ListviewLoadListener;
import com.yingluo.Appraiser.view.viewholder.ArticleViewHolder;
import com.yingluo.Appraiser.view.viewholder.footerViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 加载文章列表
 * 
 * @author Administrator
 *
 */
public class ArticleAdapter extends RecyclerView.Adapter<ViewHolder> {

	private LayoutInflater mInflater;
	private Context context;
	private ArrayList<ContentInfo> list;
	private OnClickListener onclick;
	private ListviewLoadListener listview;
	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;

	private int load_type = 2;

	public ArticleAdapter(Context context, ArrayList<ContentInfo> list, OnClickListener listner,
			ListviewLoadListener listview) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.onclick = listner;
		this.listview = listview;
		load_type = 2;
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
		return list.size() + 1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		if (arg0 instanceof footerViewHolder) {
			footerViewHolder foot = (footerViewHolder) arg0;
			foot.showloadMore(load_type);
		}

		if (arg0 instanceof ArticleViewHolder) {
			ArticleViewHolder articie = (ArticleViewHolder) arg0;
			articie.showData(list.get(arg1));
		}

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		if (arg1 == TYPE_FOOTER) {
			return new footerViewHolder(mInflater.inflate(R.layout.xlistview_footer, arg0, false), listview);
		}
		if (arg1 == TYPE_ITEM) {
			return new ArticleViewHolder(mInflater.inflate(R.layout.item_info, arg0, false), onclick);
		}
		return null;
	}

	/**
	 * 更改footview显示效果
	 * 
	 * @param type
	 *            0加载更多，1为正在加载， 2为隐藏
	 */
	public void setFootType(int type) {
		load_type = type;

	}

	public void setListData(ArrayList<ContentInfo> data) {
		list = data;
	}

}
