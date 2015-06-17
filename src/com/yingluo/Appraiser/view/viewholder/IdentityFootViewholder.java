package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.view.home.ViewHots;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public class IdentityFootViewholder extends ViewHolder {

	
	public ViewHots hotsView;
	public IdentityFootViewholder(View itemView) {
		super(itemView);
		// TODO 自动生成的构造函数存根
		hotsView = (ViewHots) itemView.findViewById(R.id.hot);
	}

}
