package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.view.viewholder.IndentityResultViewHolder;
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
 * 宝物评论列表
 * @author xy418
 *
 */
public class commentListAdapter extends BaseAdapter {

	private List<CommentEntity> list;
	private OnClickListener listner;
	private Context mContext;
	public commentListAdapter(Context context,OnClickListener listner) {
		// TODO 自动生成的构造函数存根
		list=new ArrayList<CommentEntity>();
		this.listner=listner;
		mContext=context;
	}
	
	public void setData(List<CommentEntity> commentlist) {
		// TODO 自动生成的方法存根
		Collections.reverse(commentlist);
		list=commentlist;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public CommentEntity getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		commentItemViewHolder vh=null;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_user_delais_comment, parent, false);
			vh=new commentItemViewHolder(convertView, listner);
			convertView.setTag(vh);
		}else{
			vh=(commentItemViewHolder) convertView.getTag();
		}
		vh.setItem(list.get(position));
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
