package com.it.view.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.it.R;
import com.it.bean.CollectionEntity;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class likeViewHolder extends ViewHolder {

	private CollectionEntity entity;
	
	@ViewInject(R.id.imageView)
	private ImageView logo;
	
	
	public likeViewHolder(View itemView,final OnClickListener lisntener) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this, itemView);
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.setTag(entity);
				if(lisntener!=null){
					lisntener.onClick(v);
				}
			}
		});
	}
	
	public void showData(CollectionEntity entity){
		this.entity=entity;
		BitmapsUtils.getInstance().display(logo, entity.image,logo.getMeasuredWidth(),logo.getMeasuredHeight());
	}

}
