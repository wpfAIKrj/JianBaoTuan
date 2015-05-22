package com.it.view.home;

import com.it.R;
import com.it.bean.AuthorsEntity;
import com.it.utils.BitmapsUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHomeWhoWellKnow extends LinearLayout {

	@ViewInject(R.id.iv_head)
	ImageView iv_head;

	@ViewInject(R.id.tv_name)
	TextView tv_name;

	@ViewInject(R.id.tv_identify_place)
	TextView tv_identify_place;

	@ViewInject(R.id.tv_msg)
	TextView tv_msg;

	public ViewHomeWhoWellKnow(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeWhoWellKnow(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewHomeWhoWellKnow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.item_well_know, this);

		ViewUtils.inject(this);
	}

	public void setItem(AuthorsEntity entity) {
		BitmapsUtils.getInstance().display(iv_head, entity.authImage);
		tv_name.setText(entity.authName);
		tv_identify_place.setText(entity.company);
		tv_msg.setText(entity.goodAt);

	}

}
