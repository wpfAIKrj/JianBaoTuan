package com.it.ui.adapter;

import java.util.ArrayList;

import com.it.R;
import com.it.bean.ContentInfo;
import com.it.config.Const;
import com.it.view.viewholder.ArticleViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 加载文章列表
 * @author Administrator
 *
 */
public class ArticleAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context context;
	private ArrayList<ContentInfo> list;
	public ArticleAdapter(Context context,ArrayList<ContentInfo> list) {
		// TODO Auto-generated constructor stub
		this.context=context;
		mInflater=LayoutInflater.from(context);
		this.list=list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ContentInfo getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ArticleViewHolder av=null;
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.item_info,parent,false);
			av=new ArticleViewHolder(convertView);
			convertView.setTag(av);
		}else{
			av=(ArticleViewHolder) convertView.getTag();
		}
		av.showData(list.get(position));
		return convertView;
	}

	public void clearData() {
		// TODO Auto-generated method stub
		list.clear();
	}

	/**
	 * 
	 * @param data
	 */
	public void addList(ArrayList<ContentInfo> data) {
		// TODO Auto-generated method stub
		if(list.size()==0){
			list.addAll(data);	
		}else {
			for (int i = 0; i < data.size(); i++) {
				if(!list.contains(data.get(i))){
					list.add(data.get(i));
				}
			}
		}
	}
	
	
	

}
