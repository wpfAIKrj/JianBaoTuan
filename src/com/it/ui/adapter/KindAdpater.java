package com.it.ui.adapter;

import java.util.ArrayList;

import com.it.R;
import com.it.bean.TreasureType;
import com.it.utils.SqlDataUtil;
import com.it.view.viewholder.KindViewHolder;

import android.R.array;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

public class KindAdpater extends Adapter<KindViewHolder> {
	
	
	
	private Context mContext;
	private OnClickListener lis;
	private ArrayList<TreasureType> list;
	public KindAdpater(Context context,OnClickListener listener) {
		// TODO Auto-generated constructor stub
		this.mContext=context;
		this.lis=listener;
		list=SqlDataUtil.getInstance().getFirsetTreasureType();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}


	@Override
	public void onBindViewHolder(final KindViewHolder arg0, final int arg1) {
		// TODO Auto-generated method stub
		arg0.showData(list.get(arg1));
		//弹出下级页面
		arg0.layoutTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stu
				if(arg0.nextlv.isShown()){//隐藏
					arg0.nextlv.setVisibility(View.GONE);
				}else{//显示
					arg0.shouExpandable(list.get(arg1).getCurrnt_id());
					notifyItemChanged(arg1);
				}
			}
		});
	}


	@Override
	public KindViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		return new KindViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_kind_of_precious, arg0, false),lis);
	}

	/**
	 * 关闭所有
	 */
	public void closeData() {
		// TODO Auto-generated method stub
		
	}
	


}
