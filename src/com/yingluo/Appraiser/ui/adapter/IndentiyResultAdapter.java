package com.yingluo.Appraiser.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.view.viewholder.IndentityResultViewHolder;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
/**
 * 宝物鉴定结果
 * @author xy418
 *
 */
public class IndentiyResultAdapter extends Adapter<IndentityResultViewHolder> {

	
	@Override
	public int getItemCount() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void onBindViewHolder(IndentityResultViewHolder arg0, int arg1) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public IndentityResultViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO 自动生成的方法存根
		return new IndentityResultViewHolder(LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_user_delais_identify_result, arg0, false));
	}

	
}
