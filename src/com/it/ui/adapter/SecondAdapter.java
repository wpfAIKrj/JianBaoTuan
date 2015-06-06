package com.it.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.it.R;
import com.it.bean.TreasureType;
import com.it.utils.SqlDataUtil;
import com.it.view.viewholder.KindSecondViewHolder;
import com.it.view.viewholder.KindViewHolder;
/**
 * 宝物分类2级
 * 
 * @author Administrator
 *
 */
public class SecondAdapter extends Adapter<KindSecondViewHolder>{

	private ArrayList<TreasureType> secods;
	private Context mContext;
	private long parent_id;//父级id
	public SecondAdapter(Context context, OnClickListener lis) {
		// TODO Auto-generated constructor stub
		secods=new ArrayList<TreasureType>();
		mContext=context;
	}
	
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return secods.size();
	}

	@Override
	public void onBindViewHolder(KindSecondViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.showData(secods.get(arg1));
	}

	@Override
	public KindSecondViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		return new KindSecondViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_kind_of_precious, arg0, false));
	}


	public void setCurrntId(Long currnt_id) {
		// TODO Auto-generated method stub
		parent_id=currnt_id;
		secods=SqlDataUtil.getInstance().getSecondTreasureType(parent_id);	
		this.notifyDataSetChanged();
	}
	
}