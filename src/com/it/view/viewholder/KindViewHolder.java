package com.it.view.viewholder;

import com.it.R;
import com.it.bean.TreasureType;
import com.it.ui.adapter.SecondAdapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewStub;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 宝物分类
 * @author Administrator
 *
 */
public class KindViewHolder extends ViewHolder {

	public View main;
	public 	ImageView iv;
	public TextView tv;
	public LinearLayout layoutTitle;
	public RecyclerView nextlv;
	public TreasureType type;
	public SecondAdapter madapter;
	public KindViewHolder(View itemView,OnClickListener lis) {
		super(itemView);
		// TODO Auto-generated constructor stub
		main=itemView;
		iv = (ImageView) itemView.findViewById(R.id.iv_icon);
		tv = (TextView) itemView.findViewById(R.id.tv_precious_name);
		layoutTitle=(LinearLayout)itemView.findViewById(R.id.layout_title);
		nextlv=(RecyclerView) itemView.findViewById(R.id.kind_next);
		LinearLayoutManager manager=new LinearLayoutManager(itemView.getContext());
		nextlv.setLayoutManager(manager);
		madapter=new SecondAdapter(itemView.getContext(),lis);
		nextlv.setAdapter(madapter);
		
	}

	public void showData(TreasureType treasureType) {
		// TODO Auto-generated method stub
		this.type=treasureType;
		tv.setText(type.getName());
	}

	public void shouExpandable(Long currnt_id){
		madapter.setCurrntId(currnt_id);
		nextlv.setVisibility(View.VISIBLE);
	}
}
