package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.bean.CommentEntity;
import com.yingluo.Appraiser.view.viewholder.IndentityResultViewHolder;
import com.yingluo.Appraiser.view.viewholder.commentItemViewHolder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
/**
 * 宝物鉴定结果
 * @author xy418
 *
 */
public class IndentiyResultAdapter extends BaseAdapter {

	private List<CommentEntity> list;
	private Context mContext;
	public IndentiyResultAdapter(Context context) {
		list=new ArrayList<CommentEntity>();
		this.mContext=context;
	}


		
	public void setData(List<CommentEntity> commentlist) {
		list=commentlist;
		notifyDataSetChanged();
	}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
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
		IndentityResultViewHolder vh=null;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_user_delais_identify_result, parent, false);
			vh=new IndentityResultViewHolder(convertView);
			convertView.setTag(vh);
		}else{
			vh=(IndentityResultViewHolder) convertView.getTag();
		}
		vh.setItem(list.get(position));
		return convertView;
	}
	
}
