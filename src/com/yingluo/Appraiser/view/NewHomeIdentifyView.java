package com.yingluo.Appraiser.view;

import com.yingluo.Appraiser.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewHomeIdentifyView extends RelativeLayout {

	private CircleImageView head;
	private TextView tvName,tvIntroduction,tvSay;
	private TagLinearLayout tllIdentify;
	
	public NewHomeIdentifyView(Context context) {
		this(context,null);
	}

	public NewHomeIdentifyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	
	public NewHomeIdentifyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View view = LayoutInflater.from(context).inflate(R.layout.new_has_identify, this, true); 
		head = (CircleImageView) view.findViewById(R.id.tv_home_arrow);
		tvName = (TextView) view.findViewById(R.id.tv_identifyer_name);
		tvIntroduction = (TextView) view.findViewById(R.id.tv_identifyer_introduction);
		tvSay = (TextView) view.findViewById(R.id.tv_identifer_say);		
		tllIdentify = (TagLinearLayout) view.findViewById(R.id.tll_identify_tag);
		 	 
	}
}
