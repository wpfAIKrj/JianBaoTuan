package com.it.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 加载更多鉴定师
 * @author Administrator
 *
 */
public class ChangIdentiyViewHolder extends RecyclerView.ViewHolder {

	public ChangIdentiyViewHolder(View itemView,final OnClickListener listener) {
		super(itemView);
		// TODO Auto-generated constructor stub
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null){
					listener.onClick(v);
				}
			}
		});
	}

}
