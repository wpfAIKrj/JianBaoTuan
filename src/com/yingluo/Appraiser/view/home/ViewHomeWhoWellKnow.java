package com.yingluo.Appraiser.view.home;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingluo.Appraiser.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yingluo.Appraiser.bean.CollectionTreasure;
import com.yingluo.Appraiser.config.Const;
import com.yingluo.Appraiser.ui.activity.ActivityHotIdentiy;
import com.yingluo.Appraiser.utils.BitmapsUtils;

public class ViewHomeWhoWellKnow extends LinearLayout {

	@ViewInject(R.id.iv_head)
	ImageView iv_head;

	@ViewInject(R.id.tv_name)
	TextView tv_name;

	@ViewInject(R.id.tv_identify_place)
	TextView tv_identify_place;

	@ViewInject(R.id.tv_msg)
	TextView tv_msg;

	private CollectionTreasure entity;

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

	
	public OnClickListener listener;
	
	
	public OnClickListener getListener() {
		return listener;
	}

	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.item_well_know, this);
		ViewUtils.inject(this);
		iv_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(listener!=null){
					v.setTag(entity);
					listener.onClick(v);
				}
			}
		});
	}

	public void setItem(CollectionTreasure entity) {
		this.entity=entity;
		BitmapsUtils.getInstance().display(iv_head, entity.authImage);
		tv_name.setText(entity.authName);
		tv_identify_place.setText(entity.company);
		tv_msg.setText(entity.goodAt);

	}

}
