package com.yingluo.Appraiser.view.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.utils.BitmapsUtils;
import com.yingluo.Appraiser.utils.DensityUtil;

public class likeViewHolder extends ViewHolder {

	private CollectionTreasure entity;
	
	@ViewInject(R.id.imageView)
	private ImageView logo;
	
	
	public likeViewHolder(View itemView,final OnClickListener lisntener) {
		super(itemView);
		ViewUtils.inject(this, itemView);
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.setTag(entity);
				if(lisntener!=null){
					lisntener.onClick(v);
				}
			}
		});
	}
	
	public void showData(CollectionTreasure entity,int width){
		this.entity=entity;
		ViewGroup.LayoutParams rlParams = logo.getLayoutParams();
        rlParams.width = width;
        rlParams.height = width;
        logo.setLayoutParams(rlParams);
		BitmapsUtils.getInstance().display(logo, entity.image);
	}

}
