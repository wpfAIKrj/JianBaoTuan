package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.TreasureType;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 宝物分类
 * @author Administrator
 *
 */
public class KindSecondViewHolder extends ViewHolder {

	public View main;
	public 	ImageView iv;
	public TextView tv;
	public ViewStub layoutNext=null;
	public LinearLayout layoutTitle;
	public RecyclerView nextlv;
	public TreasureType type;
	
	public KindSecondViewHolder(View itemView) {
		super(itemView);
		// TODO Auto-generated constructor stub
		main=itemView;
		iv = (ImageView) itemView.findViewById(R.id.iv_icon);
		tv = (TextView) itemView.findViewById(R.id.tv_precious_name);
		layoutTitle=(LinearLayout)itemView.findViewById(R.id.layout_title);
		
	}

	public void showData(TreasureType treasureType) {
		// TODO Auto-generated method stub
		this.type=treasureType;
		tv.setText(type.getName());
	}

}
