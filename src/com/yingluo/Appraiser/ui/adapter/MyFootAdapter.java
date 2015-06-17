package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.ui.adapter.IdentiyAdapter.MyViewHolder;
import com.yingluo.Appraiser.view.home.ViewHots;
import com.yingluo.Appraiser.view.viewholder.AcrivleFootVIewholder;
import com.yingluo.Appraiser.view.viewholder.IdentityFootViewholder;

public class MyFootAdapter extends RecyclerView.Adapter<ViewHolder> {

	private List<CollectionTreasure> list;
	private OnClickListener lis;

	

	public MyFootAdapter(OnClickListener lis,List<CollectionTreasure> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.lis=lis;
	}

	public MyFootAdapter(OnClickListener lis) {
		// TODO Auto-generated constructor stub
		list = new ArrayList<CollectionTreasure>();
		this.lis=lis;
	}

	public void setData(List<CollectionTreasure> list) {
		if (this.list == null) {
			this.list = list;
		} else {
			this.list.clear();
			this.list.addAll(list);
		}
		notifyDataSetChanged();
	}

	public List<CollectionTreasure> getData() {
		return list;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getItemViewType(int position) {
		// TODO 自动生成的方法存根
		if(list.get(position).type==0) return 0;
		if(list.get(position).type==1) return 1;
		return super.getItemViewType(position);
	}
	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO 自动生成的方法存根
		if(arg0 instanceof AcrivleFootVIewholder){//文章
			AcrivleFootVIewholder av=(AcrivleFootVIewholder) arg0;
			CollectionTreasure entity = list.get(arg1);
			av.setItem(entity);
		}
		if(arg0 instanceof IdentityFootViewholder){//宝物
			IdentityFootViewholder vh=(IdentityFootViewholder) arg0;
			CollectionTreasure entity = list.get(arg1);
			vh.hotsView.setItem(entity);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO 自动生成的方法存根
		if(arg1==1){
			return new AcrivleFootVIewholder(LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_home_3, arg0,false), lis);
		}
		if(arg1==0){
			View view = LayoutInflater.from(arg0.getContext()).inflate(
					R.layout.item_identified, arg0, false);
			// ViewHolder参数一定要是Item的Root节点.
			return new IdentityFootViewholder(view);
		}
		return null;
	}



}
