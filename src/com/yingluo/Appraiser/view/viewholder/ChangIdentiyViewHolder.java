package com.yingluo.Appraiser.view.viewholder;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * 加载更多鉴定师
 * @author Administrator
 *
 */
public class ChangIdentiyViewHolder extends RecyclerView.ViewHolder {

	@ViewInject(R.id.user_checkbox)
	public CheckBox checkBox;
	public ChangIdentiyViewHolder(View itemView) {
		super(itemView);
		// TODO Auto-generated constructor stub
		ViewUtils.inject(this, itemView);
	}

}
