package com.yingluo.Appraiser.view.home;

import com.yingluo.Appraiser.R;
import com.yingluo.Appraiser.bean.CollectionTreasure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ViewUserDelaisIdentifyResult extends LinearLayout {

	
	
	CollectionTreasure currnt;
	
	public ViewUserDelaisIdentifyResult(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewUserDelaisIdentifyResult(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public ViewUserDelaisIdentifyResult(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(
				R.layout.item_user_delais_identify_result, this);
		
	}

	public void setItem(CollectionTreasure item){
		currnt=item;
	}
}
