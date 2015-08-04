package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.view.viewholder.IndentityResultViewHolder;
import com.yingluo.Appraiser.view.viewholder.NewItemViewHolder;
import com.yingluo.Appraiser.view.viewholder.commentItemViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
/**
 * 新版首页列表
 * @author 王亚立
 *
 */
public class NewHomeListAdapter extends BaseAdapter {

	private List<CommentEntity> list;
	
	private OnClickListener listner;
	private Context mContext;
	
	public NewHomeListAdapter(List<CommentEntity> list,Context context,OnClickListener listner) {
		this.list = list;
		this.listner=listner;
		mContext=context;
	}
	
	public void setData(List<CommentEntity> commentlist) {
		Collections.reverse(commentlist);
		list=commentlist;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public CommentEntity getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewItemViewHolder vh=null;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_new_home, parent, false);
			vh=new NewItemViewHolder(convertView, listner);
			convertView.setTag(vh);
		}else{
			vh=(NewItemViewHolder) convertView.getTag();
		}
		vh.clearAllView();
		vh.setItem();
		return convertView;
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
           //获取ListView对应的Adapter
       ListAdapter listAdapter = listView.getAdapter();
       if (listAdapter == null) {
           // pre-condition
           return;
       }

       int totalHeight = 0;
       for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   //listAdapter.getCount()返回数据项的数目
           View listItem = listAdapter.getView(i, null, listView);
           listItem.measure(0, 0);  //计算子项View 的宽高
           totalHeight += listItem.getMeasuredHeight();  //统计所有子项的总高度
       }

       ViewGroup.LayoutParams params = listView.getLayoutParams();
       params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
       //listView.getDividerHeight()获取子项间分隔符占用的高度
       //params.height最后得到整个ListView完整显示需要的高度
       listView.setLayoutParams(params);
   }

	
}
