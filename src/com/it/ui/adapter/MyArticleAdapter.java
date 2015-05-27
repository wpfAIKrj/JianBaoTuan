package com.it.ui.adapter;

import java.util.ArrayList;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.it.R;
import com.it.bean.ContentInfo;
import com.it.config.Const;
import com.it.inter.ListviewLoadListener;
import com.it.view.viewholder.ArticleViewHolder;
import com.it.view.viewholder.SwipeInfoViewHolder;
import com.it.view.viewholder.footerViewHolder;

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
 * @author Administrator
 *
 */
public class MyArticleAdapter extends  RecyclerSwipeAdapter<ViewHolder> {

	private LayoutInflater mInflater;
	private Context context;
	private ArrayList<ContentInfo> list;
	private OnClickListener onclick;
	private ListviewLoadListener listview;
	private static final int TYPE_ITEM = 0;
	private static final int TYPE_FOOTER = 1;
	
	private int load_type=2;
	private boolean isScorll;
	public MyArticleAdapter(Context context,ArrayList<ContentInfo> list,OnClickListener listner,ListviewLoadListener listview) {
		// TODO Auto-generated constructor stub
		this.context=context;
		mInflater=LayoutInflater.from(context);
		this.list=list;
		this.onclick=listner;
		this.listview=listview;
		load_type=2;
	}
	


	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size()+1;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, final int arg1) {
		// TODO Auto-generated method stub
		if(arg0 instanceof footerViewHolder ){
			footerViewHolder foot=(footerViewHolder) arg0;
			foot.showloadMore(load_type);
		}
		
		if(arg0 instanceof SwipeInfoViewHolder){
			final SwipeInfoViewHolder articie=(SwipeInfoViewHolder) arg0;
			articie.showData(list.get(arg1));
			articie.swipeLayout.setSwipeEnabled(true);
			articie.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
			articie.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
	            @Override
	            public void onOpen(SwipeLayout layout) {
	              //  YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
	            }
	        });
			articie.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
	            @Override
	            public void onDoubleClick(SwipeLayout layout, boolean surface) {
	               // Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
	            }
	        });
			articie.buttonDelete.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                mItemManger.removeShownLayouts(articie.swipeLayout);
	                list.remove(arg1);
	                notifyItemRemoved(arg1);
	                notifyItemRangeChanged(arg1, list.size());
	                mItemManger.closeAllItems();
	          //      Toast.makeText(view.getContext(), "Deleted " + viewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
	            }
	        });
	        mItemManger.bindView(articie.itemView, arg1);
	        articie.swipeLayout.setSwipeEnabled(isScorll);
		}
		
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg1==TYPE_FOOTER){
			return new footerViewHolder(mInflater.inflate(R.layout.xlistview_footer, arg0, false),listview);
		}
		if(arg1==TYPE_ITEM){
			return new SwipeInfoViewHolder(mInflater.inflate(R.layout.item_swipe, arg0, false),onclick);
		}
		return null;
	}

	
	/**
	 * 更改footview显示效果
	 * @param type 0加载更多，1为正在加载， 2为隐藏
	 */
	public void setFootType(int type) {
		// TODO Auto-generated method stub
		load_type=type;
		
	}

	public void setListData(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		list=data;
	}


	public boolean isScorll() {
		return isScorll;
	}

	public void setScorll(boolean isScorll) {
		this.isScorll = isScorll;
	}


	@Override
	public int getSwipeLayoutResourceId(int arg0) {
		// TODO Auto-generated method stub
		return R.id.swipe;
	}
	
	
	

}
